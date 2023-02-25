CREATE SCHEMA trp;

-- Route
CREATE TABLE trp.route (
  id          BIGSERIAL NOT NULL,
  origin      TEXT      NOT NULL,
  destination TEXT      NOT NULL,
  distance    INTEGER   NOT NULL,

  t_active    BOOLEAN   NOT NULL DEFAULT TRUE,

  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX route_uq_origin_destination
  ON trp.route(origin, destination)
  WHERE t_active IS TRUE;

-- Pricing
CREATE TABLE trp.pricing (
  id          BIGSERIAL   NOT NULL,
  base_fare   BIGINT      NOT NULL,
  per_km_fare BIGINT      NOT NULL,
  bus_type    vh.BUS_TYPE NOT NULL,

  t_active    BOOLEAN     NOT NULL DEFAULT TRUE,

  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX pricing_uq_bus_type
  ON trp.pricing(bus_type)
  WHERE t_active IS TRUE;

-- Schedule
CREATE TABLE trp.schedule (
  id             BIGSERIAL   NOT NULL,
  route_id       BIGINT      NOT NULL,
  departure_time TIME        NOT NULL,
  bus_type       vh.bus_type NOT NULL,

  t_active       BOOLEAN     NOT NULL DEFAULT TRUE,

  PRIMARY KEY (id)
);