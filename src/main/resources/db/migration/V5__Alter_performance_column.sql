ALTER TABLE performance DROP COLUMN IF EXISTS date;
ALTER TABLE performance ADD COLUMN IF NOT EXISTS start_date_time timestamp;
ALTER TABLE performance ADD COLUMN IF NOT EXISTS end_date_time timestamp;