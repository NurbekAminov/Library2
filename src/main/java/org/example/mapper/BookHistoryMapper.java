package org.example.mapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BookHistoryMapper {
    private LocalDateTime takenDate;
    private LocalDateTime returnDate;
    private String studentName;
    private String studentSurname;
    private String phone;
    private String note;

    public BookHistoryMapper(LocalDateTime takenDate, LocalDateTime returnDate, String studentName, String studentSurname, String phone, String note) {
        this.takenDate = takenDate;
        this.returnDate = returnDate;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.phone = phone;
        this.note = note;
    }

    public BookHistoryMapper() {
    }
}
