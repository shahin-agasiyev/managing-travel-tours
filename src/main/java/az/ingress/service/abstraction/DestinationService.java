package az.ingress.service.abstraction;

import az.ingress.model.request.DestinationRequest;
import az.ingress.model.response.DestinationResponse;

import java.util.List;

public interface DestinationService {
    void create(Long tourId, DestinationRequest request);

    DestinationResponse getById(Long id);

    void delete(Long id);

    List<DestinationResponse> getAllByTourId(Long tourId);
}
