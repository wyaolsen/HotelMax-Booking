BEGIN TRANSACTION;

DROP TABLE IF EXISTS company, building, address, state, reservation, room, room_type, guest CASCADE;
--Company
    --company_id
    --company_name
    --description
    --website
CREATE TABLE company (
    company_id SERIAL,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(200),
    website VARCHAR(100),
    PRIMARY KEY (company_id)
);


--Buildings
    --building_id
    --name
    --state_id
    --zipcode
    --address
    --description
    --company_id
CREATE TABLE building (
    building_id SERIAL,
    name VARCHAR(30),
    description VARCHAR(200),
    company_id INT,
    address_id INT,
    PRIMARY KEY (building_id)
);

--Address
CREATE TABLE address (
    address_id SERIAL,
    street_address VARCHAR(100),
    city VARCHAR(50),
    state_id INT,
    zipcode VARCHAR(5),
    PRIMARY KEY (address_id)
);

--State
    --state_id
    --name
CREATE TABLE state (
    state_id SERIAL,
    name VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (state_id)
);

--Rooms
    --building_id
    --room_id
    --room_type_id
    --room_number
CREATE TABLE room (
    room_id SERIAL,
    room_type_id INT,
    room_number VARCHAR(3) NOT NULL,
    building_id INT,
    PRIMARY KEY (room_id)
);
--Room type
    --room_type_id
    --type
    --description
CREATE TABLE room_type (
    room_type_id SERIAL,
    type VARCHAR(50) NOT NULL,
    description VARCHAR(200),
    PRIMARY KEY (room_type_id)
);
--Reservations
    --reservation_id
    --guest_id
    --room_id
    --start_date
    --end_date
CREATE TABLE reservation (
    reservation_id SERIAL,
    guest_id INT NOT NULL,
    room_id INT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    PRIMARY KEY (reservation_id)
);

--Guests
    --guest_id
    --name
    --username
    --password
CREATE TABLE guest(
    guest_id SERIAL,
    name VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (guest_id)
);

--building FK
ALTER TABLE building ADD CONSTRAINT FK_company FOREIGN KEY (company_id) REFERENCES company(company_id) ON DELETE CASCADE;
ALTER TABLE building ADD CONSTRAINT FK_address FOREIGN KEY (address_id) REFERENCES address(address_id) ON DELETE SET NULL;

ALTER TABLE room ADD CONSTRAINT FK_room_type FOREIGN KEY (room_type_id) REFERENCES room_type(room_type_id) ON DELETE CASCADE;
ALTER TABLE room ADD CONSTRAINT FK_building FOREIGN KEY (building_id) REFERENCES building(building_id) ON DELETE CASCADE;

ALTER TABLE reservation ADD CONSTRAINT FK_guest FOREIGN KEY (guest_id) REFERENCES guest(guest_id) ON DELETE CASCADE;
ALTER TABLE reservation ADD CONSTRAINT FK_room FOREIGN KEY (room_id) REFERENCES room(room_id) ON DELETE CASCADE;

INSERT INTO guest (name, username, password) VALUES ('Wyatt Olsen', 'wyaolsen', 'password');
INSERT INTO guest (name, username, password) VALUES ('John Smith', 'johsmith', 'password');

INSERT INTO company (name, description, website) VALUES ('Hyatt', 'The hotel of your dreams', 'https://www.hyatt.com/');

INSERT INTO state (name) VALUES ('Alabama');
INSERT INTO state (name) VALUES ('Alaska');
INSERT INTO state (name) VALUES ('Arizona');
INSERT INTO state (name) VALUES ('Arkansas');
INSERT INTO state (name) VALUES ('California');
INSERT INTO state (name) VALUES ('Colorado');
INSERT INTO state (name) VALUES ('Connecticut');
INSERT INTO state (name) VALUES ('Delaware');
INSERT INTO state (name) VALUES ('Florida');
INSERT INTO state (name) VALUES ('Georgia');
INSERT INTO state (name) VALUES ('Hawaii');
INSERT INTO state (name) VALUES ('Idaho');
INSERT INTO state (name) VALUES ('Illinois');
INSERT INTO state (name) VALUES ('Indiana');
INSERT INTO state (name) VALUES ('Iowa');
INSERT INTO state (name) VALUES ('Kansas');
INSERT INTO state (name) VALUES ('Kentucky');
INSERT INTO state (name) VALUES ('Louisiana');
INSERT INTO state (name) VALUES ('Maine');
INSERT INTO state (name) VALUES ('Maryland');
INSERT INTO state (name) VALUES ('Massachusetts');
INSERT INTO state (name) VALUES ('Minnesota');
INSERT INTO state (name) VALUES ('Michigan');
INSERT INTO state (name) VALUES ('Mississippi');
INSERT INTO state (name) VALUES ('Missouri');
INSERT INTO state (name) VALUES ('Montana');
INSERT INTO state (name) VALUES ('Nebraska');
INSERT INTO state (name) VALUES ('Nevada');
INSERT INTO state (name) VALUES ('New Hampshire');
INSERT INTO state (name) VALUES ('New Jersey');
INSERT INTO state (name) VALUES ('New Mexico');
INSERT INTO state (name) VALUES ('New York');
INSERT INTO state (name) VALUES ('North Carolina');
INSERT INTO state (name) VALUES ('North Dakota');
INSERT INTO state (name) VALUES ('Ohio');
INSERT INTO state (name) VALUES ('Oklahoma');
INSERT INTO state (name) VALUES ('Oregon');
INSERT INTO state (name) VALUES ('Pennsylvania');
INSERT INTO state (name) VALUES ('Rhode Island');
INSERT INTO state (name) VALUES ('South Carolina');
INSERT INTO state (name) VALUES ('South Dakota');
INSERT INTO state (name) VALUES ('Tennessee');
INSERT INTO state (name) VALUES ('Texas');
INSERT INTO state (name) VALUES ('Utah');
INSERT INTO state (name) VALUES ('Vermont');
INSERT INTO state (name) VALUES ('Virginia');
INSERT INTO state (name) VALUES ('Washington');
INSERT INTO state (name) VALUES ('West Virginia');
INSERT INTO state (name) VALUES ('Wisconsin');
INSERT INTO state (name) VALUES ('Wyoming');
INSERT INTO address (street_address, city, state_id, zipcode) VALUES ('777E 777N', 'Salt Lake City', (SELECT state_id FROM state WHERE name = 'Utah'), '55555');

INSERT INTO building (name, description, company_id, address_id) VALUES ('Hyatt Salts Hotel', 'Right in the middle of down town Salt Lake City, Utah', (SELECT company_id FROM Company WHERE name = 'Hyatt'), 1);


INSERT INTO room_type (type, description) VALUES ('Single', 'This has a single bed');
INSERT INTO room_type (type, description) VALUES ('Double', 'This has a two beds');

INSERT INTO room (room_type_id, room_number, building_id ) VALUES ((SELECT room_type_id FROM room_type WHERE type = 'Single'), '100', (SELECT building_id FROM building WHERE name = 'Hyatt Salts Hotel'));
INSERT INTO room (room_type_id, room_number, building_id ) VALUES ((SELECT room_type_id FROM room_type WHERE type = 'Single'), '101', (SELECT building_id FROM building WHERE name = 'Hyatt Salts Hotel'));
INSERT INTO room (room_type_id, room_number, building_id ) VALUES ((SELECT room_type_id FROM room_type WHERE type = 'Single'), '102', (SELECT building_id FROM building WHERE name = 'Hyatt Salts Hotel'));
INSERT INTO room (room_type_id, room_number, building_id ) VALUES ((SELECT room_type_id FROM room_type WHERE type = 'Single'), '103', (SELECT building_id FROM building WHERE name = 'Hyatt Salts Hotel'));
INSERT INTO room (room_type_id, room_number, building_id ) VALUES ((SELECT room_type_id FROM room_type WHERE type = 'Single'), '104', (SELECT building_id FROM building WHERE name = 'Hyatt Salts Hotel'));
INSERT INTO room (room_type_id, room_number, building_id ) VALUES ((SELECT room_type_id FROM room_type WHERE type = 'Single'), '105', (SELECT building_id FROM building WHERE name = 'Hyatt Salts Hotel'));
INSERT INTO room (room_type_id, room_number, building_id ) VALUES ((SELECT room_type_id FROM room_type WHERE type = 'Single'), '106', (SELECT building_id FROM building WHERE name = 'Hyatt Salts Hotel'));
INSERT INTO room (room_type_id, room_number, building_id ) VALUES ((SELECT room_type_id FROM room_type WHERE type = 'Single'), '107', (SELECT building_id FROM building WHERE name = 'Hyatt Salts Hotel'));
INSERT INTO room (room_type_id, room_number, building_id ) VALUES ((SELECT room_type_id FROM room_type WHERE type = 'Single'), '108', (SELECT building_id FROM building WHERE name = 'Hyatt Salts Hotel'));
INSERT INTO room (room_type_id, room_number, building_id ) VALUES ((SELECT room_type_id FROM room_type WHERE type = 'Single'), '109', (SELECT building_id FROM building WHERE name = 'Hyatt Salts Hotel'));
INSERT INTO room (room_type_id, room_number, building_id ) VALUES ((SELECT room_type_id FROM room_type WHERE type = 'Single'), '110', (SELECT building_id FROM building WHERE name = 'Hyatt Salts Hotel'));
INSERT INTO room (room_type_id, room_number, building_id ) VALUES ((SELECT room_type_id FROM room_type WHERE type = 'Single'), '111', (SELECT building_id FROM building WHERE name = 'Hyatt Salts Hotel'));
INSERT INTO room (room_type_id, room_number, building_id ) VALUES ((SELECT room_type_id FROM room_type WHERE type = 'Double'), '112', (SELECT building_id FROM building WHERE name = 'Hyatt Salts Hotel'));
INSERT INTO room (room_type_id, room_number, building_id ) VALUES ((SELECT room_type_id FROM room_type WHERE type = 'Double'), '113', (SELECT building_id FROM building WHERE name = 'Hyatt Salts Hotel'));
INSERT INTO room (room_type_id, room_number, building_id ) VALUES ((SELECT room_type_id FROM room_type WHERE type = 'Double'), '114', (SELECT building_id FROM building WHERE name = 'Hyatt Salts Hotel'));
INSERT INTO room (room_type_id, room_number, building_id ) VALUES ((SELECT room_type_id FROM room_type WHERE type = 'Double'), '115', (SELECT building_id FROM building WHERE name = 'Hyatt Salts Hotel'));
INSERT INTO room (room_type_id, room_number, building_id ) VALUES ((SELECT room_type_id FROM room_type WHERE type = 'Double'), '116', (SELECT building_id FROM building WHERE name = 'Hyatt Salts Hotel'));


COMMIT;