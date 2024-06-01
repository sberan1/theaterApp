package cz.vse._it353.theater.service;

import cz.vse._it353.theater.dto.ProjectionDto;
import cz.vse._it353.theater.repository.MovieRepository;
import cz.vse._it353.theater.entity.Projection;
import cz.vse._it353.theater.repository.PriceRepository;
import cz.vse._it353.theater.repository.ProjectionRepository;
import cz.vse._it353.theater.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectionService {
    private final ProjectionRepository projectionRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final PriceRepository priceRepository;

    public List<Projection> findByBranchId(String branchId) {
        return projectionRepository.findByRoomBranchId(branchId);
    }
    public List<Projection> findByMovieId(String movieId) {
        return projectionRepository.findByMovieId(movieId);
    }
    public List<Projection> findAll() {
        return projectionRepository.findAll();
    }
    public List<Projection> getProjections(String filterType, String filterValue) {
        if ("movie".equalsIgnoreCase(filterType)) {
            return findByMovieId(filterValue);
        } else if ("branch".equalsIgnoreCase(filterType)) {
            return findByBranchId(filterValue);
        } else {
            return findAll();
        }
    }

    public Projection create(ProjectionDto projectionDto) {
        Projection projection = new Projection();
        projection.setStartTime(projectionDto.getStartTime());
        projection.setMovie(movieRepository.findById(projectionDto.getMovieId()).orElseThrow());
        projection.setRoom(roomRepository.findById(projectionDto.getRoomId()).orElseThrow());
        projection.setPriceType(priceRepository.findById(projectionDto.getPriceTypeId()).orElseThrow());
        return projectionRepository.save(projection);
    }
}
