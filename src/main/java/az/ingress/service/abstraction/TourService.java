package az.ingress.service.abstraction;

import az.ingress.model.request.TourRequest;
import az.ingress.model.response.TourResponse;

import javax.validation.Valid;
import java.util.List;

public interface TourService {
    void create(@Valid TourRequest tourRequest);

    TourResponse getById(Long id);

    List<TourResponse> getAllTours();

    void update(Long id, @Valid TourRequest tourRequest);

    void delete(Long id);

    List<TourResponse> getAllToursByGuideId(Long guideId);

    void assignGuideToTour(Long tourId, Long guideId);

    void assignTravelerToTour(Long tourId, Long travelerId);
}
