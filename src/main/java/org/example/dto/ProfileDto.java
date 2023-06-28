package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.Role;
import org.example.enums.StatusProfile;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProfileDto {
    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String phone;
    private StatusProfile statusProfile;
    private Role role;
    private LocalDateTime createdDate;

    public ProfileDto() {
    }

    public ProfileDto(String name, String surname, String login, String password, String phone) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.phone = phone;

    }
}
