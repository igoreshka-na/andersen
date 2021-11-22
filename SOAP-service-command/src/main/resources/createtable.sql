CREATE TABLE wsdb.users
(
    id integer NOT NULL,
    name character varying NOT NULL,
    surname character varying NOT NULL,
    group_id integer DEFAULT 0,
    CONSTRAINT users_pkey PRIMARY KEY (id),
);

CREATE TABLE wsdb.user_groups
(
    id integer NOT NULL,
    name character varying NOT NULL,
    team_lead_id integer DEFAULT 0,
    CONSTRAINT PRIMARY KEY (id),
    CONSTRAINT name UNIQUE (name),
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