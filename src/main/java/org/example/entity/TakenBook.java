package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.StatusBook;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TakenBooks")
public class TakenBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id",nullable = false)
    private ProfileEntity profileEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookEntity bookEntity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "taken_date")
    private LocalDateTime takenDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusBook status;

    @Column(name = "note")
    private String note;

}
