package dev.neiro.dental.service;

import dev.neiro.dental.entity.MovieSession;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieSessionService {

    MovieSession create(MovieSession movieSession);

    MovieSession update(Long id, MovieSession movieSession);

    void deleteById(Long id);

    List<MovieSession> findAll(Pageable pageable);

    MovieSession findById(Long id);
}
