DO $$
BEGIN
EXECUTE (
    SELECT string_agg('ALTER TABLE ' || quote_ident(schemaname) || '.' || quote_ident(tablename) || ' DISABLE TRIGGER ALL;', ' ')
    FROM pg_tables
    WHERE schemaname = 'public'
);
END $$;

-- Eliminar los datos de todas las tablas
DO $$
BEGIN
EXECUTE (
    SELECT string_agg('TRUNCATE TABLE ' || quote_ident(schemaname) || '.' || quote_ident(tablename) || ' CASCADE;', ' ')
    FROM pg_tables
    WHERE schemaname = 'public'
);
END $$;

-- Habilitar nuevamente las restricciones de claves foráneas
DO $$
BEGIN
EXECUTE (
    SELECT string_agg('ALTER TABLE ' || quote_ident(schemaname) || '.' || quote_ident(tablename) || ' ENABLE TRIGGER ALL;', ' ')
    FROM pg_tables
    WHERE schemaname = 'public'
);
END $$;

