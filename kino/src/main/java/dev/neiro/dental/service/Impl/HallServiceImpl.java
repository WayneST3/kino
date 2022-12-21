package dev.neiro.dental.service.Impl;

import dev.neiro.dental.entity.Hall;
import dev.neiro.dental.entity.Movie;
import dev.neiro.dental.entity.MovieSession;
import dev.neiro.dental.entity.MovieSessionSeatId;
import dev.neiro.dental.repository.*;
import dev.neiro.dental.serializer.ApiException;
import dev.neiro.dental.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;
    private final SeatRepository seatRepository;
    private final MovieSessionRepository movieSessionRepository;
    private final MovieRepository movieRepository;
    private final MovieSessionSeatRepository movieSessionSeatRepository;

    @Autowired
    public HallServiceImpl(HallRepository hallRepository, SeatRepository seatRepository, MovieSessionRepository movieSessionRepository, MovieRepository movieRepository, MovieSessionSeatRepository movieSessionSeatRepository) {
        this.hallRepository = hallRepository;
        this.seatRepository = seatRepository;
        this.movieSessionRepository = movieSessionRepository;
        this.movieRepository = movieRepository;
        this.movieSessionSeatRepository = movieSessionSeatRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Hall> findAll(Pageable pageable) {
        return (pageable == null) ? hallRepository.findAll() : hallRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    @Override
    public Hall findById(Long id, Long sessionId) {
        Optional<Hall> hall = hallRepository.findById(id);
        if (hall.isEmpty())
            throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "Зал с таким id не найден");
        Optional<MovieSession> movieSession = movieSessionRepository.findById(sessionId);
        if (movieSession.isEmpty())
            throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "Киносеанс с таким id не найден");
        Optional<Movie> movie = movieRepository.findById(movieSession.get().getMovieId());
        if (movie.isEmpty())
            throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "Фильм с таким id не найден");
        hall.get().getRows().forEach(row -> {
            row.setPrice(row.getPriceKoef() * movie.get().getMinPrice());
            row.getSeats().forEach(seat -> {
                seat.setSeatIsTaken(movieSessionSeatRepository.existsById(new MovieSessionSeatId(sessionId, seat.getId())));
            });
        });
        return hall.get();
    }
}
