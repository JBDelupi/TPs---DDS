
-- Inserting data into persona
INSERT INTO persona (id, tipo, calle, codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud, medioDeNotificacion, nombreUsuario, numero, tipoUsuario)
VALUES
    (1, 'Fisico', NULL, 'jdelupi@frba.utn.edu.ar', md5('1'), 'jdelupi@frba.utn.edu.ar', NULL, NULL, NULL, 'Correo', 'jdelupi@frba.utn.edu.ar', NULL, 'FISICO'),
    (2, 'Juridico', 'Ayacucho ', 'AppleOficial@apple.com', md5('Appleoficial1.'), 'AppleOficial@apple.com', NULL, 'recoleta', NULL, 'Correo', 'Apple', '1184', 'JURIDICO');

-- Inserting data into producto
INSERT INTO producto (id, descripcion, imagen, nombre, rubro)
VALUES
    (1, 'MacBook Pro 13" M2 Chip 8 Core CPU 10 Core GPU 512GB SSD Space Grey', 'macBook.png', 'Apple MacBook Pro 13', 'ELECTRONICA');

-- Inserting data into rol
INSERT INTO rol (id, id_persona, tipo)
VALUES
    (1, 1, 'COLABORADOR'),
    (2, 2, 'COLABORADOR');

-- Inserting data into colaborador
INSERT INTO colaborador (cantidad_viandas_donadas, id, puntaje, tarjeta_codigo)
VALUES
    (0, 1, 400000, NULL),
    (0, 2, 0, NULL);

-- Inserting data into contribucion
INSERT INTO contribucion (Id, fecha_de_donacion, id_colaborador, tipo)
VALUES
    (1, '2024-12-10', 2, 'ofrecer_producto');

-- Inserting data into fisico
INSERT INTO fisico (fecha_nacimiento, id, nombre, apellido, numero_Documento, tipo_documento)
VALUES
    (NULL, 1, 'DelupÍ', 'Juan Bautista', NULL, NULL);

-- Inserting data into juridico
INSERT INTO juridico (id, razon_social, tipoJuridico)
VALUES
    (2, 'APPLE ARGENTINA S.A', 'EMPRESA');

-- Inserting data into ofrecerproducto
INSERT INTO ofrecerproducto (Id, producto_id, puntos_necesarios, stock)
VALUES
    (1, 1, 500000, 2);


