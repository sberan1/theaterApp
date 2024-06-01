package cz.vse._it353.theater.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectionDto {
    private LocalDateTime startTime;
    private String movieId;
    private String priceTypeId;
    private String roomId;
}