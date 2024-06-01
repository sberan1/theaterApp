package cz.vse._it353.theater.service;

import cz.vse._it353.theater.dto.ProjectionDto;
import cz.vse._it353.theater.entity.Movie;
import cz.vse._it353.theater.entity.Price;
import cz.vse._it353.theater.entity.Room;
import cz.vse._it353.theater.repository.MovieRepository;
import cz.vse._it353.theater.entity.Projection;
import cz.vse._it353.theater.repository.PriceRepository;
import cz.vse._it353.theater.repository.ProjectionRepository;
import cz.vse._it353.theater.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectionService {
    private static final Logger logger = LoggerFactory.getLogger(ProjectionService.class);
    private final ProjectionRepository projectionRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final PriceRepository priceRepository;

    public List<Projection> findByBranchId(String branchId) {
        return projectionRepository.findByRoomBranchId(branchId);
    }
    public List<Projection> findByMovieId(String movieId) {
        List<Projection> projections = projectionRepository.findByMovieId(movieId);
        logger.info("Projections fetched for movieId {}: {}", movieId, projections);
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
        Movie movie = movieRepository.findById(projectionDto.getMovieId()).orElse(null);
        Room room = roomRepository.findById(projectionDto.getRoomId()).orElse(null);
        Price price = priceRepository.findById(projectionDto.getPriceTypeId()).orElse(null);

        if (movie == null || room == null || price == null) {
            throw new IllegalArgumentException("Movie, Room, or Price not found");
        }

        Projection projection = new Projection();
        projection.setStartTime(projectionDto.getStartTime());
        projection.setMovie(movie);
        projection.setRoom(room);
        projection.setPriceType(price);

        return projectionRepository.save(projection);
    }
}
