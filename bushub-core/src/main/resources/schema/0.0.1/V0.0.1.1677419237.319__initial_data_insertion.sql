INSERT INTO trp.route(origin, destination, distance, code)
VALUES ('CUBAO', 'BAGUIO CITY', 300, 'CUBAO_BAGUIO'),
  ('CUBAO', 'TUGUEGARAO CITY', 500, 'CUBAO_TUGUEGARAO');

INSERT INTO trp.pricing(base_fare, per_km_fare, bus_type)
VALUES (150, 3, 'REGULAR' :: vh.BUS_TYPE),
  (200, 5, 'FIRST_CLASS' :: vh.BUS_TYPE);

INSERT INTO trp.schedule(route_id, departure_time, bus_type)
VALUES ((
          SELECT
            id
          FROM trp.route
          WHERE code = 'CUBAO_BAGUIO'
        ), '17:00' :: TIME, 'REGULAR' :: vh.BUS_TYPE),
  ((
     SELECT
       id
     FROM trp.route
     WHERE code = 'CUBAO_BAGUIO'
   ), '19:00' :: TIME, 'FIRST_CLASS' :: vh.BUS_TYPE),
  ((
     SELECT
       id
     FROM trp.route
     WHERE code = 'CUBAO_TUGUEGARAO'
   ), '17:00' :: TIME, 'REGULAR' :: vh.BUS_TYPE),
  ((
     SELECT
       id
     FROM trp.route
     WHERE code = 'CUBAO_TUGUEGARAO'
   ), '19:00' :: TIME, 'FIRST_CLASS' :: vh.BUS_TYPE);


WITH
  schedule_data AS (
    SELECT
      s.id,
      p.base_fare + (p.per_km_fare * (r.distance - 100)) AS standard_fare
    FROM trp.schedule s
    INNER JOIN trp.route r
      ON r.id = s.route_id
    INNER JOIN trp.pricing p
      ON p.bus_type = s.bus_type
  )
INSERT
INTO trp.trip(schedule_id, departure_date, status, fare)
SELECT
  id,
  '2023-03-01' :: DATE,
  'PENDING' :: trp.TRIP_STATUS,
  standard_fare
FROM schedule_data;