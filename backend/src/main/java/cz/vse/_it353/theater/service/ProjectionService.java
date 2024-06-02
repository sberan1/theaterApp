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
            projections = projectionRepository.findByMovieId(filterValue, pageable);
        } else if ("branch".equalsIgnoreCase(filterType)) {
            projections = projectionRepository.findByRoomBranchId(filterValue, pageable);
        } else {
            projections = projectionRepository.findAll(pageable).stream().toList();
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
}
