package az.ingress.service.concrete;

import az.ingress.dao.entity.DestinationEntity;
import az.ingress.dao.entity.TourEntity;
import az.ingress.dao.repository.DestinationRepository;
import az.ingress.exception.IllegalArgumentException;
import az.ingress.exception.NotFoundException;
import az.ingress.model.request.DestinationRequest;
import az.ingress.model.response.DestinationResponse;
import az.ingress.service.abstraction.DestinationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.exception.ErrorMessage.DESTINATION_NOT_FOUND;
import static az.ingress.exception.ErrorMessage.ILLEGAL_VISIT_DATE;
import static az.ingress.mapper.DestinationMapper.DESTINATION_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class DestinationServiceHandler implements DestinationService {

    private final DestinationRepository destinationRepository;
    private final TourServiceHandler tourServiceHandler;

    @Override
    public void create(Long tourId, DestinationRequest request) {
        var tourEntity = tourServiceHandler.fetchTourOrThrow(tourId);
        validateVisitDate(request, tourEntity);
        var destinationEntity = DESTINATION_MAPPER.mapDestinationRequestToEntity(request);
        destinationEntity.setTour(tourEntity);
        destinationRepository.save(destinationEntity);
    }

    @Override
    public DestinationResponse getById(Long id) {
        var destinationEntity = fetchDestinationOrThrow(id);
        return DESTINATION_MAPPER.mapDestinationEntityToResponse(destinationEntity);
    }

    @Override
    public List<DestinationResponse> getAllByTourId(Long tourId) {
        var tourEntity = tourServiceHandler.fetchTourOrThrow(tourId);
        return destinationRepository.findAllByTour(tourEntity).stream()
                .map(DESTINATION_MAPPER::mapDestinationEntityToResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        fetchDestinationOrThrow(id);
        destinationRepository.deleteById(id);
    }

    private DestinationEntity fetchDestinationOrThrow(Long id) {
        return destinationRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ActionLog.fetchDestinationOrThrow.error.notFound: destinationId = {}", id);
                    return new NotFoundException(DESTINATION_NOT_FOUND.getMessage(), id);
                });
    }

    private void validateVisitDate(DestinationRequest request, TourEntity tourEntity) {
        if (tourEntity.getStartDate().isAfter(request.getVisitDate()) ||
                tourEntity.getEndDate().isBefore(request.getVisitDate())) {
            log.error("ActionLog.extracted.error: start date = {}, end date = {}, visit date = {}",
                    tourEntity.getStartDate(), tourEntity.getEndDate(), request.getVisitDate());
            throw new IllegalArgumentException(ILLEGAL_VISIT_DATE.getMessage());
        }
    }
}
