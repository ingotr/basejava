create table resume
(
    uuid      varchar(36) not null
        constraint resume_pk
            primary key,
    full_name text not null
);

create table contact
(
    id          serial
        constraint contact_pk
            primary key,
    type        text        not null,
    value       text        not null,
    resume_uuid varchar(36) not null
        constraint contact_resume_uuid___fk
            references resume
            on delete cascade
);

create table section
(
    id          serial
        constraint section_pk
            primary key,
    resume_uuid varchar(36) not null
        constraint section_fk
            references resume
            on delete cascade,
    type        text        not null,
    value       text        not null
);

create unique index section_index
    on section (resume_uuid, type);
