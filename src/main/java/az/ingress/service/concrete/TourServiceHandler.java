package az.ingress.service.concrete;

import az.ingress.dao.entity.TourEntity;
import az.ingress.dao.repository.GuideRepository;
import az.ingress.dao.repository.TourRepository;
import az.ingress.dao.repository.TravelerRepository;
import az.ingress.exception.IllegalArgumentException;
import az.ingress.exception.NotFoundException;
import az.ingress.model.request.TourRequest;
import az.ingress.model.response.TourResponse;
import az.ingress.service.abstraction.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static az.ingress.exception.ErrorMessage.GUIDE_NOT_AVAILABLE;
import static az.ingress.exception.ErrorMessage.ILLEGAL_START_OR_END_DATE;
import static az.ingress.exception.ErrorMessage.START_DATE_AFTER_END_DATE;
import static az.ingress.exception.ErrorMessage.TOUR_NOT_FOUND;
import static az.ingress.exception.ErrorMessage.TRAVELER_NOT_AVAILABLE;
import static az.ingress.mapper.TourMapper.TOUR_MAPPER;
import static java.time.LocalDate.now;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourServiceHandler implements TourService {

    private final TourRepository tourRepository;
    private final GuideServiceHandler guideServiceHandler;
    private final GuideRepository guideRepository;
    private final TravelerServiceHandler travelerServiceHandler;
    private final TravelerRepository travelerRepository;

    @Override
    public void create(TourRequest tourRequest) {
        validateTourTimes(tourRequest);
        validateStartBeforeEnd(tourRequest);
        var tourEntity = TOUR_MAPPER.mapTourRequestToTourEntity(tourRequest);
        tourRepository.save(tourEntity);
    }

    @Override
    public TourResponse getById(Long id) {
        var tourEntity = fetchTourOrThrow(id);
        return TOUR_MAPPER.mapTourRequestToTourResponse(tourEntity);
    }

    @Override
    public List<TourResponse> getAllTours() {
        return tourRepository.findAll().stream()
                .map(TOUR_MAPPER::mapTourRequestToTourResponse)
                .toList();
    }

    @Transactional
    @Override
    public void update(Long id, TourRequest tourRequest) {
        var tourEntity = fetchTourOrThrow(id);
        validateTourTimes(tourRequest);
        TOUR_MAPPER.updateTourEntity(tourRequest, tourEntity);
        tourRepository.save(tourEntity);
    }

    @Override
    public void delete(Long id) {
        var tourEntity = fetchTourOrThrow(id);
        tourRepository.delete(tourEntity);
    }

    @Override
    public List<TourResponse> getAllToursByGuideId(Long guideId) {
        return tourRepository.findAllByGuidesId(guideId).stream()
                .map(TOUR_MAPPER::mapTourRequestToTourResponse)
                .toList();
    }

    @Transactional
    @Override
    public void assignGuideToTour(Long tourId, Long guideId) {
        var guideEntity = guideServiceHandler.fetchGuideOrThrow(guideId);
        var tourEntity = fetchTourOrThrow(tourId);

        if (guideRepository.existsGuideBusyInRange(
                guideId, tourEntity.getStartDate(), tourEntity.getEndDate())) {
            log.error("Guide {} is busy between {} and {}",
                    guideId, tourEntity.getStartDate(), tourEntity.getEndDate());
            throw new IllegalArgumentException(GUIDE_NOT_AVAILABLE.getMessage());
        }

        tourEntity.getGuides().add(guideEntity);
        tourRepository.save(tourEntity);
    }

    @Transactional
    @Override
    public void assignTravelerToTour(Long tourId, Long travelerId) {
        var travelerEntity = travelerServiceHandler.fetchTravelerOrThrow(travelerId);
        var tourEntity = fetchTourOrThrow(tourId);

        if (travelerRepository.existsTravelerBusyInRange(
                travelerId, tourEntity.getStartDate(), tourEntity.getEndDate())) {
            log.error("Traveler {} is busy between {} and {}",
                    travelerId, tourEntity.getStartDate(), tourEntity.getEndDate());
            throw new IllegalArgumentException(TRAVELER_NOT_AVAILABLE.getMessage());
        }

        tourEntity.getTravelers().add(travelerEntity);
        tourRepository.save(tourEntity);
    }

    public TourEntity fetchTourOrThrow(Long id) {
        return tourRepository.findById(id).orElseThrow(() -> {
            log.error("ActionLog.fetchTourOrThrow.error: {}", id);
            return new NotFoundException(TOUR_NOT_FOUND.getMessage(), id);
        });
    }

    private void validateTourTimes(TourRequest tourRequest) {
        var now = now();
        if (now.isAfter(tourRequest.getStartDate()) || now.isAfter(tourRequest.getEndDate())) {
            log.error("ActionLog.validateTourTimes.error: now = {}, startDate = {}, endDate = {}",
                    now, tourRequest.getStartDate(), tourRequest.getEndDate());
            throw new IllegalArgumentException(ILLEGAL_START_OR_END_DATE.getMessage());
        }
    }

    private void validateStartBeforeEnd(TourRequest tourRequest) {
        if (tourRequest.getStartDate().isAfter(tourRequest.getEndDate())) {
            log.error("ActionLog.validateStartBeforeEnd.error: startDate = {}, endDate = {}",
                    tourRequest.getStartDate(), tourRequest.getEndDate());
            throw new IllegalArgumentException(START_DATE_AFTER_END_DATE.getMessage());
        }
    }
}
