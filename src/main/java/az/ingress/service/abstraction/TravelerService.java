package az.ingress.service.abstraction;

import az.ingress.model.request.TravelerRequest;
import az.ingress.model.response.TravelerResponse;

import javax.validation.Valid;
import java.util.List;

public interface TravelerService {
    void create(@Valid TravelerRequest travelerRequest);

    TravelerResponse getById(Long id);

    List<TravelerResponse> getAll();

    List<TravelerResponse> getByTourId(Long tourId);

    void delete(Long id);
}
