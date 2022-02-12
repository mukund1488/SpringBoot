package sample.spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonalDetails {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long mobile;
    private String currentLocation;
    private String address;

}
