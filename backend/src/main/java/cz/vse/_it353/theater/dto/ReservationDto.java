package cz.vse._it353.theater.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private String username;
    private String projectionId;
    private boolean paid;
    private Integer discount;
    private List<String> seats;
}