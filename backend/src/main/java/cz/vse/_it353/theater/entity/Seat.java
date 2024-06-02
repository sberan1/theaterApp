package cz.vse._it353.theater.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"room_id", "room_row", "seat_number"}))
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @ManyToOne
    Room room;
    String roomRow;
    Integer seatNumber;
    @UpdateTimestamp
    LocalDateTime updatedAt;
    @CreationTimestamp
    LocalDateTime createdAt;
}
