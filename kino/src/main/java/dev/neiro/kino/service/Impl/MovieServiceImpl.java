package dev.neiro.kino.service.Impl;

import dev.neiro.kino.entity.Movie;
import dev.neiro.kino.repository.MovieRepository;
import dev.neiro.kino.serializer.ApiException;
import dev.neiro.kino.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie create(Movie movie) {
        movie.setId(null);
        if (!movie.getGenres().isEmpty()) {
            movie.setGenres(null);
        }
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Long id, Movie movie) {
        if (!movieRepository.existsById(id))
            throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "Фильм с таким id не найден");
        movie.setId(id);
        if (!movie.getGenres().isEmpty()) {
            movie.getGenres().forEach(genre -> genre.setMovieId(id));
        }
        return movieRepository.save(movie);
    }

    @Override
    public void deleteById(Long id) {
        if (!movieRepository.existsById(id))
            throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "Фильм с таким id не найден");
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> findAll(Pageable pageable) {
        return (pageable == null) ? movieRepository.findAll() : movieRepository.findAll(pageable).getContent();
    }

    @Override
    public Movie findById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty())
            throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "Фильм с таким id не найден");
        return movie.get();
    }
}
