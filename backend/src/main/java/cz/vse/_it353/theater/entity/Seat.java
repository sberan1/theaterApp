package cz.vse._it353.theater.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
 * Entity representing a seat.
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"room_id", "room_row", "seat_number"}))
public class Seat {
    /**
     * The unique identifier for the seat.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    /**
     * The room to which the seat belongs.
     */
    @ManyToOne()
    @JoinColumn(name = "room_id")
    @JsonBackReference
    Room room;

    /**
     * The row in which the seat is located.
     */
    String roomRow;

    /**
     * The number of the seat.
     */
    Integer seatNumber;

    /**
     * The list of reservations for the seat.
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "seats")
    @JsonBackReference
    List<Reservation> reservations = new ArrayList<>();

    /**
     * The timestamp of when the seat was last updated.
     */
    @UpdateTimestamp
    LocalDateTime updatedAt;

    /**
     * The timestamp of when the seat was created.
     */
    @CreationTimestamp
    LocalDateTime createdAt;
}