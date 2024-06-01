package cz.vse._it353.theater.service;

import cz.vse._it353.theater.entity.MovieRepository;
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
    public Projection create(Projection projection) {
        projection.setMovie(movieRepository.findById(projection.getMovie().getId()).orElse(null));
        projection.setRoom(roomRepository.findById(projection.getRoom().getId()).orElse(null));
        projection.setPriceType(priceRepository.findById(projection.getPriceType().getId()).orElse(null));
        return projectionRepository.save(projection);
    }
}