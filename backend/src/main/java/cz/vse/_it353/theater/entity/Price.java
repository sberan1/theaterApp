package cz.vse._it353.theater.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing a price.
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    /**
     * The unique identifier for the price.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    /**
     * The name of the price type.
     */
    String name;

    /**
     * The value of the price.
     */
    BigDecimal value;

    /**
     * The version of the price entity, used for optimistic locking.
     */
    @Version
    Long version;

    /**
     * The timestamp of when the price was last updated.
     */
    @UpdateTimestamp
    LocalDateTime updatedAt;

    /**
     * The timestamp of when the price was created.
     */
    @CreationTimestamp
    LocalDateTime createdAt;

    /**
     * Constructs a new Price with the given ID.
     *
     * @param id the unique identifier for the price
     */
    public Price(String id) {
        this.id = id;
    }
}