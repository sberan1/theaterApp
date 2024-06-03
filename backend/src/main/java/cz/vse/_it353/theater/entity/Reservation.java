package cz.vse._it353.theater.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
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
 * Entity representing a reservation.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    /**
     * The unique identifier for the reservation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    /**
     * The user who made the reservation.
     */
    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    AppUser user;

    /**
     * The projection for which the reservation is made.
     */
    @ManyToOne()
    @JoinColumn(name = "projection_id")
    @JsonManagedReference
    Projection projection;

    /**
     * The list of seats reserved.
     */
    @ManyToMany
    @JoinTable(
            name = "reservation_seats",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "seats_id")
    )
    @JsonManagedReference
    List<Seat> seats = new ArrayList<>();

    /**
     * Indicates whether the reservation is paid.
     */
    boolean paid;

    /**
     * The discount applied to the reservation. Must be between 0 and 100 inclusive.
     */
    @Min(0)
    @Max(100)
    Integer discount;

    /**
     * The version of the reservation entity, used for optimistic locking.
     */
    @Version
    private Long version;

    /**
     * The timestamp of when the reservation was last updated.
     */
    @UpdateTimestamp
    LocalDateTime updatedAt;

    /**
     * The timestamp of when the reservation was created.
     */
    @CreationTimestamp
    LocalDateTime createdAt;

    /**
     * Adds a seat to the reservation.
     * @param seat the seat to add
     */
    public void addSeat(Seat seat) {
        seats.add(seat);
    }
}