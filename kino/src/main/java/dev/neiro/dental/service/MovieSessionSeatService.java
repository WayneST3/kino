package dev.neiro.dental.service;

import dev.neiro.dental.entity.MovieSessionSeatId;

public interface MovieSessionSeatService {

    void create(MovieSessionSeatId id);

    void deleteById(MovieSessionSeatId id);
}
