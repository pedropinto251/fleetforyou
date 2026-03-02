CREATE DATABASE RENTCAR
GO
USE RENTCAR;
GO
CREATE TABLE "Stand"
(
	"id_stand"				integer IDENTITY (1,1),
	"name"					nvarchar(255) NOT NULL,
	"phone_number"			nvarchar(255) NOT NULL,
	"address"				nvarchar(255) NOT NULL,
	"postal_code"			nvarchar(255) NOT NULL,
	"local"					nvarchar(255) NOT NULL,
	"district"				nvarchar(255) NOT NULL,
	"deleted"               bit,
	CONSTRAINT "PK_Stand" PRIMARY KEY ("id_stand"),
	CONSTRAINT "UK_User_name" UNIQUE ("name")
);

CREATE TABLE "User"
(
	"id_user"				integer IDENTITY (1,1),
	"name"					nvarchar(255) NOT NULL,
	"phone_number"			nvarchar(255) NOT NULL,
	"address"				nvarchar(255) NOT NULL,
	"postal_code"			nvarchar(255) NOT NULL,
	"local"					nvarchar(255) NOT NULL,
	"district"				nvarchar(255) NOT NULL,
	"email"					nvarchar(255) NOT NULL,
	"password"				nvarchar(255) NOT NULL,
	"id_stand"				integer NOT NULL,
	"deleted"               bit,
	CONSTRAINT "PK_User" PRIMARY KEY ("id_user"),
	CONSTRAINT "UK_User_email" UNIQUE ("email"),
	CONSTRAINT "FK_User_Stand_id_stand" FOREIGN KEY ("id_stand") REFERENCES "Stand" ("id_stand")
);

CREATE TABLE "Permission"
(
	"id_permission"			integer IDENTITY (1,1),
	"description"			nvarchar(255) NOT NULL,
	CONSTRAINT "PK_Permission" PRIMARY KEY ("id_permission")
);

CREATE TABLE "User_Permission"
(
	"id_user" integer NOT NULL,
    "id_permission" integer NOT NULL,
    CONSTRAINT "FK_User_Permission_id_user" FOREIGN KEY ("id_user") REFERENCES "User" ("id_user"),
    CONSTRAINT "FK_User_Permission_id_permission" FOREIGN KEY ("id_permission") REFERENCES "Permission" ("id_permission")
);

CREATE TABLE "Client"
(
	"id_client"				integer IDENTITY (1,1),
	"vat_number"			nvarchar(255) NOT NULL,
	"name"					nvarchar(255) NOT NULL,
	"phone_number"			nvarchar(255) NOT NULL,
	"address"				nvarchar(255) NOT NULL,
	"postal_code"			nvarchar(255) NOT NULL,
	"local"					nvarchar(255) NOT NULL,
	"district"				nvarchar(255) NOT NULL,
	"deleted"               bit,
	CONSTRAINT "PK_Client" PRIMARY KEY ("id_client"),
	CONSTRAINT "UK_Client_vat_number" UNIQUE ("vat_number")
);

CREATE TABLE "Vehicle"
(
	"id_vehicle"			integer IDENTITY (1,1),
	"registration"			nvarchar(255) NOT NULL,
	"brand"					nvarchar(255) NOT NULL,
	"model"					nvarchar(255) NOT NULL,
	"segment"				nvarchar(255) NOT NULL,
	"status"				bit,
	"fuel"					nvarchar(255) NOT NULL,
	"year_built"			integer NOT NULL,
	"num_doors"				integer NOT NULL,
	"num_km"				decimal NOT NULL,
	"engine_capacity"		integer NOT NULL,
	"potency"				integer NOT NULL,
	"color"					nvarchar(255) NOT NULL,
	"rental_price"			decimal NOT NULL,
    "type"                  nvarchar (255) NOT NULL,
    "deleted"               bit,
    CONSTRAINT "PK_Vehicle" PRIMARY KEY ("id_vehicle"),
	CONSTRAINT "UK_Vehicle_registration" UNIQUE ("registration")
);

CREATE TABLE "Stand_User"
(
	"id_stand"				integer NOT NULL,
	"id_user"				integer NOT NULL,
	CONSTRAINT "FK_Stand_User_id_standUser" FOREIGN KEY ("id_stand") REFERENCES "Stand" ("id_stand"),
	CONSTRAINT "FK_Stand_User_id_user" FOREIGN KEY ("id_user") REFERENCES "User" ("id_user")
);

CREATE TABLE "Stand_Vehicle"
(
	"id_stand"				integer NOT NULL,
	"id_vehicle"			integer NOT NULL,
	CONSTRAINT "FK_Stand_User_id_standVehicle" FOREIGN KEY ("id_stand") REFERENCES "Stand" ("id_stand"),
	CONSTRAINT "FK_Stand_User_id_vehicle" FOREIGN KEY ("id_vehicle") REFERENCES "Vehicle" ("id_vehicle")
);

CREATE TABLE "Rental"
(
	"id_rental"				integer IDENTITY (1,1),
	"date_start"			datetime NOT NULL,
	"date_end"				datetime NOT NULL,
	"date_return"			datetime,
	"id_vehicle"			integer NOT NULL,
	"id_client"				integer NOT NULL,
	"id_user"				integer NOT NULL,
	CONSTRAINT "PK_Rental" PRIMARY KEY ("id_rental"),
	CONSTRAINT "FK_Rental_id_vehicle" FOREIGN KEY ("id_vehicle") REFERENCES "Vehicle" ("id_vehicle"),
	CONSTRAINT "FK_Rental_id_client" FOREIGN KEY ("id_client") REFERENCES "Client" ("id_client"),
	CONSTRAINT "FK_Rental_id_user" FOREIGN KEY ("id_user") REFERENCES "User" ("id_user")
);

CREATE TABLE "Form"
(
	"id_form"				integer IDENTITY (1,1),
	"satisfaction"			integer NOT NULL,
	"observation"			nvarchar(255),
	"id_rental"				integer NOT NULL,
	CONSTRAINT "PK_Form" PRIMARY KEY ("id_form"),
	CONSTRAINT "FK_Form_id_rental" FOREIGN KEY ("id_rental") REFERENCES "Rental" ("id_rental")
);


