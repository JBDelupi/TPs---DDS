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


WITH new_persona AS (
   INSERT INTO persona (tipo, calle, codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud, mediodenotificacion, nombreusuario, numero, tipousuario, alta)
       VALUES
           ('Fisico', 'Bruselas', 'nagimenez@frba.utn.edu.ar', MD5('Hola123.'), 'nagimenez@frba.utn.edu.ar', NULL, 'Caseros', NULL, 'Correo', 'Nahu', '1', 'ADMINISTRADOR',true)
       RETURNING id
)

INSERT INTO persona (id, tipo, calle, codigo_de_notificacion, contrasenia, correo_electronico, latitud, localidad, longitud, medioDeNotificacion, nombreUsuario, numero, tipoUsuario, alta)
VALUES
   (1, 'Juridico', 'Ayacucho ', 'CotoOficial@coto.com', md5('Coto123.'), 'CotoOficial@coto.com', NULL, 'recoleta', NULL, 'Correo', 'Coto', '1184', 'JURIDICO', true);


INSERT INTO rol (id, id_persona, tipo)
VALUES
   (1, 1, 'COLABORADOR');


INSERT INTO colaborador (cantidad_viandas_donadas, id, puntaje, tarjeta_codigo)
VALUES
   (0, 1, 0, NULL);


INSERT INTO juridico (id, razon_social, tipoJuridico)
VALUES
  (1, 'APPLE ARGENTINA S.A', 'EMPRESA');
