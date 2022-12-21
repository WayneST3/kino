CREATE SEQUENCE core.halls_id_seq start 1;

CREATE TABLE IF NOT EXISTS core.halls
(
    id            bigint            NOT NULL DEFAULT nextval('core.halls_id_seq'::regclass),
    name          character varying NOT NULL,
    cleaning_time timestamp,
    CONSTRAINT halls_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE core.rows_id_seq start 1;

CREATE TABLE IF NOT EXISTS core.rows
(
    id         bigint NOT NULL DEFAULT nextval('core.rows_id_seq'::regclass),
    number     int    NOT NULL,
    hall_id    bigint NOT NULL,
    price_koef double precision,
    CONSTRAINT rows_pkey PRIMARY KEY (id),
    CONSTRAINT rows_hall_id_fkey FOREIGN KEY (hall_id)
        REFERENCES core.halls (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS rows_hall_idx
    ON core.rows USING btree
        (hall_id ASC NULLS LAST);

CREATE SEQUENCE core.seats_id_seq start 1;

CREATE TABLE IF NOT EXISTS core.seats
(
    id     bigint NOT NULL DEFAULT nextval('core.seats_id_seq'::regclass),
    number int    NOT NULL,
    row_id bigint NOT NULL,
    CONSTRAINT seats_pkey PRIMARY KEY (id),
    CONSTRAINT seats_row_id_fkey FOREIGN KEY (row_id)
        REFERENCES core.rows (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS seats_row_idx
    ON core.seats USING btree
        (row_id ASC NULLS LAST);

