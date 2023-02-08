CREATE SCHEMA mng;

CREATE TABLE mng.user (
  id          BIGSERIAL NOT NULL,
  username    TEXT      NOT NULL,
  password    TEXT      NOT NULL,
  first_name  TEXT      NOT NULL,
  middle_name TEXT,
  last_name   TEXT      NOT NULL,
  birth_date  DATE      NOT NULL,
  t_active    BOOLEAN   NOT NULL DEFAULT TRUE,

  PRIMARY KEY (id)
);
