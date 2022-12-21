package dev.neiro.kino.service;

import dev.neiro.kino.entity.MovieSessionSeatId;

public interface MovieSessionSeatService {

    void create(MovieSessionSeatId id);

    void deleteById(MovieSessionSeatId id);
}
