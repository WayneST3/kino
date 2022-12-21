CREATE SEQUENCE core.movie_sessions_id_seq start 1;

CREATE TABLE IF NOT EXISTS core.movie_sessions
(
    id           bigint NOT NULL DEFAULT nextval('core.movie_sessions_id_seq'::regclass),
    movie_id     bigint NOT NULL,
    hall_id      bigint NOT NULL,
    start_time   timestamp without time zone,
    end_time     timestamp without time zone,
    session_date timestamp without time zone,
    CONSTRAINT movie_sessions_pkey PRIMARY KEY (id),
    CONSTRAINT movie_sessions_movie_id_fkey FOREIGN KEY (movie_id)
        REFERENCES core.movies (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT movie_sessions_hall_id_fkey FOREIGN KEY (hall_id)
        REFERENCES core.halls (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS movie_sessions_movie_idx
    ON core.movie_sessions USING btree
        (movie_id ASC NULLS LAST);

CREATE INDEX IF NOT EXISTS movie_sessions_hall_idx
    ON core.movie_sessions USING btree
        (hall_id ASC NULLS LAST);

CREATE TABLE IF NOT EXISTS core.movie_sessions_seats
(
    movie_session_id bigint NOT NULL,
    seat_id          bigint NOT NULL,
    CONSTRAINT movie_sessions_seats_pkey PRIMARY KEY (movie_session_id, seat_id),
    CONSTRAINT movie_sessions_seats_movie_session_id_fkey FOREIGN KEY (movie_session_id)
        REFERENCES core.movie_sessions (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT movie_sessions_seats_seat_id_fkey FOREIGN KEY (seat_id)
        REFERENCES core.seats (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS movie_sessions_seats_movie_session_idx
    ON core.movie_sessions_seats USING btree
        (movie_session_id ASC NULLS LAST);

CREATE INDEX IF NOT EXISTS movie_sessions_seats_seat_idx
    ON core.movie_sessions_seats USING btree
        (seat_id ASC NULLS LAST);
