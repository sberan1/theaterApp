package cz.vse._it353.theater.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String title;
    @Min(1)
    Integer durationInMinutes;
    String rating;
    String genre;
    @UpdateTimestamp
    LocalDateTime updatedAt;
    @CreationTimestamp
    LocalDateTime createdAt;
    @OneToMany
    List<Projection> projections;
    public Movie(String id) {
        this.id = id;
    }
}
