-- JURIDICO 1 --

-- Insertar en la tabla persona y obtener el id
INSERT INTO Persona (tipo, calle, codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud, mediodenotificacion, nombreusuario, numero, tipousuario)
VALUES
    ('Juridico', 'Avenida Medrano', 'lucasmatiasperez1@gmail.com', MD5('1'), 'juridico1@gmail.com', NULL, 'Almagro', NULL, 'Correo', 'MediSinFront', '632', 'JURIDICO');

-- Obtener el id generado de la persona insertada
SET @persona_id = LAST_INSERT_ID();

-- Insertar en la tabla juridico usando el id de persona
INSERT INTO Juridico (id, razon_social, tipojuridico)
VALUES (@persona_id, 'MediSinFront', 'ONG');

-- Insertar en la tabla rol usando el id de persona
INSERT INTO Rol (id_persona, tipo)
VALUES (@persona_id, 'COLABORADOR');

-- Insertar en la tabla colaborador usando el id de rol
INSERT INTO Colaborador (id, cantidad_viandas_donadas, puntaje)
VALUES (@persona_id, 0, 0.0);

-- Persona Física con Rol Colaborador
-- Insertar en la tabla persona física
INSERT INTO Persona (tipo, calle, codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud, mediodenotificacion, nombreusuario, numero, tipousuario)
VALUES
    ('Fisico', 'Humauaca', 'lucasmatiasperez1@gmail.com', MD5('1'), 'fisico1@gmail.com', NULL, 'Almagro', NULL, 'Correo', 'tecnico1', '4027', 'FISICO');

-- Obtener el id generado de la persona física insertada
SET @persona_fisica_id = LAST_INSERT_ID();

-- Insertar en la tabla rol usando el id de persona física
INSERT INTO Rol (id_persona, tipo)
VALUES (@persona_fisica_id, 'COLABORADOR');

-- Insertar en la tabla colaborador usando el id de rol
INSERT INTO Colaborador (id, cantidad_viandas_donadas, puntaje)
VALUES (@persona_fisica_id, 0, 0.0);

