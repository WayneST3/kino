package dev.neiro.dental.controller;

import dev.neiro.dental.controller.shared.MovieController;
import dev.neiro.dental.entity.Movie;
import dev.neiro.dental.serializer.ApiException;
import dev.neiro.dental.service.MovieService;
import dev.neiro.dental.util.PageableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import java.util.List;

@Component
@Path("/movies")
public class MovieControllerImpl implements MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieControllerImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public Movie create(Movie movie) {
        return movieService.create(movie);
    }

    @Override
    public Movie update(Long id, Movie movie) {
        if (id == null)
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "Id фильма не задан");
        return movieService.update(id, movie);
    }

    @Override
    public void delete(Long id) {
        movieService.deleteById(id);
    }

    @Override
    public List<Movie> findAll(Integer pageNum, Integer pageSize, String sortField) {
        return movieService.findAll(PageableUtil.generatePageable(pageNum, pageSize, sortField));
    }

    @Override
    public Movie findById(Long id) {
        return movieService.findById(id);
    }
}
