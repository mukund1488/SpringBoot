package sample.spring.model.modify;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ModifyPersonalDetailsRequest {
    private Long id;
    @NotBlank(message = "First name cannot be blank/null/empty")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank/null/empty")
    private String lastName;
    @NotBlank(message = "Email cannot be blank/null/empty")
    private String email;
    @NotBlank(message = "Mobile cannot be blank/null/empty")
    private String mobile;
    @NotBlank(message = "Address cannot be blank/null/empty")
    private String address;
    @NotBlank(message = "Current Location cannot be blank/null/empty")
    private String currentLocation;
}
