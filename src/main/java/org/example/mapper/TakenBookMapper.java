package org.example.mapper;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TakenBookMapper {
    private Integer id;
    private String title;
    private String author;
    private String categoryName;
    private LocalDateTime takenDate;
    private LocalDateTime deadLineDate;
    private String studentName;
    private String studentSurname;
    private String phone;
    private Integer deadLine;

    public TakenBookMapper(Integer id, String title, String author, String categoryName, LocalDateTime takenDate, Integer deadLineDate, String studentName, String studentSurname, String phone) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categoryName = categoryName;
        this.takenDate = takenDate;
        this.deadLine = deadLineDate;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "TakenBookMapper{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", takenDate=" + takenDate +
                ", deadLineDate=" + deadLineDate +
                ", studentName='" + studentName + '\'' +
                ", studentSurname='" + studentSurname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
