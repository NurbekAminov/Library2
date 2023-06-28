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
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Integer studentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Integer bookId;
    @Column
    private LocalDateTime createdDate;
    @Column
    @Enumerated(EnumType.STRING)
    private StatusBook status;
    @Column
    private String note;

    public TakenBook() {
    }

    public TakenBook(Integer id, Integer studentId, Integer bookId, LocalDateTime createdDate, StatusBook status, String note) {
        this.id = id;
        this.studentId = studentId;
        this.bookId = bookId;
        this.createdDate = createdDate;
        this.status = status;
        this.note = note;
    }
}
