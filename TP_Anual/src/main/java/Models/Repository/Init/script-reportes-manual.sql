-- INSERT INTO persona
INSERT INTO persona VALUES
                        ('T', 1, 'Fisico', NULL, 'tduren@frba.utn.edu.ar', 'c4ca4238a0b923820dcc509a6f75849b', 'tduren@frba.utn.edu.ar', NULL, NULL, NULL, 'Correo', 'tduren@frba.utn.edu.ar', NULL, 'FISICO'),
                        ('T', 2, 'Fisico', '1', 'admin@admin.com', '61cc0e405f4b518d264c089ac8b642ef', 'admin@admin.com', NULL, 'barrio-norte', NULL, 'Correo', 'admin', '1', 'ADMINISTRADOR'),
                        ('T', 3, 'Juridico', 'Honduras', 'tduren@frba.utn.edu.ar', '48ae1b30bbaea617ba41b86ac1bc4066', 'tduren@frba.utn.edu.ar', NULL, 'belgrano', NULL, 'Correo', 'coto', '353', 'JURIDICO'),
                        ('T', 4, 'Fisico', 'Paparela', NULL, '2e30386cae53dbc989d91731a75a1815', NULL, NULL, 'Palermo', NULL, 'Correo', '7512313', '422', 'FISICO'),
                        ('T', 5, 'Fisico', 'Paparela', NULL, '0bf943895a165911d18e1455c5ee19a4', NULL, NULL, 'Belgrano', NULL, 'Correo', '5341234123', '422', 'FISICO'),
                        ('T', 6, 'Fisico', 'Paparela', NULL, 'f9b06c8e59979f4ccfd96de8746576b5', NULL, NULL, 'Belgrano', NULL, 'Correo', '45432434', '422', 'FISICO'),
                        ('T', 7, 'Fisico', 'Paparela', NULL, 'a2926b49905fdc107b05e1f6f3257da5', NULL, NULL, 'Belgrano', NULL, 'Correo', '545234234', '422', 'FISICO'),
                        ('T', 8, 'Fisico', 'Paparela', NULL, '46310eb402e184bb757862862f12606a', NULL, NULL, 'Flores', NULL, 'Correo', '3453453', '422', 'FISICO');

-- INSERT INTO tarjeta
INSERT INTO tarjeta VALUES
                        (NULL, 1, NULL, NULL, 1, NULL, 'tarjeta_accesos'),
                        (NULL, 2, NULL, NULL, 2, NULL, 'tarjeta_accesos'),
                        (4, 3, NULL, '2024-12-16', 4, 1, 'tarjeta_alimentar'),
                        (4, 4, NULL, '2024-12-16', 5, 1, 'tarjeta_alimentar'),
                        (4, 5, NULL, '2024-12-16', 6, 1, 'tarjeta_alimentar'),
                        (4, 6, NULL, '2024-12-16', 7, 1, 'tarjeta_alimentar'),
                        (4, 7, NULL, '2024-12-16', 8, 1, 'tarjeta_alimentar');

-- INSERT INTO rol
INSERT INTO rol VALUES
                    (1, 1, 'COLABORADOR'),
                    (2, 2, 'COLABORADOR'),
                    (3, 3, 'COLABORADOR'),
                    (4, 4, 'VULNERABLE'),
                    (5, 5, 'VULNERABLE'),
                    (6, 6, 'VULNERABLE'),
                    (7, 7, 'VULNERABLE'),
                    (8, 8, 'VULNERABLE');

-- INSERT INTO colaborador
INSERT INTO colaborador VALUES
                            (8, 1, 16, 1),
                            (0, 2, 0, 2),
                            (0, 3, 0, NULL);

-- INSERT INTO contribucion
INSERT INTO contribucion VALUES
                             (1, '2024-12-16', 1, 'donacion_de_vianda'),
                             (2, '2024-12-16', 1, 'donacion_de_vianda'),
                             (3, '2024-12-16', 1, 'donacion_de_vianda'),
                             (4, '2024-12-16', 1, 'donacion_de_vianda'),
                             (5, '2024-12-16', 1, 'donacion_de_vianda'),
                             (6, '2024-12-16', 1, 'donacion_de_vianda'),
                             (7, '2024-12-16', 1, 'donacion_de_vianda'),
                             (8, '2024-12-16', 1, 'donacion_de_vianda'),
                             (9, '2024-12-16', 1, 'distribucion_de_viandas'),
                             (10, '2024-12-16', 1, 'entrega_de_tarjeta'),
                             (11, '2024-12-16', 1, 'entrega_de_tarjeta'),
                             (12, '2024-12-16', 1, 'entrega_de_tarjeta'),
                             (13, '2024-12-16', 1, 'entrega_de_tarjeta'),
                             (14, '2024-12-16', 1, 'entrega_de_tarjeta');

-- INSERT INTO heladera
INSERT INTO heladera (
    abierto,
    cantidad_de_fallas,
    cantidad_de_viandas_depositadas,
    cantidad_de_viandas_retiradas,
    capacidadactual,
    capacidad_de_viandas,
    estallena,
    fecha_de_puesta_en_marcha,
    id,
    persona_responsable_id,
    temperatura_actual,
    temperatura_maxima,
    temperatura_minima,
    calle,
    latitud,
    localidad,
    longitud,
    numero,
    estadoactual
)
VALUES
    ('T', 0, 8, 8, 15, 15, 'F', '2024-12-16', 1, NULL, NULL, 50, 1, 'Paparela', '-34.5875', 'recoleta', '-58.3974', '412', 'DISPONIBLE'),
    ('T', 0, 5, 2, 12, 15, 'F', '2024-12-16', 2, NULL, NULL, 50, 1, 'Honduras', '-34.6346', 'flores', '-58.4444', '412', 'DISPONIBLE');


-- INSERT INTO vianda
INSERT INTO vianda VALUES
                       (150, '2000-01-01', 1, NULL, 2, 'Vianda de alimentos'),
                       (150, '2000-01-01', 2, NULL, 2, 'Vianda de alimentos'),
                       (150, '2000-01-01', 3, 2, 2, 'Vianda de alimentos'),
                       (150, '2000-01-01', 4, 2, 2, 'Vianda de alimentos'),
                       (150, '2000-01-01', 5, 2, 2, 'Vianda de alimentos'),
                       (150, '2000-01-01', 6, NULL, 2, 'Vianda de alimentos'),
                       (150, '2000-01-01', 7, NULL, 2, 'Vianda de alimentos'),
                       (150, '2000-01-01', 8, NULL, 2, 'Vianda de alimentos');


-- INSERT INTO distribuciondeviandas
INSERT INTO distribuciondeviandas VALUES
    (9, 5, 2, 1, 'motivo');

-- INSERT INTO donaciondevianda
INSERT INTO donaciondevianda VALUES
                                 (1, 1, 1),
                                 (2, 1, 2),
                                 (3, 1, 3),
                                 (4, 1, 4),
                                 (5, 1, 5),
                                 (6, 1, 6),
                                 (7, 1, 7),
                                 (8, 1, 8);

-- INSERT INTO entregadetarjeta
INSERT INTO entregadetarjeta VALUES
                                 (10, 3),
                                 (11, 4),
                                 (12, 5),
                                 (13, 6),
                                 (14, 7);

-- INSERT INTO fisico
INSERT INTO fisico VALUES
                       (NULL, 1, 'Duren', 'Tobias', NULL, NULL),
                       ('0009-09-09', 2, 'Administrador', 'Administrador', '1', 'DNI'),
                       ('1990-01-01', 4, 'Cuevas', 'Eduardo', '7512313', 'DNI'),
                       ('1990-01-01', 5, 'Cuevas', 'Pedro', '5341234123', 'DNI'),
                       ('1990-01-01', 6, 'Lopez', 'Juan', '45432434', 'DNI'),
                       ('1990-01-01', 7, 'Garcia', 'Fernando', '545234234', 'DNI'),
                       ('1990-01-01', 8, 'Ortiz', 'Jorge', '3453453', 'DNI');


-- INSERT INTO juridico
INSERT INTO juridico VALUES
    (3, 'coto', 'GUBERNAMENTAL');


-- INSERT INTO personavulnerable
INSERT INTO personavulnerable VALUES
                                  ('2024-12-16', '0', 4, 0, 3),
                                  ('2024-12-16', '0', 5, 0, 4),
                                  ('2024-12-16', '0', 6, 0, 5),
                                  ('2024-12-16', '0', 7, 0, 6),
                                  ('2024-12-16', '0', 8, 0, 7);

-- INSERT INTO registro_de_uso
INSERT INTO registro_de_uso VALUES
                                ('2024-12-16', 1, 1, 4, 6, 'QUITAR'),
                                ('2024-12-16', 1, 2, 5, 7, 'QUITAR'),
                                ('2024-12-16', 1, 3, 6, 8, 'QUITAR'),
                                ('2024-12-16', 2, 4, 3, 1, 'QUITAR'),
                                ('2024-12-16', 2, 5, 7, 2, 'QUITAR');
