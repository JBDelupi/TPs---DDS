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



-- Insertar persona admin
INSERT INTO persona (tipo, calle, codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud, mediodenotificacion, nombreusuario, numero, tipousuario, alta)
VALUES
    ('Juridico', 'Bruselas', 'admin1@gmail.com', MD5('1234'), 'admin1@gmail.com', NULL, 'Ituzaingo', NULL, 'Correo', 'admin1', '1234', 'ADMINISTRADOR', true);




-- Insertar persona y ponerla en la tabla de fisica (futuro tecnico)
WITH new_persona AS (
INSERT INTO persona (tipo, calle, codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud, mediodenotificacion, nombreusuario, numero, tipousuario, alta)
VALUES
    ('Fisico', 'Bruselas', '1293045718', MD5('1234'), 'fisica1@gmail.com', NULL, 'Barracas', NULL, 'Telegram', 'fisica1', '1', 'FISICO', true)
    RETURNING id
    )
-- Insertar en la tabla fisico utilizando el id generado en new_persona
        , new_fisico AS (
INSERT INTO fisico (id, fecha_nacimiento, apellido, nombre, numero_documento, tipo_documento)
SELECT id, '1998-08-15', 'Iturrioz', 'Luis', '12345678', 'DNI'
FROM new_persona
    RETURNING id
    )




-- Insertar persona y ponerla en la tabla de fisica y hacerla colaborador
WITH new_persona AS (
    INSERT INTO persona (tipo, calle, codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud, mediodenotificacion, nombreusuario, numero, tipousuario, alta)
    VALUES
    ('Fisico', 'Bruselas', 'colab1@gmail.com', MD5('1234'), 'colab1@gmail.com', NULL, 'Monserrat', NULL, 'Telegram', 'colab1', '1', 'FISICO', true)
    RETURNING id
    )
-- Insertar en la tabla fisico utilizando el id generado en new_persona
        , new_fisico AS (
    INSERT INTO fisico (id, fecha_nacimiento, apellido, nombre, numero_documento, tipo_documento)
    SELECT id, '1998-08-15', 'Iturrioz', 'Luis', '12345678', 'DNI'
    FROM new_persona
    RETURNING id
    )
-- Insertar en la tabla rol utilizando el id generado en new_fisico
        , new_rol AS (
    INSERT INTO rol (id_persona, tipo)
    SELECT id, 'COLABORADOR' FROM new_fisico
    RETURNING id
    )
-- Insertar en la tabla colaborador utilizando el id generado en new_rol
INSERT INTO colaborador (id, cantidad_viandas_donadas, puntaje)
SELECT id, 0, 0.0 FROM new_rol;




-- Insertar heladera en Puerto Madero
INSERT INTO heladera (
    abierto, cantidad_de_fallas, cantidad_de_viandas_depositadas, cantidad_de_viandas_retiradas, capacidadactual, capacidad_de_viandas, estallena, fecha_de_puesta_en_marcha, persona_responsable_id, temperatura_actual, temperatura_maxima, temperatura_minima, calle, estadoactual, latitud, localidad, longitud, numero
)
VALUES
    ('T', 0, 0, 0, 4, 4, 'F', NULL, NULL, NULL, NULL, NULL, 'Bruselas', NULL, '-34.6372', 'Barracas', '-58.3847', '12');




-- Insertar heladera en Recoleta
INSERT INTO heladera (
    abierto, cantidad_de_fallas, cantidad_de_viandas_depositadas, cantidad_de_viandas_retiradas, capacidadactual, capacidad_de_viandas, estallena, fecha_de_puesta_en_marcha, persona_responsable_id, temperatura_actual, temperatura_maxima, temperatura_minima, calle, estadoactual, latitud, localidad, longitud, numero
)
VALUES
    ('T', 0, 0, 0, 4, 4, 'F', NULL, NULL, NULL, NULL, NULL, 'Bruselas', NULL, '-34.5625', 'Belgrano', '-58.4585', '12');