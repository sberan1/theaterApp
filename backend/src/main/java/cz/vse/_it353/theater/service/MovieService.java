package cz.vse._it353.theater.service;

import cz.vse._it353.theater.dto.CreateMovieDto;
import cz.vse._it353.theater.entity.Movie;
import cz.vse._it353.theater.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie create(CreateMovieDto movie) {
        Movie movie1 = new Movie();
        movie1.setTitle(movie.getTitle());
        movie1.setGenre(movie.getGenre());
        movie1.setDurationInMinutes(movie.getDurationInMinutes());
        movie1.setRating(movie.getRating());
        movie1.setCoverImageUrl(movie.getCoverImageUrl());
        return movieRepository.save(movie1);
    }
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
}
