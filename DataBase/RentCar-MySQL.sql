CREATE DATABASE RENTCAR;

USE RENTCAR;

CREATE TABLE Stand (
    id_stand INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    postal_code VARCHAR(255) NOT NULL,
    local VARCHAR(255) NOT NULL,
    district VARCHAR(255) NOT NULL,
    deleted BIT,
    CONSTRAINT UK_Stand_name UNIQUE (name)
);

CREATE TABLE User (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    postal_code VARCHAR(255) NOT NULL,
    local VARCHAR(255) NOT NULL,
    district VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    id_stand INT NOT NULL,
    deleted BIT,
    CONSTRAINT UK_User_email UNIQUE (email),
    CONSTRAINT FK_User_Stand_id_stand FOREIGN KEY (id_stand) REFERENCES Stand(id_stand)
);

CREATE TABLE Permission (
    id_permission INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE User_Permission (
    id_user INT NOT NULL,
    id_permission INT NOT NULL,
    CONSTRAINT FK_User_Permission_id_user FOREIGN KEY (id_user) REFERENCES Users (id_user),
    CONSTRAINT FK_User_Permission_id_permission FOREIGN KEY (id_permission) REFERENCES Permission (id_permission)
);

CREATE TABLE Client (
    id_client INT AUTO_INCREMENT PRIMARY KEY,
    vat_number VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    postal_code VARCHAR(255) NOT NULL,
    local VARCHAR(255) NOT NULL,
    district VARCHAR(255) NOT NULL,
    deleted BIT,
    CONSTRAINT UK_Client_vat_number UNIQUE (vat_number)
);

CREATE TABLE Vehicle (
    id_vehicle INT AUTO_INCREMENT PRIMARY KEY,
    registration VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    segment VARCHAR(255) NOT NULL,
    status BIT,
    fuel VARCHAR(255) NOT NULL,
    year_built INT NOT NULL,
    num_doors INT NOT NULL,
    num_km DECIMAL(10,2) NOT NULL,
    engine_capacity INT NOT NULL,
    potency INT NOT NULL,
    color VARCHAR(255) NOT NULL,
    rental_price DECIMAL(10,2) NOT NULL,
    type varchar (255) NOT NULL,
    deleted BIT,
    CONSTRAINT UK_Vehicle_registration UNIQUE (registration)
);

CREATE TABLE Stand_User (
    id_stand INT NOT NULL,
    id_user INT NOT NULL,
    CONSTRAINT FK_Stand_User_id_standUser FOREIGN KEY (id_stand) REFERENCES Stand (id_stand),
    CONSTRAINT FK_Stand_User_id_user FOREIGN KEY (id_user) REFERENCES Users (id_user)
);

CREATE TABLE Stand_Vehicle (
    id_stand INT NOT NULL,
    id_vehicle INT NOT NULL,
    CONSTRAINT FK_Stand_User_id_standVehicle FOREIGN KEY (id_stand) REFERENCES Stand (id_stand),
    CONSTRAINT FK_Stand_User_id_vehicle FOREIGN KEY (id_vehicle) REFERENCES Vehicle (id_vehicle)
);

CREATE TABLE Rental (
    id_rental INT AUTO_INCREMENT PRIMARY KEY,
    date_start DATETIME NOT NULL,
    date_end DATETIME NOT NULL,
    date_return DATETIME,
    id_vehicle INT NOT NULL,
    id_client INT NOT NULL,
    id_user INT NOT NULL,
    CONSTRAINT FK_Rental_id_vehicle FOREIGN KEY (id_vehicle) REFERENCES Vehicle (id_vehicle),
    CONSTRAINT FK_Rental_id_client FOREIGN KEY (id_client) REFERENCES Client (id_client),
    CONSTRAINT FK_Rental_id_user FOREIGN KEY (id_user) REFERENCES Users (id_user)
);

CREATE TABLE Form (
    id_form INT AUTO_INCREMENT PRIMARY KEY,
    satisfaction INT NOT NULL,
    observation VARCHAR(255),
    id_rental INT NOT NULL,
    CONSTRAINT FK_Form_id_rental FOREIGN KEY (id_rental) REFERENCES Rental (id_rental)
);
