package cz.vse._it353.theater.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

/**
 * Entity representing a movie.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    /**
     * The unique identifier for the movie.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    /**
     * The title of the movie.
     */
    String title;

    /**
     * The duration of the movie in minutes. Must be at least 1 minute.
     */
    @Min(1)
    Integer durationInMinutes;

    /**
     * The rating of the movie.
     */
    String rating;

    /**
     * The genre of the movie.
     */
    String genre;

    /**
     * The URL of the cover image for the movie.
     */
    String coverImageUrl;

    /**
     * The version of the movie entity, used for optimistic locking.
     */
    @Version
    Long version;

    /**
     * The timestamp of when the movie was last updated.
     */
    @UpdateTimestamp
    LocalDateTime updatedAt;

    /**
     * The timestamp of when the movie was created.
     */
    @CreationTimestamp
    LocalDateTime createdAt;

    /**
     * The list of projections associated with the movie.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Projection> projections = new ArrayList<>();

    /**
     * Constructs a new Movie with the given ID.
     * @param id the unique identifier for the movie
     */
    public Movie(String id) {
        this.id = id;
    }
}