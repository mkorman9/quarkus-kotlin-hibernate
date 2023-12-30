create table duck_groups (
     id         uuid          constraint duck_groups_pkey primary key,
     name       varchar(255),
     created_at timestamp
);

create table ducks (
    id         uuid          constraint ducks_pkey primary key,
    name       varchar(255),
    height     integer,
    group_id   uuid          constraint ducks__duck_groups_fkey
        references duck_groups(id) on delete cascade on update cascade,
    created_at timestamp
);
