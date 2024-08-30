CREATE TABLE IF NOT EXISTS `employee` (
    `id` INTEGER PRIMARY KEY,
    `enroll_id` VARCHAR(80) NOT NULL,
    `course_id` VARCHAR(30) NOT NULL,
    `employee_id` VARCHAR(30) NOT NULL,
    `status` BIT NOT NULL,
    `created_at` DATE DEFAULT NULL,
    `created_by` VARCHAR(30) DEFAULT NULL,
    `updated_at` DATE DEFAULT NULL,
    `updated_by` VARCHAR(30) DEFAULT NULL
    );