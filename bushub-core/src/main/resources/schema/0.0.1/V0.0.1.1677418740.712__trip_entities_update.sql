ALTER TABLE trp.route
  ADD COLUMN code TEXT NOT NULL DEFAULT '';

ALTER TABLE trp.trip
  DROP COLUMN departure_time;
