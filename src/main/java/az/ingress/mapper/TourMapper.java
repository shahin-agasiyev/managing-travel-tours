package az.ingress.mapper;

import az.ingress.dao.entity.TourEntity;
import az.ingress.model.request.TourRequest;
import az.ingress.model.response.TourResponse;

public enum TourMapper {
    TOUR_MAPPER;

    public TourEntity mapTourRequestToTourEntity(TourRequest tourRequest) {
        return TourEntity.builder()
                .name(tourRequest.getName())
                .description(tourRequest.getDescription())
                .price(tourRequest.getPrice())
                .startDate(tourRequest.getStartDate())
                .endDate(tourRequest.getEndDate())
                .build();
    }

    public TourResponse mapTourRequestToTourResponse(TourEntity tourEntity) {
        return TourResponse.builder()
                .id(tourEntity.getId())
                .name(tourEntity.getName())
                .description(tourEntity.getDescription())
                .price(tourEntity.getPrice())
                .endDate(tourEntity.getEndDate())
                .startDate(tourEntity.getStartDate())
                .build();
    }

    public void updateTourEntity(TourRequest tourRequest, TourEntity tourEntity) {
        if (tourRequest.getName() != null) tourEntity.setName(tourRequest.getName());
        if (tourRequest.getDescription() != null) tourEntity.setDescription(tourRequest.getDescription());
        if (tourRequest.getPrice() != null) tourEntity.setPrice(tourRequest.getPrice());
        if (tourRequest.getStartDate() != null) tourEntity.setStartDate(tourRequest.getStartDate());
        if (tourRequest.getEndDate() != null) tourEntity.setEndDate(tourRequest.getEndDate());
    }
}
