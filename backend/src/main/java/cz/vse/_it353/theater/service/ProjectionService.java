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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectionService {
    private final ProjectionRepository projectionRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final PriceRepository priceRepository;

    public List<Projection> findAll(String filterType, String filterValue, String sortBy, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortBy).ascending());
        List<Projection> projections;
        if ("movie".equalsIgnoreCase(filterType)) {
            projections = projectionRepository.findByMovie_IdAndStartTimeGreaterThan(filterValue, LocalDateTime.now(), pageable);
        } else if ("branch".equalsIgnoreCase(filterType)) {
            projections = projectionRepository.findByRoomBranchIdAndStartTimeGreaterThan(filterValue, LocalDateTime.now(), pageable);
        } else {
            projections = projectionRepository.findByStartTimeGreaterThan(LocalDateTime.now(), pageable).stream().toList();
        }
        return projections;
    }

    public List<Projection> findByBranchId(String branchId) {
        return projectionRepository.findByRoomBranchId(branchId);
    }

    public Projection create(ProjectionDto projectionDto) {
        Projection projection = new Projection();
        projection.setStartTime(projectionDto.getStartTime());
        projection.setMovie(movieRepository.findById(projectionDto.getMovieId()).orElseThrow());
        projection.setRoom(roomRepository.findById(projectionDto.getRoomId()).orElseThrow());
        projection.setPriceType(priceRepository.findById(projectionDto.getPriceTypeId()).orElseThrow());
        return projectionRepository.save(projection);
    }

    public Optional<Projection> findById(String id) {
        return projectionRepository.findById(id);
    }

    public void deleteById(String id) {
        projectionRepository.deleteById(id);
    }

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
