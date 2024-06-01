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

    public Room create(CreateRoomDto room) {
        Branch branch = branchRepository.findById(room.getBranchId()).orElseThrow(() -> new IllegalArgumentException("Branch not found"));
        Room roomEntity = new Room();
        roomEntity.setBranch(branch);
        roomEntity.setCapacity(room.getCapacity());
        roomEntity.setName(room.getName());
        return roomRepository.save(roomEntity);
    }
}
