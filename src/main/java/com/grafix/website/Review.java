package com.grafix.website;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String contact;

    @Column(columnDefinition = "TEXT")
    private String address;

    private String interest;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(name = "date", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public String getDate() {
        if (createdAt == null)
            return "Recent";
        return createdAt.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
    }
}
