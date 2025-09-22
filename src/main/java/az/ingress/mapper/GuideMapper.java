package az.ingress.mapper;

import az.ingress.dao.entity.GuideEntity;
import az.ingress.model.request.GuideRequest;
import az.ingress.model.response.GuideResponse;

public enum GuideMapper {
    GUIDE_MAPPER;

    public GuideEntity mapGuideRequestToEntity(GuideRequest guideRequest) {
        return GuideEntity.builder()
                .name(guideRequest.getName())
                .email(guideRequest.getEmail())
                .phoneNumber(guideRequest.getPhoneNumber())
                .build();
    }

    public GuideResponse mapGuideEntityToResponse(GuideEntity guideEntity) {
        return GuideResponse.builder()
                .id(guideEntity.getId())
                .name(guideEntity.getName())
                .email(guideEntity.getEmail())
                .phoneNumber(guideEntity.getPhoneNumber())
                .build();
    }
}
