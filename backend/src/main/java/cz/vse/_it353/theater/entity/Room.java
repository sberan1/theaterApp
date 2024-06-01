package cz.vse._it353.theater.entity;

import jakarta.persistence.*;
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
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    Integer capacity;
    @ManyToOne
    Branch branch;
    @OneToMany
    List<Projection> projections;
    @OneToMany
    List<Seat> seats;
    @UpdateTimestamp
    LocalDateTime updatedAt;
    @CreationTimestamp
    LocalDateTime createdAt;
    public Room(String id) {
        this.id = id;
    }
}
