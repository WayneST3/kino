package dev.neiro.kino.service;

import dev.neiro.kino.entity.MovieSession;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieSessionService {

    MovieSession create(MovieSession movieSession);

    MovieSession update(Long id, MovieSession movieSession);

    void deleteById(Long id);

    List<MovieSession> findAll(Pageable pageable);

    MovieSession findById(Long id);
}
