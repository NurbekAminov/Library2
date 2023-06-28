package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class BookDto {
    private Integer id;
    private String title;
    private String author;
    private String category;
    private LocalDateTime publishDate;
    private LocalDateTime availableDay;
    private Boolean visible = true;
    private LocalDateTime createdDate;

    public BookDto() {
    }

    public BookDto(Integer id, String title, String author, String category, LocalDateTime publishDate, LocalDateTime availableDay, Boolean visible, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.publishDate = publishDate;
        this.availableDay = availableDay;
        this.visible = visible;
        this.createdDate = createdDate;
    }
}
