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
    @ManyToOne
    @JoinColumn (name = "category_id")
    private CategoryEntity category;
    @Column
    private LocalDateTime publishedDate;
    @Column
    private Integer availableDate;
    @Column
    private Boolean visible = true;
    @Column
    private LocalDateTime createdDate;

}
