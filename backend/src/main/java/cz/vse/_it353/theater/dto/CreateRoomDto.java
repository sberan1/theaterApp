package cz.vse._it353.theater.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateRoomDto {
    String name;
    int capacity;
    String branchId;
}
