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
    public List<Room> getRooms(@RequestParam("branchId") String branchId){
        return roomService.findAll(branchId);
    }
    @PostMapping("/admin/room")
    public void addRoom(@RequestBody CreateRoomDto room){
        roomService.create(room);
    }
}
