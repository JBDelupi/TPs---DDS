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



-- Inserting data into persona
INSERT INTO persona (id, tipo, calle, codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud, medioDeNotificacion, nombreUsuario, numero, tipoUsuario)
VALUES
    --lstraccia@frba.utn.edu.a
    (1, 'Fisico', NULL, 'jdelupi@frba.utn.edu.ar', md5('1'), 'jdelupi@frba.utn.edu.ar', NULL, NULL, NULL, 'Correo', 'jdelupi@frba.utn.edu.ar', NULL, 'FISICO'),
    (2, 'Juridico', 'Ayacucho ', 'deccocolaboraciones@gmail.com', md5('Deccooficial1.'), 'deccocolaboraciones@gmail.com', NULL, 'recoleta', NULL, 'Correo', 'Decco', '1184', 'JURIDICO');

-- Inserting data into producto
INSERT INTO producto (id, descripcion, imagen, nombre, rubro)
VALUES
    (1, 'Remera blanca de Decco Colaboraciones', 'remera.jpg', 'Remera DeccoColaboraciones', 'GASTRONOMIA');

-- Inserting data into rol
INSERT INTO rol (id, id_persona, tipo)
VALUES
    (1, 1, 'COLABORADOR'),
    (2, 2, 'COLABORADOR');

-- Inserting data into colaborador
INSERT INTO colaborador (cantidad_viandas_donadas, id, puntaje, tarjeta_codigo)
VALUES
    (0, 1, 100, NULL),
    (0, 2, 0, NULL);

-- Inserting data into contribucion
INSERT INTO contribucion (Id, fecha_de_donacion, id_colaborador, tipo)
VALUES
    (1, '2024-12-16', 2, 'ofrecer_producto');

-- Inserting data into fisico
INSERT INTO fisico (fecha_nacimiento, id, nombre, apellido, numero_Documento, tipo_documento)
VALUES
    (NULL, 1, 'Luciano', 'Straccia', NULL, NULL);

-- Inserting data into juridico
INSERT INTO juridico (id, razon_social, tipoJuridico)
VALUES
    (2, 'DECCO COLABORACIONES', 'ONG');

-- Inserting data into ofrecerproducto
INSERT INTO ofrecerproducto (Id, producto_id, puntos_necesarios, stock)
VALUES
    (1, 1, 100, 3);


