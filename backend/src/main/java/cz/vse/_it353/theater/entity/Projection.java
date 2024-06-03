package cz.vse._it353.theater.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
 * Entity representing a projection.
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Projection {
    /**
     * The unique identifier for the projection.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    /**
     * The start time of the projection.
     */
    LocalDateTime startTime;

    /**
     * The price type associated with the projection.
     */
    @ManyToOne()
    @JoinColumn(name = "price_type_id")
    @JsonManagedReference
    Price priceType;

    /**
     * The movie associated with the projection.
     */
    @ManyToOne()
    @JoinColumn(name = "movie_id")
    @JsonManagedReference
    Movie movie;

    /**
     * The room where the projection takes place.
     */
    @ManyToOne()
    @JoinColumn(name = "room_id")
    @JsonManagedReference
    Room room;

    /**
     * The list of reservations for the projection.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "projection", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Reservation> reservations = new ArrayList<>();

    /**
     * The version of the projection entity, used for optimistic locking.
     */
    @Version
    private Long version;

    /**
     * The timestamp of when the projection was last updated.
     */
    @UpdateTimestamp
    LocalDateTime updatedAt;

    /**
     * The timestamp of when the projection was created.
     */
    @CreationTimestamp
    LocalDateTime createdAt;
}