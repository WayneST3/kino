package dev.neiro.kino.service.Impl;

import dev.neiro.kino.entity.MovieSessionSeat;
import dev.neiro.kino.entity.MovieSessionSeatId;
import dev.neiro.kino.repository.MovieSessionRepository;
import dev.neiro.kino.repository.MovieSessionSeatRepository;
import dev.neiro.kino.repository.SeatRepository;
import dev.neiro.kino.serializer.ApiException;
import dev.neiro.kino.service.MovieSessionSeatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class MovieSessionSeatServiceImpl implements MovieSessionSeatService {

    private final MovieSessionSeatRepository movieSessionSeatRepository;
    private final MovieSessionRepository movieSessionRepository;
    private final SeatRepository seatRepository;

    public MovieSessionSeatServiceImpl(MovieSessionSeatRepository movieSessionSeatRepository, MovieSessionRepository movieSessionRepository, SeatRepository seatRepository) {
        this.movieSessionSeatRepository = movieSessionSeatRepository;
        this.movieSessionRepository = movieSessionRepository;
        this.seatRepository = seatRepository;
    }

    @Transactional
    @Override
    public void create(MovieSessionSeatId id) {
        check(id);
        movieSessionSeatRepository.save(new MovieSessionSeat(id));
    }

    @Transactional
    @Override
    public void deleteById(MovieSessionSeatId id) {
        check(id);
        movieSessionSeatRepository.deleteById(id);
    }

    private void check(MovieSessionSeatId id) {
        if (id.getMovieSessionId() == null)
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "Id киносеанса не задан");
        if (id.getSeatId() == null)
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "Id места не задан");
        if (!movieSessionRepository.existsById(id.getMovieSessionId()))
            throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "Киносеанс с таким id не найден");
        if (!seatRepository.existsById(id.getSeatId()))
            throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "Место с таким id не найдено");
    }
}
