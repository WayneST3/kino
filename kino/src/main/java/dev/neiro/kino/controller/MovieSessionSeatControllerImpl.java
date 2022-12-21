package dev.neiro.kino.controller;

import dev.neiro.kino.controller.shared.MovieSessionSeatController;
import dev.neiro.kino.entity.MovieSessionSeatId;
import dev.neiro.kino.service.MovieSessionSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;

@Component
@Path("/movie-sessions-seats")
public class MovieSessionSeatControllerImpl implements MovieSessionSeatController {

    private final MovieSessionSeatService movieSessionSeatService;

    @Autowired
    public MovieSessionSeatControllerImpl(MovieSessionSeatService movieSessionSeatService) {
        this.movieSessionSeatService = movieSessionSeatService;
    }

    @Override
    public void create(MovieSessionSeatId id) {
        movieSessionSeatService.create(id);
    }

    @Override
    public void delete(MovieSessionSeatId id) {
        movieSessionSeatService.deleteById(id);
    }
}
