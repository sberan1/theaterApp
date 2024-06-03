package cz.vse._it353.theater.service;

import cz.vse._it353.theater.dto.ProjectionDto;
import cz.vse._it353.theater.repository.MovieRepository;
import cz.vse._it353.theater.entity.Projection;
import cz.vse._it353.theater.repository.PriceRepository;
import cz.vse._it353.theater.repository.ProjectionRepository;
import cz.vse._it353.theater.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Projection entities.
 */
@Service
@AllArgsConstructor
public class ProjectionService {
    private final ProjectionRepository projectionRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final PriceRepository priceRepository;

    /**
     * Finds all existing projections.
     * @param filterType filter type
     * @param filterValue filter value
     * @param sortBy sorted by
     * @param page the pagination information
     * @param limit the limit of pagination
     * @return a list of projections
     */
    public List<Projection> findAll(String filterType, String filterValue, String sortBy, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortBy).ascending());
        List<Projection> projections;
        if ("movie".equalsIgnoreCase(filterType)) {
            projections = projectionRepository.findByMovieId(filterValue, pageable);
        } else if ("branch".equalsIgnoreCase(filterType)) {
            projections = projectionRepository.findByRoomBranchId(filterValue, pageable);
        } else {
            projections = projectionRepository.findAll(pageable).stream().toList();
        }
        return projections;
    }

    /**
     * Find a list of projections by branch ID.
     * @param branchId branch ID
     * @return a list of projections
     */
    public List<Projection> findByBranchId(String branchId) {
        return projectionRepository.findByRoomBranchId(branchId);
    }

    /**
     * Creates a new projection.
     * @param projectionDto the DTO containing projection details
     * @return the created projection
     */
    public Projection create(ProjectionDto projectionDto) {
        Projection projection = new Projection();
        projection.setStartTime(projectionDto.getStartTime());
        projection.setMovie(movieRepository.findById(projectionDto.getMovieId()).orElseThrow());
        projection.setRoom(roomRepository.findById(projectionDto.getRoomId()).orElseThrow());
        projection.setPriceType(priceRepository.findById(projectionDto.getPriceTypeId()).orElseThrow());
        return projectionRepository.save(projection);
    }

    /**
     * Finds projection by ID.
     * @param id projection ID
     * @return projection if it is found
     */
    public Optional<Projection> findById(String id) {
        return projectionRepository.findById(id);
    }

    /**
     * Deletes an projection by ID.
     * @param id projection id
     */
    public void deleteById(String id) {
        projectionRepository.deleteById(id);
    }

    /**
     * Updates projection by ID.
     * @param id projection ID
     * @param projectionDto new projection DTO
     * @return list of all projections
     */
    public List<Projection> updateProjection(String id, ProjectionDto projectionDto) {
        Projection projection = projectionRepository.findById(id).orElseThrow();
        projection.setStartTime(projectionDto.getStartTime());
        projection.setMovie(movieRepository.findById(projectionDto.getMovieId()).orElseThrow());
        projection.setRoom(roomRepository.findById(projectionDto.getRoomId()).orElseThrow());
        projection.setPriceType(priceRepository.findById(projectionDto.getPriceTypeId()).orElseThrow());
        projection.setVersion(projectionDto.getVersion());
        projectionRepository.save(projection);
        return projectionRepository.findAll();
    }
}
