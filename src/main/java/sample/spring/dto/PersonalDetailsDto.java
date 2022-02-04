package sample.spring.dto;

import lombok.Data;

@Data
public class PersonalDetailsDto {
    private String firstName;
    private String lastName;
    private String email;
    private Long mobile;
    private String currentLocation;
    private String address;

}
