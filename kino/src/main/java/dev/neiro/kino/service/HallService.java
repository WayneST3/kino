package dev.neiro.kino.service;

import dev.neiro.kino.entity.Hall;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HallService {

    List<Hall> findAll(Pageable pageable);

    Hall findById(Long id, Long sessionId);
}
