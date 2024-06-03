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
 * Entity representing a room.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    /**
     * The unique identifier for the room.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    /**
     * The name of the room.
     */
    String name;

    /**
     * The capacity of the room.
     */
    Integer capacity;

    /**
     * The branch to which the room belongs.
     */
    @ManyToOne()
    @JoinColumn(name = "branch_id")
    @JsonManagedReference
    Branch branch;

    /**
     * The list of projections in the room.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Projection> projections = new ArrayList<>();

    /**
     * The list of seats in the room.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Seat> seats;

    /**
     * The version of the room entity, used for optimistic locking.
     */
    @Version
    Long version;

    /**
     * The timestamp of when the room was last updated.
     */
    @UpdateTimestamp
    LocalDateTime updatedAt;

    /**
     * The timestamp of when the room was created.
     */
    @CreationTimestamp
    LocalDateTime createdAt;

    /**
     * Constructs a new Room with the given ID.
     * @param id the unique identifier for the room
     */
    public Room(String id) {
        this.id = id;
    }
}