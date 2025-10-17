package az.ingress.service.concrete;

import az.ingress.dao.entity.TravelerEntity;
import az.ingress.dao.repository.TravelerRepository;
import az.ingress.exception.NotFoundException;
import az.ingress.model.request.TravelerRequest;
import az.ingress.model.response.TravelerResponse;
import az.ingress.service.abstraction.TravelerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.exception.ErrorMessage.TRAVELER_NOT_FOUND;
import static az.ingress.mapper.TravelerMapper.TRAVELER_MAPPER;

@Slf4j
@Service
public class TravelerServiceHandler implements TravelerService {

    private final TravelerRepository travelerRepository;
    private final TourServiceHandler tourServiceHandler;

    public TravelerServiceHandler(TravelerRepository travelerRepository,
                                  @Lazy TourServiceHandler tourServiceHandler) {
        this.travelerRepository = travelerRepository;
        this.tourServiceHandler = tourServiceHandler;
    }

    @Override
    public void create(TravelerRequest travelerRequest) {
        var travelerEntity = TRAVELER_MAPPER.mapTravelerRequestToEntity(travelerRequest);
        travelerRepository.save(travelerEntity);
    }

    @Override
    public TravelerResponse getById(Long id) {
        var travelerEntity = fetchTravelerOrThrow(id);
        return TRAVELER_MAPPER.mapTravelerEntityToResponse(travelerEntity);
    }

    @Override
    public List<TravelerResponse> getAll() {
        return travelerRepository.findAll().stream()
                .map(TRAVELER_MAPPER::mapTravelerEntityToResponse)
                .toList();
    }

    @Override
    public List<TravelerResponse> getByTourId(Long tourId) {
        tourServiceHandler.fetchTourOrThrow(tourId);
        return travelerRepository.findAllByTourId(tourId).stream()
                .map(TRAVELER_MAPPER::mapTravelerEntityToResponse)
                .toList();
    }

    @Override
    public void update(Long id, TravelerRequest travelerRequest) {
        var travelerEntity = fetchTravelerOrThrow(id);
        TRAVELER_MAPPER.updateTravelerFields(travelerRequest, travelerEntity);
        travelerRepository.save(travelerEntity);
    }

    @Override
    public void delete(Long id) {
        fetchTravelerOrThrow(id);
        travelerRepository.deleteById(id);
    }

    public TravelerEntity fetchTravelerOrThrow(Long id) {
        return travelerRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ActionLog.fetchTravelerOrThrow.error : {}", id);
                    return new NotFoundException(TRAVELER_NOT_FOUND.getMessage(), id);
                });
    }
}
