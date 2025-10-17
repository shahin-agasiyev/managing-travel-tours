package az.ingress.service.concrete;

import az.ingress.dao.entity.GuideEntity;
import az.ingress.dao.repository.GuideRepository;
import az.ingress.exception.NotFoundException;
import az.ingress.model.request.GuideRequest;
import az.ingress.model.response.GuideResponse;
import az.ingress.service.abstraction.GuideService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static az.ingress.exception.ErrorMessage.GUIDE_NOT_FOUND;
import static az.ingress.mapper.GuideMapper.GUIDE_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuideServiceHandler implements GuideService {

    private final GuideRepository guideRepository;

    @Override
    public void create(GuideRequest guideRequest) {
        var guideEntity = GUIDE_MAPPER.mapGuideRequestToEntity(guideRequest);
        guideRepository.save(guideEntity);
    }

    @Override
    public GuideResponse getById(Long id) {
        var guideEntity = fetchGuideOrThrow(id);
        return GUIDE_MAPPER.mapGuideEntityToResponse(guideEntity);
    }

    @Override
    public List<GuideResponse> getAllGuides() {
        return guideRepository.findAll().stream()
                .map(GUIDE_MAPPER::mapGuideEntityToResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        fetchGuideOrThrow(id);
        guideRepository.deleteById(id);
    }

    @Override
    public List<GuideResponse> getAvailableGuides(LocalDate startDate, LocalDate endDate) {
        return guideRepository.findAllAvailableGuides(startDate, endDate)
                .stream()
                .map(GUIDE_MAPPER::mapGuideEntityToResponse)
                .toList();
    }

    @Override
    public void update(Long id, GuideRequest guideRequest) {
        var guideEntity = fetchGuideOrThrow(id);
        GUIDE_MAPPER.updateGuideFields(guideRequest, guideEntity);
        guideRepository.save(guideEntity);
    }

    public GuideEntity fetchGuideOrThrow(Long id) {
        return guideRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ActionLog.fetchGuideOrThrow.error: {}", id);
                    return new NotFoundException(GUIDE_NOT_FOUND.getMessage(), id);
                });
    }
}
