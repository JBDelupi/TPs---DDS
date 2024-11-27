NSERT INTO persona (id, tipo, calle, codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud, medioDeNotificacion, nombreUsuario, numero, tipoUsuario)
VALUES
    ('1', 'FISICO', 'Avenida Siempreviva 742', 'juanperez@example.com', 'securePass456', 'juanperez@example.com', '-34.6158', 'Recoleta', '-58.4354', 'Telefono', 'JP', '1133445566', 'FISICO'),
    ('2', 'FISICO', 'Mercedes 123', 'mariafernanda@gmail.com', 'password123', 'mariafernanda@gmail.com', '-34.6037', 'Palermo', '-58.3816', 'Correo', 'MFe', '1102233445', 'FISICO'),
    ('3', 'JURIDICO', 'Av. Belgrano 456', 'legalteam@hotmail.com', 'legal2024', 'legalteam@hotmail.com', '-34.6158', 'Monserrat', '-58.3846', 'Telefono', 'LegalCorp', '1155566677', 'JURIDICO'),
    ('4', 'FISICO', 'Av. Rivadavia 789', 'carlosperez@gmail.com', 'pass789', 'carlosperez@gmail.com', '-34.6215', 'Caballito', '-58.4326', 'Correo', 'CPerez', '1123456789', 'FISICO'),
    ('5', 'FISICO', 'Calle Florida 101', 'juridicoflorida@hotmail.com', 'juridico101', 'juridicoflorida@hotmail.com', '-34.6010', 'Retiro', '-58.3745', 'Correo', 'FloridaLegal', '1133344455', 'ADMINISTRADOR'),
    ('6', 'FISICO', 'Av. Corrientes 200', 'marialopez@gmail.com', 'password200', 'marialopez@gmail.com', '-34.6034', 'Balvanera', '-58.4029', 'Correo', 'MLopez', '1122233344', 'FISICO'),
    ('7', 'FISICO', 'San Martin 300', 'josemartinez@gmail.com', 'josepass', 'josemartinez@gmail.com', '-34.6070', 'San Telmo', '-58.3732', 'Telefono', 'JMartinez', '1166677788', 'FISICO'),
    ('8', 'JURIDICO', 'Av. Santa Fe 400', 'consultoreslegales@hotmail.com', 'juridico400', 'consultoreslegales@hotmail.com', '-34.5984', 'Recoleta', '-58.3968', 'Correo', 'ConsultLegales', '1199988877', 'JURIDICO'),
    ('9', 'FISICO', 'Defensa 500', 'lauragarcia@gmail.com', 'laurapass', 'lauragarcia@gmail.com', '-34.6125', 'San Telmo', '-58.3708', 'Correo', 'LGarcia', '1177766655', 'FISICO'),
    ('10', 'FISICO', 'Maipú 600', 'pablohernandez@hotmail.com', 'passpablo', 'pablohernandez@hotmail.com', '-34.6050', 'Microcentro', '-58.3778', 'Telefono', 'PHernandez', '1155544332', 'ADMINISTRADOR'),
    ('11', 'JURIDICO', 'Av. Callao 700', 'estudiosjuridicos@gmail.com', 'estudio700', 'estudiosjuridicos@gmail.com', '-34.6097', 'Recoleta', '-58.3926', 'Correo', 'EstudiosJuridicos', '1188811122', 'JURIDICO');

INSERT INTO juridico (id, razon_social, tipoJuridico)
VALUES
    ('3', 'Mercado Libre', 'EMPRESA'),
    ('8', 'Consultores Legales', 'ONG'),
    ('11', 'Estudios Jurídicos Asociados', 'GUBERNAMENTAL');

INSERT INTO fisico (fecha_nacimiento, id, apellido, nombre, numero_documento, tipo_documento)
VALUES
    ('1990-05-20', '1', 'Garcia', 'Pedro', '39827132', 'DNI'),
    ('1985-03-15', '2', 'Fernandez', 'Maria', '31245678', 'DNI'),
    ('1978-10-10', '4', 'Perez', 'Carlos', '29123456', 'DNI'),
    ('1992-12-01', '5', 'Lopez', 'Martina', '30123456', 'DNI'),
    ('1989-07-25', '6', 'Lopez', 'Maria', '29567890', 'DNI'),
    ('1975-04-18', '7', 'Martinez', 'Jose', '28765432', 'DNI'),
    ('1993-09-05', '9', 'Garcia', 'Laura', '32456789', 'DNI'),
    ('1980-06-22', '10', 'Hernandez', 'Pablo', '27890123', 'DNI');

INSERT INTO heladera (abierto, cantidad_de_fallas, cantidad_de_viandas_depositadas, cantidad_de_viandas_retiradas, capacidadActual, capacidad_de_viandas, estaLlena, fecha_de_puesta_en_marcha, id, persona_responsable_id, temperatura_actual, temperatura_maxima, temperatura_minima, calle, localidad, latitud, longitud, numero, estadoActual)
VALUES
    ('TRUE', '1', '10', '3', '17', '25', 'FALSE', '2020-05-15', '2', '3', '15', '35', '3', 'Av. Medrano', 'Palermo', '-34.5987', '-58.4224', '3182', 'DISPONIBLE'),
    ('FALSE', '2', '5', '1', '5', '10', 'FALSE', '2019-11-12', '3', '8', '10', '30', '1', 'Av. Santa Fe', 'Recoleta', '-34.5895', '-58.3974', '2345', 'NO_DISPONIBLE'),
    ('TRUE', '0', '20', '5', '15', '20', 'TRUE', '2021-03-25', '4', '11', '12', '32', '2', 'Av. Rivadavia', 'Caballito', '-34.6185', '-58.4407', '5555', 'DISPONIBLE'),
    ('FALSE', '3', '18', '7', '18', '20', 'TRUE', '2021-08-30', '5', '3', '14', '28', '4', 'Calle Corrientes', 'Balvanera', '-34.6033', '-58.4105', '1323', 'NO_DISPONIBLE'),
    ('TRUE', '1', '12', '4', '8', '15', 'FALSE', '2020-06-14', '6', '8', '16', '33', '5', 'Av. Callao', 'Recoleta', '-34.6097', '-58.3923', '3000', 'DISPONIBLE'),
    ('TRUE', '2', '15', '6', '9', '15', 'TRUE', '2019-07-18', '7', '11', '18', '36', '6', 'Av. Libertador', 'Núñez', '-34.5488', '-58.4563', '799', 'DISPONIBLE'),
    ('FALSE', '0', '8', '3', '7', '12', 'FALSE', '2022-01-01', '8', '3', '13', '27', '3', 'Av. Belgrano', 'Monserrat', '-34.6142', '-58.3808', '1700', 'NO_DISPONIBLE'),
    ('TRUE', '1', '14', '2', '14', '15', 'FALSE', '2021-12-22', '9', '8', '17', '34', '4', 'Calle Defensa', 'San Telmo', '-34.6149', '-58.3711', '1110', 'DISPONIBLE'),
    ('TRUE', '2', '6', '1', '10', '15', 'FALSE', '2020-09-15', '10', '11', '15', '29', '5', 'Av. Cabildo', 'Belgrano', '-34.5638', '-58.4480', '2800', 'DISPONIBLE');

INSERT INTO rol (id, id_persona, tipo)
VALUES
    ('1', '1', 'TECNICO'),
    ('2', '2', 'COLABORADOR'),
    ('3', '3', 'COLABORADOR')
    ('4', '4', 'TECNICO'),
    ('5', '5', 'VULNERABLE'),
    ('6', '6', 'TECNICO'),
    ('7', '7', 'COLABORADOR'),
    ('8', '8', 'COLABORADOR'),
    ('9', '9', 'VULNERABLE'),
    ('10', '10', 'TECNICO');
    ('11', '11', 'COLABORADOR');

INSERT INTO tecnico (id, cuil, latitud, longitud, radio)
VALUES
    ('1', '20-39827132-8', '-34.6100', '-58.4910', '2'),
    ('4', '20-31245678-5', '-34.6215', '-58.4326', '1'),
    ('6', '20-29567890-3', '-34.6034', '-58.4029', '5'),
    ('10', '20-27890123-7', '-34.6050', '-58.3778', '2');

INSERT INTO colaborador (cantidad_viandas_donadas, id, puntaje, tarjeta_codigo)
VALUES
    ('25', '2', '8500', '1'),
    ('40', '3', '92', '2'),
    ('30', '7', '750', '3'),
    ('20', '8', '800000', '4'),
    ('15', '11', '650', '5');

INSERT INTO tarjeta (cant_maxima_uso, codigo, colaborador_id, fechaUltUso, titular_id, usos_hoy, DTYPE)
VALUES
    ('10', '1', '2', '2024-11-15', '2', '5', 'tarjeta_accesos'),
    ('8', '2', '3', '2024-11-14', '3', '3', 'tarjeta_accesos'),
    ('5', '3', '7', '2024-11-12', '7', '2', 'tarjeta_accesos'),
    ('12', '4', '8', '2024-11-10', '8', '4', 'tarjeta_accesos'),
    ('7', '5', '11', '2024-11-13', '11', '1', 'tarjeta_accesos'),

    ('10', '6', '7', '2024-11-12', '5', '2', 'tarjeta_alimentar'),
    ('8', '7', '11', '2024-11-14', '9', '3', 'tarjeta_alimentar');

INSERT INTO personaVulnerable (fechaRegistro, flag_situacion_de_calle, id, menores_a_cargo, tarjeta_codigo)
VALUES
    ('2015-09-02', 'TRUE', '5', '1', '6'),
    ('1999-02-02', 'FALSE', '9', '0', '7');

INSERT INTO producto (id, descripcion, imagen, nombre, rubro)
VALUES
    (1,'Laptop Lenovo - 8GB RAM - 15" - 1TB SSD','','Laptop Lenovo A74895d', 'ELECTRODOMESTICO'),
    (2,'Heladera Samsung - 200W - 1TB SSD','','Heladera AJF23', 'ELECTRODOMESTICO');
