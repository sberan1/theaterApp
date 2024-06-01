package cz.vse._it353.theater.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private String userId;
    private String projectionId;
    private boolean paid;
    private Integer discount;
}