package az.ingress.service.abstraction;

import az.ingress.model.request.PassportRequest;
import az.ingress.model.response.PassportResponse;

import javax.validation.Valid;

public interface PassportService {
    void create(Long guideId, PassportRequest passportRequest);

    PassportResponse getById(Long id);

    void update(Long id, @Valid PassportRequest passportRequest);

    void delete(Long id);
}
