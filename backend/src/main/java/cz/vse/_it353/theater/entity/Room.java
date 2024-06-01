package cz.vse._it353.theater.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    public Room(String id) {
        this.id = id;
    }
}
