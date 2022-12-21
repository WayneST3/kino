package dev.neiro.kino.repository;

import dev.neiro.kino.entity.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieSessionRepository extends JpaRepository<MovieSession, Long> {

}
