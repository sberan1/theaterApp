package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.dto.CreateRoomDto;
import cz.vse._it353.theater.entity.Room;
import cz.vse._it353.theater.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/rooms")
    public List<Room> getRooms(@RequestParam(value = "branchId", required = false) String branchId){
        return roomService.findAll(branchId);
    }
    @PostMapping("/admin/room")
    public void addRoom(@RequestBody CreateRoomDto room){
        roomService.create(room);
    }

    @DeleteMapping("/admin/room/{id}")
    public Room deleteRoom(@PathVariable String id){
        return roomService.deleteById(id);
    }

    @PutMapping("/admin/room/{id}")
    public Room updateRoom(@PathVariable String id, @RequestBody CreateRoomDto room){
        return roomService.updateRoom(id, room);
    }

    @GetMapping("/room/{id}")
    public Room getRoomById(@PathVariable String id){
        return roomService.findById(id);
    }
}
