package dev.neiro.kino.service;

import dev.neiro.kino.entity.Movie;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {

    Movie create(Movie movie);

    Movie update(Long id, Movie movie);

    void deleteById(Long id);

    List<Movie> findAll(Pageable pageable);

    Movie findById(Long id);
}
