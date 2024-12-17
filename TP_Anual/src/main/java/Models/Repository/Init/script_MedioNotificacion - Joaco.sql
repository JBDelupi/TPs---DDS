-- Eliminar los datos de todas las tablas
DO $$
    DECLARE
stmt text;
BEGIN
SELECT string_agg('TRUNCATE TABLE ' || quote_ident(schemaname) || '.' || quote_ident(tablename) || ' CASCADE;', ' ')
INTO stmt
FROM pg_tables
WHERE schemaname = 'public'
  AND tablename NOT LIKE 'pg_%'  -- Excluir tablas del sistema
  AND tablename NOT LIKE 'sql_%'; -- Excluir tablas generadas automáticamente;
IF stmt IS NOT NULL THEN
            EXECUTE stmt;
END IF;
END $$;

--CREAR PERSONA FISICA WHATSAPP

WITH new_persona AS (
INSERT INTO persona (tipo, calle,codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud,mediodenotificacion, nombreusuario, numero, tipousuario,alta)
VALUES
    ('Fisico', 'Bruselas','5491121619445', MD5('hola123'), 'joaquincoaricona@gmail.com', NULL, 'Ituzaingo', NULL,'Whatsapp', 'joaquin1', '100', 'FISICO',true)
    RETURNING id
    ),


    new_fisico AS (
INSERT INTO fisico (id, fecha_nacimiento, apellido, nombre, numero_documento, tipo_documento)
SELECT id, '1998-08-15', 'Coaricona', 'Joaquin', '12345678', 'DNI'
FROM new_persona
    RETURNING id
    ),


    new_rol AS (
INSERT INTO rol (id_persona, tipo)
SELECT id, 'COLABORADOR'
FROM new_persona
    RETURNING id
    )


INSERT INTO colaborador (id, cantidad_viandas_donadas, puntaje)
SELECT id, 0, 0.0
FROM new_rol;

--CREAR PERSONA FISICA TELEGRAM

WITH new_persona AS (
INSERT INTO persona (tipo, calle,codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud,mediodenotificacion, nombreusuario, numero, tipousuario,alta)
VALUES
    ('Fisico', 'Rivadavia','8068469724', MD5('hola123'), 'joaquincoaricona2@gmail.com', NULL, 'Floresta', NULL,'Telegram', 'joaquin2', '8300', 'FISICO',true)
    RETURNING id
    ),


    new_fisico AS (
INSERT INTO fisico (id, fecha_nacimiento, apellido, nombre, numero_documento, tipo_documento)
SELECT id, '1998-08-15', 'Gonzalez', 'Joaquin', '12345678', 'DNI'
FROM new_persona
    RETURNING id
    ),


    new_rol AS (
INSERT INTO rol (id_persona, tipo)
SELECT id, 'COLABORADOR'
FROM new_persona
    RETURNING id
    )


INSERT INTO colaborador (id, cantidad_viandas_donadas, puntaje)
SELECT id, 0, 0.0
FROM new_rol;

--CREAR PERSONA FISICA SMS

WITH new_persona AS (
INSERT INTO persona (tipo, calle,codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud,mediodenotificacion, nombreusuario, numero, tipousuario,alta)
VALUES
    ('Fisico', 'Rivadavia','+541124634937', MD5('hola123'), 'joaquincoaricona3@gmail.com', NULL, 'Caballito', NULL,'SMS', 'joaquin3', '6000', 'FISICO',true)
    RETURNING id
    ),


    new_fisico AS (
INSERT INTO fisico (id, fecha_nacimiento, apellido, nombre, numero_documento, tipo_documento)
SELECT id, '1998-08-15', 'Martinez', 'Joaquin', '12345678', 'DNI'
FROM new_persona
    RETURNING id
    ),


    new_rol AS (
INSERT INTO rol (id_persona, tipo)
SELECT id, 'COLABORADOR'
FROM new_persona
    RETURNING id
    )


INSERT INTO colaborador (id, cantidad_viandas_donadas, puntaje)
SELECT id, 0, 0.0
FROM new_rol;


--Crear Persona Juridica y Heladera
WITH new_persona AS (
INSERT INTO persona (tipo, calle, codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud, mediodenotificacion, nombreusuario, numero, tipousuario,alta)
VALUES
    ('Juridico', 'Bruselas', 'juridico125@gmail.com', MD5('hola123'), 'juri11@gmail.com', NULL, 'Ituzaingo', NULL, 'Correo', 'changomas', '1', 'JURIDICO',true)
    RETURNING id
    ),
-- Insertar en la tabla juridico usando el id de persona
    new_juridico AS (
INSERT INTO juridico (id, razon_social, tipojuridico)
SELECT id, 'changomas', 'EMPRESA'
FROM new_persona
    RETURNING id
    ),
-- Insertar en la tabla rol usando el id de persona
    new_rol AS (
INSERT INTO rol (id_persona, tipo)
SELECT id, 'COLABORADOR'
FROM new_persona
    RETURNING id
    ),
-- Insertar en la tabla colaborador usando el id de rol
    new_colaborador AS (
INSERT INTO colaborador (id, cantidad_viandas_donadas, puntaje)
SELECT id, 0, 0.0
FROM new_rol
    RETURNING id
    )


INSERT INTO heladera (
    abierto, cantidad_de_fallas, cantidad_de_viandas_depositadas, cantidad_de_viandas_retiradas, capacidadactual, capacidad_de_viandas, estallena, fecha_de_puesta_en_marcha, persona_responsable_id, temperatura_actual, temperatura_maxima, temperatura_minima, calle, estadoactual, latitud, localidad, longitud, numero
)
SELECT
    'T', 0, 0, 0, 4, 4, 'F', localtimestamp , a.id, NULL, NULL, NULL, 'Riglos', NULL, '-34.6207', 'Flores', '-58.4375','12'
FROM new_persona as a;
