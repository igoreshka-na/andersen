DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id      INTEGER PRIMARY KEY,
    name    VARCHAR NOT NULL,
    surname VARCHAR NOT NULL
);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id)

        ON DELETE CASCADE
);

CREATE TABLE user_groups
(
    group        VARCHAR,
    team_lead_id INTEGER,
    user_id      INTEGER NOT NULL

    CONSTRAINT user_groups_idx UNIQUE (user_id, group),
    FOREIGN KEY (user_id) REFERENCES users (id)

        ON DELETE CASCADE,
    FOREIGN KEY (team_lead_id) REFERENCES users (id)

        ON DELETE CASCADE
);