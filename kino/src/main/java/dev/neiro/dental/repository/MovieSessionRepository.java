package dev.neiro.dental.repository;

import dev.neiro.dental.entity.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieSessionRepository extends JpaRepository<MovieSession, Long> {

}
