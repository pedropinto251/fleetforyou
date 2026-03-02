--Stand
INSERT INTO [Stand](name, phone_number, address, postal_code, local, district)
VALUES ('Stand', '+1234567890', '123 Main Street', '12345', 'Anytown', 'Anydistrict');

--User
INSERT INTO [User](name, phone_number, address, postal_code, local, district, email, password, id_stand, deleted)
VALUES ('John Doe', '+1234567890', '123 Main Street', '12345', 'Anytown', 'Anydistrict', 'john.doe@example.com', 'password123', 1, 0);

--Permission
INSERT INTO [Permission](description)
VALUES ('God Admin')

INSERT INTO [Permission](description)
VALUES ('Gerir clientes')

INSERT INTO [Permission](description)
VALUES ('Gerir veículos')

INSERT INTO [Permission](description)
VALUES ('Criar aluguer')

INSERT INTO [Permission](description)
VALUES ('Gerir funcionários')

INSERT INTO [Permission](description)
VALUES ('Gerir estatísticas')

--User_Role id 1
INSERT INTO [User_Permission](id_permission, id_user)
VALUES (1, 1)

INSERT INTO [User_Permission](id_permission, id_user)
VALUES (2, 1)

INSERT INTO [User_Permission](id_permission, id_user)
VALUES (3, 1)

INSERT INTO [User_Permission](id_permission, id_user)
VALUES (4, 1)

INSERT INTO [User_Permission](id_permission, id_user)
VALUES (5, 1)

INSERT INTO [User_Permission](id_permission, id_user)
VALUES (6, 1)