CREATE TABLE candidates
(
    id          BIGSERIAL PRIMARY KEY,
    surname     VARCHAR(255) NOT NULL,
    name        VARCHAR(255) NOT NULL,
    patronymic  VARCHAR(255) NOT NULL,
    description TEXT         NOT NULL,
    photo       BYTEA        NOT NULL,
    cv          BYTEA        NOT NULL
);

CREATE TABLE candidate_directions
(
    candidate_id BIGINT NOT NULL,
    direction_id BIGINT NOT NULL,
    PRIMARY KEY (candidate_id, direction_id),
    FOREIGN KEY (candidate_id) REFERENCES candidates (id),
    FOREIGN KEY (direction_id) REFERENCES directions (id)
);

CREATE TABLE candidate_tests
(
    id           BIGSERIAL PRIMARY KEY,
    candidate_id BIGINT NOT NULL,
    test_id      BIGINT NOT NULL,
    score        BIGINT,
    test_date    DATE,
    FOREIGN KEY (candidate_id) REFERENCES candidates (id),
    FOREIGN KEY (test_id) REFERENCES tests (id)
);