CREATE TABLE driver (
    id UUID PRIMARY KEY,
    employee_id VARCHAR(20) UNIQUE NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    license_number VARCHAR(30) NOT NULL
);

CREATE TABLE vehicle (
    id UUID PRIMARY KEY,
    vin VARCHAR(17) UNIQUE NOT NULL,
    licence_plate VARCHAR(10) UNIQUE NOT NULL,
    brand VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    status VARCHAR(30) NOT NULL,
    driver_id UUID UNIQUE,
    CONSTRAINT fk_driver FOREIGN KEY (driver_id) REFERENCES driver(id) ON DELETE SET NULL
);

CREATE TABLE maintenance_task (
    id UUID PRIMARY KEY,
    vehicle_id UUID NOT NULL,
    description TEXT NOT NULL,
    cost DECIMAL(10, 2) NOT NULL,
    scheduled_date DATE NOT NULL,
    completion_date DATE,
    CONSTRAINT fk_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle(id) ON DELETE CASCADE
)