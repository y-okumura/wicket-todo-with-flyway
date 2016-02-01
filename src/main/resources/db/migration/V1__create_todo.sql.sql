create table TODO (
    id BIGINT NOT NULL,
    done BOOLEAN,
    description VARCHAR(255),
    due DATE,
    PRIMARY KEY (id)
)
