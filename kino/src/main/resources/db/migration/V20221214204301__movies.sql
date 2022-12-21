CREATE SCHEMA core;

CREATE SEQUENCE core.movies_id_seq start 1;

CREATE TABLE IF NOT EXISTS core.movies
(
    id           bigint            NOT NULL DEFAULT nextval('core.movies_id_seq'::regclass),
    name         character varying NOT NULL,
    description  character varying,
    image_url    character varying,
    trailer_url  character varying,
    age_category character varying,
    country      character varying,
    director     character varying,
    duration     character varying,
    min_price    double precision,
    CONSTRAINT movies_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS core.movie_genres
(
    movie_id bigint            NOT NULL,
    genre    character varying NOT NULL,
    CONSTRAINT movie_genres_pkey PRIMARY KEY (movie_id, genre),
    CONSTRAINT movie_genres_movie_id_fkey FOREIGN KEY (movie_id)
        REFERENCES core.movies (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS movie_genres_movie_idx
    ON core.movie_genres USING btree
        (movie_id ASC NULLS LAST);
