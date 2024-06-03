package cz.vse._it353.theater.service;

import cz.vse._it353.theater.dto.CreateRoomDto;
import cz.vse._it353.theater.entity.Branch;
import cz.vse._it353.theater.repository.BranchRepository;
import cz.vse._it353.theater.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import cz.vse._it353.theater.entity.Room;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final BranchRepository branchRepository;

    public List<Room> findAll(String branchId) {
        if (branchId != null) {
            return roomRepository.findAllByBranchId(branchId);
        }
        return roomRepository.findAll();
    }

    public Room deleteById(String id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Room not found"));
        roomRepository.deleteById(id);
        return room;
    }

    public Room create(CreateRoomDto room) {
        Branch branch = branchRepository.findById(room.getBranchId()).orElseThrow(() -> new IllegalArgumentException("Branch not found"));
        Room roomEntity = new Room();
        roomEntity.setBranch(branch);
        roomEntity.setCapacity(room.getCapacity());
        roomEntity.setName(room.getName());
        return roomRepository.save(roomEntity);
    }

    public Room updateRoom(String id, CreateRoomDto room) {
        Room roomToUpdate = roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Room not found"));
        Branch branch = branchRepository.findById(room.getBranchId()).orElseThrow(() -> new IllegalArgumentException("Branch not found"));
        roomToUpdate.setBranch(branch);
        roomToUpdate.setCapacity(room.getCapacity());
        roomToUpdate.setName(room.getName());
        roomToUpdate.setVersion(room.getVersion());
        return roomRepository.save(roomToUpdate);
    }

    public Room findById(String id) {
        return roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Room not found"));
    }
}
