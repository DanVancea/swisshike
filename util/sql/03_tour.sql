create table tour (
    id serial primary key,
    name varchar(255) not null,
    difficulty varchar(255) not null,
    start_date timestamp not null,
    end_date timestamp not null,
    mountain_huts INT not null,
    hikers INT not null,
    primary key (mountain_huts),
    foreign key (mountain_huts)
                  references mountain_hut (id),
    primary key (hikers),
    foreign key (hikers)
        references hiker (id)
);