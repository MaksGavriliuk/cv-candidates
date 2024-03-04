CREATE TABLE directions
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT         NOT NULL
);

CREATE TABLE tests
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    description  TEXT         NOT NULL,
    direction_id BIGINT       NOT NULL,
    FOREIGN KEY (direction_id) REFERENCES directions (id)
);