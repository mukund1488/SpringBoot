package sample.spring.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "PERSONAL_DETAILS")
public class PersonalDetails {
    @Id
    @Column(name="ID")
    private String id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "CURRENT_LOCATION")
    private String currentLocation;
    @Column(name = "MOBILE")
    private Long mobile;
    @Column(name = "EMAIL")
    private String email;
}
