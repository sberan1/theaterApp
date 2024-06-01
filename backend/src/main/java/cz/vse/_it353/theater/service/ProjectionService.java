package cz.vse._it353.theater.service;

import cz.vse._it353.theater.entity.Projection;
import cz.vse._it353.theater.repository.ProjectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectionService {
    private final ProjectionRepository projectionRepository;

    public List<Projection> findByBranchId(String branchId) {
        return projectionRepository.findByRoomBranchId(branchId);
    }
    public Projection create(Projection projection) {
        return projectionRepository.save(projection);
    }
}