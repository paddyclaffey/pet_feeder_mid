
CREATE TYPE schedule_category AS ENUM ('FEEDING', 'MEDICATION', 'EXERCISE', 'OTHER');

CREATE TABLE pet_schedules (
    id serial PRIMARY KEY,
    pet_id int NOT NULL,
    category text NOT NULL,
    description text,
    start_time time NOT NULL,
    frequency_interval interval NOT NULL,
    active boolean NOT NULL DEFAULT TRUE,
    FOREIGN KEY (pet_id) REFERENCES pets(id) ON DELETE CASCADE
);
