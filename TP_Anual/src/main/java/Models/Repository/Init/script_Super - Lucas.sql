-- Eliminar los datos de todas las tablas
DO $$
    DECLARE
        stmt text;
        seq RECORD;
    BEGIN
        -- TRUNCATE todas las tablas
        SELECT string_agg('TRUNCATE TABLE ' || quote_ident(schemaname) || '.' || quote_ident(tablename) || ' CASCADE;', ' ')
        INTO stmt
        FROM pg_tables
        WHERE schemaname = 'public';

        IF stmt IS NOT NULL THEN
            EXECUTE stmt;
        END IF;

        -- Reiniciar todas las secuencias
        FOR seq IN
            SELECT relname AS sequence_name
            FROM pg_class
            WHERE relkind = 'S'  -- 'S' son las secuencias
            LOOP
                EXECUTE 'ALTER SEQUENCE ' || seq.sequence_name || ' RESTART WITH 1;';
            END LOOP;
    END $$;


-- JURIDICO 1 --


WITH new_persona AS (
    INSERT INTO persona (tipo, calle, codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud, mediodenotificacion, nombreusuario, numero, tipousuario, alta)
        VALUES
            ('Juridico', 'Bruselas', 'lucasiturrioz212@gmail.com', MD5('1'), 'fisico1@gmail.com', NULL, 'Ituzaingo', NULL, 'Correo', 'coto', '1', 'JURIDICO', true)
        RETURNING id
),
-- Insertar en la tabla juridico usando el id de persona
     new_juridico AS (
         INSERT INTO juridico (id, razon_social, tipojuridico)
             SELECT id, 'Coto', 'EMPRESA'
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


     new_colaborador AS (
         INSERT INTO colaborador (id, cantidad_viandas_donadas, puntaje)
             SELECT id, 0, 0.0
             FROM new_rol
             RETURNING id
     )

-- HELADERA 1 --

INSERT INTO heladera (id,
                      abierto, cantidad_de_fallas, cantidad_de_viandas_depositadas, cantidad_de_viandas_retiradas, capacidadactual, capacidad_de_viandas, estallena, fecha_de_puesta_en_marcha, persona_responsable_id, temperatura_actual, temperatura_maxima, temperatura_minima, calle, estadoactual, latitud, localidad, longitud, numero
)
SELECT
    1,'T', 0, 0, 0, 4, 4, 'T', NULL, a.id, NULL, NULL, NULL, NULL, 'DISPONIBLE', '-34.6207', 'Caballito', '-58.4375','12'
FROM new_persona as a;

-- HELADERA 2 --

INSERT INTO heladera (id,
                      abierto, cantidad_de_fallas, cantidad_de_viandas_depositadas, cantidad_de_viandas_retiradas, capacidadactual, capacidad_de_viandas, estallena, fecha_de_puesta_en_marcha, persona_responsable_id, temperatura_actual, temperatura_maxima, temperatura_minima, calle, estadoactual, latitud, localidad, longitud, numero
) values  (2,'T',0,0,0,5,7,'F',null,null,0,10,-3,'','DISPONIBLE','-34.5924','Retiro','-58.3747','11');



-- TECNICO 1 --



WITH new_persona AS (
    INSERT INTO persona (tipo, calle, codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud, mediodenotificacion, nombreusuario, numero, tipousuario, alta)
        VALUES
            ('Fisico', 'Av. Juan b Justo', 'lucasiturrioz212@gmail.com', MD5('1'), 'tecnico1@gmail.com', NULL, 'Floresta', NULL, 'Correo', 'tecnico1', '123', 'FISICO', true)
        RETURNING id
),


-- Insertar en la tabla fisico utilizando el id generado en new_persona
     new_fisico AS (
         INSERT INTO fisico (id, fecha_nacimiento, apellido, nombre, numero_documento, tipo_documento)
             SELECT id, '1998-08-15', 'Iturrioz', 'Lucas', '12345678', 'DNI'
             FROM new_persona
             RETURNING id
     ),
-- Insertar en la tabla rol utilizando el id de la persona
     new_rol AS (
         INSERT INTO rol (id_persona, tipo)
             SELECT a.id, 'TECNICO' FROM new_persona as a
             RETURNING id
     )
-- Insertar en la tabla colaborador utilizando el id del rol
INSERT INTO tecnico (id, cuil,latitud,longitud,radio)
SELECT id, '20410661127', '-34.6289','-58.4859','3' FROM new_rol;



-- OTRO TECNICO 2 --


WITH new_persona AS (
    INSERT INTO persona (tipo, calle, codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud, mediodenotificacion, nombreusuario, numero, tipousuario)
        VALUES
            ('Fisico', 'Av. Heras', 'liturriozgarcia@frba.utn.edu.ar', MD5('1'), 'tecnico2@gmail.com', NULL, 'Recoleta', NULL, 'Correo', 'tecnico2', '1', 'FISICO')
        RETURNING id
),
-- Insertar en la tabla fisico utilizando el id generado en new_persona
     new_fisico AS (
         INSERT INTO fisico (id, fecha_nacimiento, apellido, nombre, numero_documento, tipo_documento)
             SELECT id, '1998-08-15', 'Garcia', 'David', '12345678', 'DNI'
             FROM new_persona
             RETURNING id
     ),
-- Insertar en la tabla rol utilizando el id de la persona
     new_rol AS (
         INSERT INTO rol (id_persona, tipo)
             SELECT a.id, 'TECNICO' FROM new_persona as a
             RETURNING id
     )
-- Insertar en la tabla colaborador utilizando el id del rol
INSERT INTO tecnico (id, cuil,latitud,longitud,radio)
SELECT id, '20410661127', '-34.5895','-58.3974','3' FROM new_rol;
