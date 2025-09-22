package az.ingress.service.abstraction;

import az.ingress.model.request.PassportRequest;
import az.ingress.model.response.PassportResponse;

public interface PassportService {
    void create(Long guideId, PassportRequest passportRequest);

    PassportResponse getById(Long id);

    void delete(Long id);
}
