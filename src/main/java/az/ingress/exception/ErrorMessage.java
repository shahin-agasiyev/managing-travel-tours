package az.ingress.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

    UNEXPECTED_ERROR("Unexpected error occurred"),
    TOUR_NOT_FOUND("Tour with id: %s not found"),
    DESTINATION_NOT_FOUND("Destination with id: %s not found"),
    GUIDE_NOT_FOUND("Guide with id: %s not found"),
    PASSPORT_NOT_FOUND("Passport with id: %s not found"),
    ILLEGAL_START_OR_END_DATE("Start date and end date must be in the future"),
    START_DATE_AFTER_END_DATE("Start date cannot be after end date"),
    ILLEGAL_VISIT_DATE("Destination visit date is illegal"),
    TRAVELER_NOT_FOUND("Traveler with id: %s not found"),
    PASSPORT_ALREADY_HAS("Guide already has a passport"),
    GUIDE_NOT_AVAILABLE("Guide is not available"),
    TRAVELER_NOT_AVAILABLE("Traveler is not available"),;

    private final String message;
}