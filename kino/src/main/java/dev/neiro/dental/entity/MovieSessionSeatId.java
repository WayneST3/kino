package dev.neiro.dental.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Embeddable
@NoArgsConstructor
public class MovieSessionSeatId implements Serializable{

        @Column(name = "movie_session_id")
        private Long movieSessionId;
        @Column(name = "seat_id")
        private Long seatId;
}
