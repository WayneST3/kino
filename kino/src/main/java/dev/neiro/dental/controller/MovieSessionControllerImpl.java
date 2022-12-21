package dev.neiro.dental.controller;

import dev.neiro.dental.controller.shared.MovieSessionController;
import dev.neiro.dental.entity.MovieSession;
import dev.neiro.dental.serializer.ApiException;
import dev.neiro.dental.service.MovieSessionService;
import dev.neiro.dental.util.PageableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import java.util.List;

@Component
@Path("/movie-sessions")
public class MovieSessionControllerImpl implements MovieSessionController {

    private final MovieSessionService movieSessionService;

    @Autowired
    public MovieSessionControllerImpl(MovieSessionService movieSessionService) {
        this.movieSessionService = movieSessionService;
    }

    @Override
    public MovieSession create(MovieSession movieSession) {
        return movieSessionService.create(movieSession);
    }

    @Override
    public MovieSession update(Long id, MovieSession movieSession) {
        if (id == null)
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "Id сессии фильма не задан");
        return movieSessionService.update(id, movieSession);
    }

    @Override
    public void delete(Long id) {
        movieSessionService.deleteById(id);
    }

    @Override
    public List<MovieSession> findAll(Integer pageNum, Integer pageSize, String sortField) {
        return movieSessionService.findAll(PageableUtil.generatePageable(pageNum, pageSize, sortField));
    }

    @Override
    public MovieSession findById(Long id) {
        return movieSessionService.findById(id);
    }
}
