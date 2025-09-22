package az.ingress.mapper;

import az.ingress.dao.entity.DestinationEntity;
import az.ingress.model.request.DestinationRequest;
import az.ingress.model.response.DestinationResponse;

public enum DestinationMapper {
    DESTINATION_MAPPER;

    public DestinationEntity mapDestinationRequestToEntity(DestinationRequest request) {
        return DestinationEntity.builder()
                .location(request.getLocation())
                .description(request.getDescription())
                .visitDate(request.getVisitDate())
                .build();
    }

    public DestinationResponse mapDestinationEntityToResponse(DestinationEntity destinationEntity) {
        return DestinationResponse.builder()
                .id(destinationEntity.getId())
                .location(destinationEntity.getLocation())
                .description(destinationEntity.getDescription())
                .visitDate(destinationEntity.getVisitDate())
                .build();
    }
}
