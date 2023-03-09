CREATE TABLE customer (
  id            BIGSERIAL NOT NULL,
  username      TEXT      NOT NULL,
  first_name    TEXT      NOT NULL,
  middle_name   TEXT,
  last_name     TEXT      NOT NULL,
  birth_date    DATE      NOT NULL,
  mobile_number TEXT      NOT NULL,
  email         TEXT      NOT NULL,
  t_active      BOOLEAN   NOT NULL DEFAULT TRUE,

  PRIMARY KEY (id)
);
