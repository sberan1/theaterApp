package cz.vse._it353.theater.controller;

import cz.vse._it353.theater.dto.CreateRoomDto;
import cz.vse._it353.theater.entity.Room;
import cz.vse._it353.theater.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/admin/room")
    public void addRoom(@RequestBody CreateRoomDto room) {
        roomService.create(room);
    }
}
