package dev.neiro.kino.repository;

import dev.neiro.kino.entity.MovieSessionSeat;
import dev.neiro.kino.entity.MovieSessionSeatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieSessionSeatRepository extends JpaRepository<MovieSessionSeat, MovieSessionSeatId> {
}
