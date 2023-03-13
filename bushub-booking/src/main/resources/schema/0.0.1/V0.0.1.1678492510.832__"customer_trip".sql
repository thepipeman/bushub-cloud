CREATE TABLE customer_trip (
  id               BIGSERIAL NOT NULL,
  customer_id      BIGINT    NOT NULL,
  reference_number TEXT      NOT NULL,

  t_active         BOOLEAN   NOT NULL DEFAULT TRUE,

  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX customer_trip_uq_reference_number
  ON customer_trip(reference_number)
  WHERE t_active IS TRUE;