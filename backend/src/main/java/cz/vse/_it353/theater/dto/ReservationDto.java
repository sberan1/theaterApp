package cz.vse._it353.theater.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object for creating or updating a reservation.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    /**
     * The username of the person making the reservation.
     */
    private String username;

    /**
     * The ID of the projection for which the reservation is made.
     */
    private String projectionId;

    /**
     * Indicates whether the reservation is paid.
     */
    private boolean paid;

    /**
     * The discount applied to the reservation.
     */
    private Integer discount;

    /**
     * The list of seat IDs reserved.
     */
    private List<String> seats;
}