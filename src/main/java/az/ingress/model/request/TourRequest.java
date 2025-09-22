package az.ingress.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class TourRequest {

    @NotBlank(message = "validation.not.blank-tour-name")
    String name;

    @NotBlank(message = "validation.not.blank-tour-description")
    String description;

    @NotNull(message = "validation.not.null-tour-price")
    @Positive(message = "validation.positive-tour-price")
    BigDecimal price;

    @NotNull(message = "validation.not.null-tour-start-date")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = STRING)
    LocalDate startDate;

    @NotNull(message = "validation.not.null-tour-end-date")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = STRING)
    LocalDate endDate;
}