package cz.vse._it353.theater.service;

import cz.vse._it353.theater.entity.Movie;
import cz.vse._it353.theater.entity.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }
}
