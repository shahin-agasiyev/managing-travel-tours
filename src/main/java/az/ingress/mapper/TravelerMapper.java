package az.ingress.mapper;

import az.ingress.dao.entity.TravelerEntity;
import az.ingress.model.request.TravelerRequest;
import az.ingress.model.response.TravelerResponse;

public enum TravelerMapper {
    TRAVELER_MAPPER;

    public TravelerEntity mapTravelerRequestToEntity(TravelerRequest travelerRequest) {
        return TravelerEntity.builder()
                .firstName(travelerRequest.getFirstName())
                .lastName(travelerRequest.getLastName())
                .email(travelerRequest.getEmail())
                .build();
    }

    public TravelerResponse mapTravelerEntityToResponse(TravelerEntity travelerEntity) {
        return TravelerResponse.builder()
                .id(travelerEntity.getId())
                .firstName(travelerEntity.getFirstName())
                .lastName(travelerEntity.getLastName())
                .email(travelerEntity.getEmail())
                .build();
    }
}
