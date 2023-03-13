ALTER TABLE trp.trip
  ADD COLUMN trip_code TEXT NOT NULL
    DEFAULT gen_random_uuid() :: TEXT;

ALTER TABLE trp.trip
  ALTER COLUMN trip_code
    DROP DEFAULT;