CREATE TABLE wsdb.users
(
    id integer NOT NULL,
    name character varying NOT NULL,
    surname character varying NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE wsdb.users_group
(
    name character varying,
    team_lead_id integer,
    user_id integer,
    CONSTRAINT name UNIQUE (name, user_id),
    CONSTRAINT lead_id FOREIGN KEY (team_lead_id)
        REFERENCES wsdb.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT user_id FOREIGN KEY (user_id)
        REFERENCES wsdb.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);

CREATE TABLE wsdb.user_roles
(
    user_id integer NOT NULL,
    role character varying,
    CONSTRAINT id UNIQUE (user_id, role),
    CONSTRAINT user_id FOREIGN KEY (user_id)
        REFERENCES wsdb.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);