package cz.vse._it353.theater.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    String coverImageUrl;
    @UpdateTimestamp
    LocalDateTime updatedAt;
    @CreationTimestamp
    LocalDateTime createdAt;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Projection> projections = new ArrayList<>();
    public Movie(String id) {
        this.id = id;
    }
}
