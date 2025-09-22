package az.ingress.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class TravelerRequest {

    @NotBlank(message = "validation.not.blank-traveler-first-name")
    String firstName;

    @NotBlank(message = "validation.not.blank-traveler-last-name")
    String lastName;

    @NotBlank(message = "validation.not.blank-email")
    @Email(message = "validation.invalid-traveler-email")
    String email;
}
