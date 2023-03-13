ALTER TABLE trp.booking
  ADD COLUMN reference_number TEXT NOT NULL
    DEFAULT gen_random_uuid() :: TEXT;


ALTER TABLE trp.booking
  ALTER COLUMN reference_number
    DROP DEFAULT;