package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.Role;
import org.example.enums.StatusProfile;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Profiles")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(unique = true)
    private String phone;
    @Column
    @Enumerated(EnumType.STRING)
    private StatusProfile statusProfile;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column
    private LocalDateTime createdDate;

    public ProfileEntity() {
    }

    public ProfileEntity(String name, String surname, String login, String password, String phone) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.phone = phone;
    }
}
