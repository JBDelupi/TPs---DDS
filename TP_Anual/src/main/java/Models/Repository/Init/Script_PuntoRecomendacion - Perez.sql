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
-- Insertar persona jurídica y sus datos asociados
WITH new_persona_juridica AS (
    INSERT INTO persona (tipo, calle, codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud, mediodenotificacion, nombreusuario, numero, tipousuario, alta)
        VALUES
            ('Juridico', 'Avenida Medrano', 'lucasmatiasperez1@gmail.com', MD5('1'), 'lucasmatiasperez1@gmail.com', NULL, 'Almagro', NULL, 'Correo', 'MediSinFront', '632', 'JURIDICO', 'T')
        RETURNING id
),
     new_juridico AS (
         INSERT INTO juridico (id, razon_social, tipojuridico)
             SELECT id, 'MediSinFront', 'ONG'
             FROM new_persona_juridica
             RETURNING id
     ),
     new_rol_juridico AS (
         INSERT INTO rol (id_persona, tipo)
             SELECT id, 'COLABORADOR'
             FROM new_persona_juridica
             RETURNING id
     )
INSERT INTO colaborador (id, cantidad_viandas_donadas, puntaje)
SELECT id, 0, 0.0
FROM new_rol_juridico;

-- Insertar persona física y sus datos asociados
WITH new_persona_fisica AS (
    INSERT INTO persona (tipo, calle, codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud, mediodenotificacion, nombreusuario, numero, tipousuario, alta)
        VALUES
            ('Fisico', 'Humauaca', 'lucasmatiasperez1@gmail.com', MD5('1'), 'lprez@frba.utn.edu.ar', NULL, 'Almagro', NULL, 'Correo', 'tecnico1', '4027', 'FISICO', 'T')
        RETURNING id
),
     new_fisico AS (
         INSERT INTO fisico (id, fecha_nacimiento, apellido, nombre, numero_documento, tipo_documento)
             SELECT id, '1998-08-15', 'Perez', 'Lucas', '12345678', 'DNI'
             FROM new_persona_fisica
             RETURNING id
     ),
     new_rol_fisico AS (
         INSERT INTO rol (id_persona, tipo)
             SELECT id, 'COLABORADOR'
             FROM new_fisico
             RETURNING id
     )
INSERT INTO colaborador (id, cantidad_viandas_donadas, puntaje)
SELECT id, 0, 0.0
FROM new_rol_fisico;
