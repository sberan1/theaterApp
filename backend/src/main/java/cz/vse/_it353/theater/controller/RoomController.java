package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.dto.CreateRoomDto;
import cz.vse._it353.theater.entity.Room;
import cz.vse._it353.theater.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for the room-related endpoints.
 */
@RestController
@CrossOrigin
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;

    /**
     * Handles getting all rooms, optionally filtered by branch ID.
     * @param branchId the ID of the branch to filter rooms, optional
     * @return a list of rooms, possibly filtered by branch ID
     */
    @GetMapping("/rooms")
    public List<Room> getRooms(@RequestParam(value = "branchId", required = false) String branchId) {
        return roomService.findAll(branchId);
    }

    /**
     * Handles adding a new room.
     * @param room the room DTO containing room details
     */
    @PostMapping("/admin/room")
    public void addRoom(@RequestBody CreateRoomDto room) {
        roomService.create(room);
    }

    /**
     * Handles deleting a room by its ID.
     * @param id the ID of the room to be deleted
     * @return the deleted room
     */
    @DeleteMapping("/admin/room/{id}")
    public Room deleteRoom(@PathVariable String id) {
        return roomService.deleteById(id);
    }

    /**
     * Handles updating a room.
     * @param id the ID of the room to be updated
     * @param room the room DTO containing updated room details
     * @return the updated room
     */
    @PutMapping("/admin/room/{id}")
    public Room updateRoom(@PathVariable String id, @RequestBody CreateRoomDto room) {
        return roomService.updateRoom(id, room);
    }

    /**
     * Handles getting a room by its ID.
     * @param id the ID of the room
     * @return the room
     */
    @GetMapping("/room/{id}")
    public Room getRoomById(@PathVariable String id) {
        return roomService.findById(id);
    }
}