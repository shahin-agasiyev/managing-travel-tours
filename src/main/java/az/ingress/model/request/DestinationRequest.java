package az.ingress.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class DestinationRequest {

    @NotBlank(message = "validation.not.blank-destination-location")
    String location;

    @NotBlank(message = "validation.not.blank-destination-description")
    String description;

    @NotNull(message = "validation.not.null-destination-visit-date")
    LocalDate visitDate;
}
