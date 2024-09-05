CREATE SCHEMA trp;

-- Route
CREATE TABLE trp.route
(
    id          BIGSERIAL NOT NULL,
    origin      TEXT      NOT NULL,
    destination TEXT      NOT NULL,
    distance    INTEGER   NOT NULL,
    code        TEXT      NOT NULL,

    t_active    BOOLEAN   NOT NULL DEFAULT TRUE,

    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX route_uq_origin_destination
    ON trp.route (origin, destination)
    WHERE t_active IS TRUE;

-- Pricing
CREATE TABLE trp.pricing
(
    id          BIGSERIAL   NOT NULL,
    base_fare   NUMERIC     NOT NULL,
    per_km_fare NUMERIC     NOT NULL,
    bus_type    vh.BUS_TYPE NOT NULL,

    t_active    BOOLEAN     NOT NULL DEFAULT TRUE,

    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX pricing_uq_bus_type
    ON trp.pricing (bus_type)
    WHERE t_active IS TRUE;

-- Schedule
CREATE TABLE trp.schedule
(
    id             BIGSERIAL   NOT NULL,
    route_id       BIGINT      NOT NULL,
    departure_time TIME        NOT NULL,
    bus_type       vh.bus_type NOT NULL,

    t_active       BOOLEAN     NOT NULL DEFAULT TRUE,

    PRIMARY KEY (id)
);

CREATE INDEX schedule_idx_route_id_departure_time
    ON trp.schedule (route_id, departure_time)
    WHERE t_active IS TRUE;

ALTER TABLE trp.schedule
    ADD CONSTRAINT schedule_fk_route_id
        FOREIGN KEY (route_id)
            REFERENCES trp.route (id);

-- Trip
DO
$$
    BEGIN
        CREATE TYPE trp.TRIP_STATUS AS ENUM (
            'PENDING' ,
            'DEPARTED',
            'CANCELLED',
            'COMPLETED'
            );
    EXCEPTION
        WHEN duplicate_object THEN NULL;
    END
$$;

CREATE CAST (CHARACTER VARYING as trp.TRIP_STATUS) WITH INOUT AS IMPLICIT;

-- a daily "snapshot" of schedule + with complete bus details.
CREATE TABLE trp.trip
(
    id             BIGSERIAL       NOT NULL,
    schedule_id    BIGINT          NOT NULL,
    departure_date DATE            NOT NULL,
    status         trp.TRIP_STATUS NOT NULL,
--   seats_available INTEGER[]       NOT NULL,
--   seats_taken     INTEGER[]       NULL,
    fare           BIGINT          NOT NULL,
    bus_id         BIGINT          NULL,

    t_active       BOOLEAN         NOT NULL DEFAULT TRUE,

    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX trip_uq_bus_id_departure_date
    ON trp.trip (bus_id, departure_date)
    WHERE t_active IS TRUE;


ALTER TABLE trp.trip
    ADD CONSTRAINT trip_fk_schedule_id
        FOREIGN KEY (schedule_id)
            REFERENCES trp.schedule (id);


ALTER TABLE trp.trip
    ADD CONSTRAINT trip_fk_bus_id
        FOREIGN KEY (bus_id)
            REFERENCES vh.bus (id);

-- Booking

DO
$$
    BEGIN
        CREATE TYPE trp.BOOKING_STATUS AS ENUM (
            'AVAILABLE',
            'LOCKED',
            'PENDING_PAYMENT',
            'PAID',
            'CANCELLED'
            );
    EXCEPTION
        WHEN duplicate_object THEN NULL;
    END
$$;

CREATE CAST (CHARACTER VARYING as trp.booking_status) WITH INOUT AS IMPLICIT;

CREATE TABLE trp.booking
(
    id               BIGSERIAL          NOT NULL,
    trip_id          BIGINT             NOT NULL,
    seat_number      INTEGER            NOT NULL,
    fare             BIGINT             NOT NULL,
    customer_name    TEXT,
    status           trp.BOOKING_STATUS NOT NULL,
    reference_number TEXT               NOT NULL,

    t_active         BOOLEAN            NOT NULL DEFAULT TRUE,

    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX booking_uq_trip_id_seat_number_available
    ON trp.booking (trip_id, seat_number)
    WHERE t_active IS TRUE
        AND status != 'CANCELLED' :: trp.BOOKING_STATUS;


ALTER TABLE trp.booking
    ADD CONSTRAINT booking_fk_trip_d
        FOREIGN KEY (trip_id)
            REFERENCES trp.trip (id);
