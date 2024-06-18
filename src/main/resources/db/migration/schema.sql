-- src/main/resources/db/migration/V1__schema.sql

-- Create table for Availability
CREATE TABLE IF NOT EXISTS availability (
    id UUID PRIMARY KEY,
    professional_id UUID NOT NULL,
    day_of_week VARCHAR(10) NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL
);

-- Create table for Reservation
CREATE TABLE IF NOT EXISTS reservation (
    id UUID PRIMARY KEY,
    professional_id UUID NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL
);

