package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column
    private String author;
    @Column
    private String category;
    @Column
    private LocalDateTime publishDate;
    @Column
    private LocalDateTime availableDay;
    @Column
    private Boolean visible = true;
    @Column
    private LocalDateTime createdDate;

    public BookEntity() {
    }

    public BookEntity(String title, String author, String category, LocalDateTime publishDate, LocalDateTime availableDay, LocalDateTime createdDate) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.publishDate = publishDate;
        this.availableDay = availableDay;
        this.createdDate = createdDate;
    }
}
