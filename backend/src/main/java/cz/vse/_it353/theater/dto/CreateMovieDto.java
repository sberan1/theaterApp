package cz.vse._it353.theater.dto;

import lombok.Data;

@Data
public class CreateMovieDto {
    private String title;
    private String coverImageUrl;
    private int durationInMinutes;
    private String genre;
    private String rating;
    private Long version;
}
