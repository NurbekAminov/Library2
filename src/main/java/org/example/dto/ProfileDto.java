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

    public ProfileDto(Integer id, String name, String surname, String login, String password, String phone, StatusProfile statusProfile, Role role, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.statusProfile = statusProfile;
        this.role = role;
        this.createdDate = createdDate;
    }

}
