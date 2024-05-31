package cz.vse._it353.theater.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(unique = true)
    String name;
    String address;
    @OneToMany
    List<Room> rooms;
}
