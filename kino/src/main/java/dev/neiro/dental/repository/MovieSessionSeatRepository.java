package dev.neiro.dental.repository;

import dev.neiro.dental.entity.MovieSessionSeat;
import dev.neiro.dental.entity.MovieSessionSeatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieSessionSeatRepository extends JpaRepository<MovieSessionSeat, MovieSessionSeatId> {
}
