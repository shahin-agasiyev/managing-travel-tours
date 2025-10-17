package az.ingress.service.abstraction;

import az.ingress.model.request.GuideRequest;
import az.ingress.model.response.GuideResponse;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

public interface GuideService {
    void create(@Valid GuideRequest guideRequest);

    GuideResponse getById(Long id);

    List<GuideResponse> getAllGuides();

    List<GuideResponse> getAvailableGuides(LocalDate startDate, LocalDate endDate);

    void update(Long id, @Valid GuideRequest guideRequest);

    void delete(Long id);
}
