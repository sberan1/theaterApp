package cz.vse._it353.theater.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for creating a new room.
 */
@Getter
@Setter
@AllArgsConstructor
public class CreateRoomDto {
    /**
     * The name of the room.
     */
    String name;

    /**
     * The capacity of the room.
     */
    int capacity;

    /**
     * The ID of the branch to which the room belongs.
     */
    String branchId;

    /**
     * The version of the room.
     */
    Long version;
}