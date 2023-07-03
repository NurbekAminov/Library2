package org.example.mapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.enums.StatusProfile;

@Getter
@Setter
@ToString
public class StudentByBookMapper {
    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String phone;
    private StatusProfile status;
    private Long takenBookCount;
    private Long bookOnHand;

    public StudentByBookMapper(Integer id, String name, String surname, String login, String phone, String status, Long takenBookCount, Long bookOnHand) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.phone = phone;
        this.status = StatusProfile.valueOf(status);
        this.takenBookCount = takenBookCount;
        this.bookOnHand = bookOnHand;
    }

    public StudentByBookMapper() {
    }
}
