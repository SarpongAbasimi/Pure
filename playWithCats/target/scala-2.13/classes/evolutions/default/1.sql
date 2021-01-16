-- Todos schema

-- !Ups

CREATE TABLE Todo (
    id serial NOT NULL,
    todo text NOT NULL,
    PRIMARY KEY (id)
);

-- !Downs

DROP TABLE Todo;