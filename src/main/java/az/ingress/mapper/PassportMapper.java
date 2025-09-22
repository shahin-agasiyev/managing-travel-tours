package az.ingress.mapper;

import az.ingress.dao.entity.GuideEntity;
import az.ingress.dao.entity.PassportEntity;
import az.ingress.model.request.PassportRequest;
import az.ingress.model.response.PassportResponse;

import java.time.LocalDate;

public enum PassportMapper {
    PASSPORT_MAPPER;

    public PassportResponse mapPassportEntityToResponse(PassportEntity passportEntity) {
        return PassportResponse.builder()
                .id(passportEntity.getId())
                .country(passportEntity.getCountry())
                .expiryDate(passportEntity.getExpiryDate())
                .issueDate(passportEntity.getIssueDate())
                .build();
    }

    public PassportEntity getPassportEntity(PassportRequest passportRequest, GuideEntity guideEntity, LocalDate issueDate, LocalDate expiryDate) {
        return PassportEntity.builder()
                .country(passportRequest.getCountry())
                .guide(guideEntity)
                .issueDate(issueDate)
                .expiryDate(expiryDate)
                .build();
    }
}
