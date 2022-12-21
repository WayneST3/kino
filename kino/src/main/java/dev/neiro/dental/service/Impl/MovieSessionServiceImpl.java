package dev.neiro.dental.service.Impl;

import dev.neiro.dental.entity.MovieSession;
import dev.neiro.dental.repository.HallRepository;
import dev.neiro.dental.repository.MovieRepository;
import dev.neiro.dental.repository.MovieSessionRepository;
import dev.neiro.dental.serializer.ApiException;
import dev.neiro.dental.service.MovieSessionService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {

    private final MovieSessionRepository movieSessionRepository;
    private final HallRepository hallRepository;
    private final MovieRepository movieRepository;

    public MovieSessionServiceImpl(MovieSessionRepository movieSessionRepository, HallRepository hallRepository, MovieRepository movieRepository) {
        this.movieSessionRepository = movieSessionRepository;
        this.hallRepository = hallRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional
    @Override
    public MovieSession create(MovieSession movieSession) {
        check(movieSession);
        movieSession.setId(null);
        return movieSessionRepository.save(movieSession);
    }

    @Transactional
    @Override
    public MovieSession update(Long id, MovieSession movieSession) {
        if (!movieRepository.existsById(id))
            throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "Сессия фильма с таким id не найдена");
        check(movieSession);
        movieSession.setId(id);
        return movieSessionRepository.save(movieSession);
    }

    private void check(MovieSession movieSession) {
        if (movieSession.getMovieId() == null)
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "Id фильма не задан");
        if (movieSession.getHallId() == null)
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "Id кинозала не задан");
        if (!movieRepository.existsById(movieSession.getMovieId()))
            throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "Фильм с таким id не найден");
        if (!hallRepository.existsById(movieSession.getHallId()))
            throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "Кинозал с таким id не найден");
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if (!movieRepository.existsById(id))
            throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "Сессия фильма с таким id не найдена");
        movieSessionRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MovieSession> findAll(Pageable pageable) {
        return (pageable == null) ? movieSessionRepository.findAll() : movieSessionRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    @Override
    public MovieSession findById(Long id) {
        Optional<MovieSession> movieSession = movieSessionRepository.findById(id);
        if (movieSession.isEmpty())
            throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "Сессия фильма с таким id не найдена");
        return movieSession.get();
    }
}
