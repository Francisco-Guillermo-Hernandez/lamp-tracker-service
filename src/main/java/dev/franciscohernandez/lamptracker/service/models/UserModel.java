package dev.franciscohernandez.lamptracker.service.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("User")
public class UserModel {
    

    @Id
    private String id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String token;

    private String role;
    private String status;
}
