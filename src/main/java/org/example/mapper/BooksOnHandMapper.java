package org.example.mapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
public class BooksOnHandMapper {
    private Integer id;
    private String title;
    private String author;
    private String categoryName;
    private LocalDateTime takenDate;
    private Integer deadLine;
    private LocalDateTime deadLineDate;

    public BooksOnHandMapper(Integer id, String title, String author, String categoryName, LocalDateTime takenDate, Integer deadLine) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categoryName = categoryName;
        this.takenDate = takenDate;
        this.deadLine = deadLine;
    }

    public BooksOnHandMapper(Integer id, String title, String author, String categoryName, LocalDateTime takenDate, LocalDateTime deadLineDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categoryName = categoryName;
        this.takenDate = takenDate;
        this.deadLineDate = deadLineDate;
    }
}
