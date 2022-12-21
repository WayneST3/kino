
insert
into core.movie_genres(movie_id, genre)
values (1, 'Комедия')
on conflict do nothing;

insert
into core.movie_genres(movie_id, genre)
values (2, 'Семейный')
on conflict do nothing;

insert
into core.rows (number, hall_id, price_koef)
values (1, 1, 1.1)
on conflict do nothing;
insert into core.rows (number, hall_id, price_koef)
values (2, 1, 1.2)
on conflict do nothing;
insert into core.rows (number, hall_id, price_koef)
values (3, 1, 1.3)
on conflict do nothing;
insert into core.rows (number, hall_id, price_koef)
values (4, 1, 1.4)
on conflict do nothing;
insert into core.rows (number, hall_id, price_koef)
values (5, 1, 1.5)
on conflict do nothing;
insert into core.rows (number, hall_id, price_koef)
values (6, 1, 1.6)
on conflict do nothing;
insert into core.rows (number, hall_id, price_koef)
values (7, 1, 1.7)
on conflict do nothing;
insert into core.rows (number, hall_id, price_koef)
values (8, 1, 1.8)
on conflict do nothing;
insert into core.rows (number, hall_id, price_koef)
values (9, 1, 1.9)
on conflict do nothing;
insert into core.rows (number, hall_id, price_koef)
values (10, 1, 2.0)
on conflict do nothing;