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
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "branch")
    @JsonBackReference
    List<Room> rooms = new ArrayList<>();
    @UpdateTimestamp
    LocalDateTime updatedAt;
    @CreationTimestamp
    LocalDateTime createdAt;
}
