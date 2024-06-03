package cz.vse._it353.theater.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for creating or updating a projection.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectionDto {
    /**
     * The start time of the projection.
     */
    private LocalDateTime startTime;

    /**
     * The ID of the movie associated with the projection.
     */
    private String movieId;

    /**
     * The ID of the price type associated with the projection.
     */
    private String priceTypeId;

    /**
     * The ID of the room where the projection will take place.
     */
    private String roomId;

    /**
     * The version of the projection.
     */
    private Long version;
}