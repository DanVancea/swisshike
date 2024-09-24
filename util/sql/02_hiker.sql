create table hiker (
    id               serial primary key,
    name             varchar(255) not null,
    age              integer      not null,
    experience_level varchar(255) not null,
    tour_id INT not null,
    primary key (tour_id),
    foreign key (tour_id)
        references tour (id)
);