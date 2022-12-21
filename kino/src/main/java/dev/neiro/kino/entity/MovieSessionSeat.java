package dev.neiro.kino.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(schema = "core", name = "movie_sessions_seats")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Schema(description = "Бронь места на киносеанс")
public class MovieSessionSeat implements Serializable {

    public MovieSessionSeat(MovieSessionSeatId movieSessionSeatId) {
        this.movieSessionSeatId = movieSessionSeatId;
    }

    @EmbeddedId
    @Schema(description = "Идентификаторы записи")
    private MovieSessionSeatId movieSessionSeatId;
}
