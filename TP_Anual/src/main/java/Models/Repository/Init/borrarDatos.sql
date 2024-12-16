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