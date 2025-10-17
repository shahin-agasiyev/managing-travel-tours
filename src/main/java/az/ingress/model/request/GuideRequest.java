package az.ingress.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuideRequest {

    @NotBlank(message = "validation.not.blank-guide-name")
    String name;

    @NotBlank(message = "validation.not.blank-guide-email")
    @Email(message = "validation.invalid-guide-email")
    String email;

    @NotBlank(message = "validation.not.blank-guide-phone-number")
    @Pattern(regexp = "^\\+994(50|51|55|70|77|99)\\d{7}$", message = "validation.invalid-guide-phone-number")
    String phoneNumber;
}
