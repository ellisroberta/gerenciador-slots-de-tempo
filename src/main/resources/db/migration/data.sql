-- src/main/resources/db/migration/V2__data.sql

-- Insert some sample data into availability table
INSERT INTO availability (id, professional_id, day_of_week, start_time, end_time)
VALUES
    (RANDOM_UUID(), RANDOM_UUID(), 'MONDAY', '09:00:00', '17:00:00'),
    (RANDOM_UUID(), RANDOM_UUID(), 'TUESDAY', '09:30:00', '18:00:00'),
    (RANDOM_UUID(), RANDOM_UUID(), 'WEDNESDAY', '10:00:00', '16:30:00');

-- Insert some sample data into reservation table
INSERT INTO reservation (id, professional_id, start_time, end_time)
VALUES
    (RANDOM_UUID(), RANDOM_UUID(), '10:00:00', '11:00:00'),
    (RANDOM_UUID(), RANDOM_UUID(), '14:30:00', '15:30:00');