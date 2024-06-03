package cz.vse._it353.theater.service;

import cz.vse._it353.theater.dto.CreateMovieDto;
import cz.vse._it353.theater.entity.Movie;
import cz.vse._it353.theater.repository.MovieRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<Movie> findAll(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return movieRepository.findAll(pageable).stream().toList();
    }

    public Movie deleteById(String id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        movieRepository.deleteById(id);
        return movie;
    }

    public Movie findById(String id) {
        return movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Movie not found"));
    }

    public Movie updateMovie(String id, CreateMovieDto movie) {
        Movie movieToUpdate = movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        movieToUpdate.setTitle(movie.getTitle());
        movieToUpdate.setGenre(movie.getGenre());
        movieToUpdate.setDurationInMinutes(movie.getDurationInMinutes());
        movieToUpdate.setRating(movie.getRating());
        movieToUpdate.setCoverImageUrl(movie.getCoverImageUrl());
        movieToUpdate.setVersion(movie.getVersion());
        return movieRepository.save(movieToUpdate);
    }
}
