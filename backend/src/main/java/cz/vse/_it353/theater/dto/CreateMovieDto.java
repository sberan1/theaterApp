package cz.vse._it353.theater.dto;

import lombok.Data;

/**
 * Data Transfer Object for creating a new movie.
 */
@Data
public class CreateMovieDto {
    /**
     * The title of the movie.
     */
    private String title;

    /**
     * The URL of the cover image for the movie.
     */
    private String coverImageUrl;

    /**
     * The duration of the movie in minutes.
     */
    private int durationInMinutes;

    /**
     * The genre of the movie.
     */
    private String genre;

    /**
     * The rating of the movie.
     */
    private String rating;

    /**
     * The version of the movie.
     */
    private Long version;
}