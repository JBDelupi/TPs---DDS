-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: deccocolaboracion
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `canje`
--

DROP TABLE IF EXISTS `canje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `canje` (
  `cantidad` int DEFAULT NULL,
  `colaborador_id` int DEFAULT NULL,
  `fecha_canje` date DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `ofrecerProducto_Id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr07pj6joe0ksadi0k22a6720f` (`colaborador_id`),
  KEY `FKe8ffimu2r63qto1tw2am3wnr6` (`ofrecerProducto_Id`),
  CONSTRAINT `FKe8ffimu2r63qto1tw2am3wnr6` FOREIGN KEY (`ofrecerProducto_Id`) REFERENCES `ofrecerproducto` (`Id`),
  CONSTRAINT `FKr07pj6joe0ksadi0k22a6720f` FOREIGN KEY (`colaborador_id`) REFERENCES `colaborador` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `canje`
--

LOCK TABLES `canje` WRITE;
/*!40000 ALTER TABLE `canje` DISABLE KEYS */;
/*!40000 ALTER TABLE `canje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colaborador`
--

DROP TABLE IF EXISTS `colaborador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colaborador` (
  `cantidad_viandas_donadas` int DEFAULT NULL,
  `id` int NOT NULL,
  `puntaje` double DEFAULT NULL,
  `tarjeta_codigo` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKmkp18f6g1mesrktlwbcrjpjjn` (`tarjeta_codigo`),
  CONSTRAINT `FK33o05rk79hokwq4wbbk1pgwnl` FOREIGN KEY (`tarjeta_codigo`) REFERENCES `tarjeta` (`codigo`),
  CONSTRAINT `FK8b6bovkixn6ds1sbkd8dyhnuw` FOREIGN KEY (`id`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colaborador`
--

LOCK TABLES `colaborador` WRITE;
/*!40000 ALTER TABLE `colaborador` DISABLE KEYS */;
INSERT INTO `colaborador` VALUES (8,1,16,1),(0,2,0,2),(0,3,0,NULL);
/*!40000 ALTER TABLE `colaborador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contribucion`
--

DROP TABLE IF EXISTS `contribucion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contribucion` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `fecha_de_donacion` date DEFAULT NULL,
  `id_colaborador` int DEFAULT NULL,
  `tipo` varchar(31) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKqq8naaakahf09lfomql016n2p` (`id_colaborador`),
  CONSTRAINT `FKqq8naaakahf09lfomql016n2p` FOREIGN KEY (`id_colaborador`) REFERENCES `colaborador` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contribucion`
--

LOCK TABLES `contribucion` WRITE;
/*!40000 ALTER TABLE `contribucion` DISABLE KEYS */;
INSERT INTO `contribucion` VALUES (1,'2024-12-16',1,'donacion_de_vianda'),(2,'2024-12-16',1,'donacion_de_vianda'),(3,'2024-12-16',1,'donacion_de_vianda'),(4,'2024-12-16',1,'donacion_de_vianda'),(5,'2024-12-16',1,'donacion_de_vianda'),(6,'2024-12-16',1,'donacion_de_vianda'),(7,'2024-12-16',1,'donacion_de_vianda'),(8,'2024-12-16',1,'donacion_de_vianda'),(9,'2024-12-16',1,'distribucion_de_viandas'),(10,'2024-12-16',1,'entrega_de_tarjeta'),(11,'2024-12-16',1,'entrega_de_tarjeta'),(12,'2024-12-16',1,'entrega_de_tarjeta'),(13,'2024-12-16',1,'entrega_de_tarjeta'),(14,'2024-12-16',1,'entrega_de_tarjeta');
/*!40000 ALTER TABLE `contribucion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distribuciondeviandas`
--

DROP TABLE IF EXISTS `distribuciondeviandas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `distribuciondeviandas` (
  `Id` int NOT NULL,
  `cantidad_de_viandas_a_mover` int DEFAULT NULL,
  `heladera_destino_id` int DEFAULT NULL,
  `heladera_origen_id` int DEFAULT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKe6n8cmci6k9hty2tjlbnf9w9k` (`heladera_destino_id`),
  KEY `FK4fanwidrboempffbsmm1y3iy6` (`heladera_origen_id`),
  CONSTRAINT `FK4fanwidrboempffbsmm1y3iy6` FOREIGN KEY (`heladera_origen_id`) REFERENCES `heladera` (`id`),
  CONSTRAINT `FK8240ihrredqj2uy12d5ujh7lk` FOREIGN KEY (`Id`) REFERENCES `contribucion` (`Id`),
  CONSTRAINT `FKe6n8cmci6k9hty2tjlbnf9w9k` FOREIGN KEY (`heladera_destino_id`) REFERENCES `heladera` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distribuciondeviandas`
--

LOCK TABLES `distribuciondeviandas` WRITE;
/*!40000 ALTER TABLE `distribuciondeviandas` DISABLE KEYS */;
INSERT INTO `distribuciondeviandas` VALUES (9,5,2,1,'motivo');
/*!40000 ALTER TABLE `distribuciondeviandas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donaciondedinero`
--

DROP TABLE IF EXISTS `donaciondedinero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donaciondedinero` (
  `Id` int NOT NULL,
  `monto` double DEFAULT NULL,
  `tipo_de_frecuencia` enum('ANUAL','DIARIO','MENSUAL','TRISTREMAL') DEFAULT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `FKm80e6u3xsxcm01nq8f6cp237q` FOREIGN KEY (`Id`) REFERENCES `contribucion` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donaciondedinero`
--

LOCK TABLES `donaciondedinero` WRITE;
/*!40000 ALTER TABLE `donaciondedinero` DISABLE KEYS */;
/*!40000 ALTER TABLE `donaciondedinero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donaciondevianda`
--

DROP TABLE IF EXISTS `donaciondevianda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donaciondevianda` (
  `Id` int NOT NULL,
  `heladera_id` int DEFAULT NULL,
  `vianda_id` int DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK8quynrir28htn64foktkpnh8k` (`vianda_id`),
  KEY `FKk74pm4t0eyfqft773adhb6b91` (`heladera_id`),
  CONSTRAINT `FKe2mux3mwru3kqh6l431ohfac8` FOREIGN KEY (`Id`) REFERENCES `contribucion` (`Id`),
  CONSTRAINT `FKk74pm4t0eyfqft773adhb6b91` FOREIGN KEY (`heladera_id`) REFERENCES `heladera` (`id`),
  CONSTRAINT `FKt5yre4vfv80soeu8tbo76iavk` FOREIGN KEY (`vianda_id`) REFERENCES `vianda` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donaciondevianda`
--

LOCK TABLES `donaciondevianda` WRITE;
/*!40000 ALTER TABLE `donaciondevianda` DISABLE KEYS */;
INSERT INTO `donaciondevianda` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8);
/*!40000 ALTER TABLE `donaciondevianda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entregadetarjeta`
--

DROP TABLE IF EXISTS `entregadetarjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entregadetarjeta` (
  `Id` int NOT NULL,
  `tarjeta_codigo` int DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK3aolqiiulg20nn7dm9rp8ouwt` (`tarjeta_codigo`),
  CONSTRAINT `FK5ftp4m8vryk8ixiecx75w33ya` FOREIGN KEY (`tarjeta_codigo`) REFERENCES `tarjeta` (`codigo`),
  CONSTRAINT `FK8x9k1cjxquw1wlast6grf474o` FOREIGN KEY (`Id`) REFERENCES `contribucion` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entregadetarjeta`
--

LOCK TABLES `entregadetarjeta` WRITE;
/*!40000 ALTER TABLE `entregadetarjeta` DISABLE KEYS */;
INSERT INTO `entregadetarjeta` VALUES (10,3),(11,4),(12,5),(13,6),(14,7);
/*!40000 ALTER TABLE `entregadetarjeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faltannviandasparallenar`
--

DROP TABLE IF EXISTS `faltannviandasparallenar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faltannviandasparallenar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cantidad_de_viandas` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faltannviandasparallenar`
--

LOCK TABLES `faltannviandasparallenar` WRITE;
/*!40000 ALTER TABLE `faltannviandasparallenar` DISABLE KEYS */;
/*!40000 ALTER TABLE `faltannviandasparallenar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fisico`
--

DROP TABLE IF EXISTS `fisico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fisico` (
  `fecha_nacimiento` date DEFAULT NULL,
  `id` int NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `numero_Documento` varchar(255) DEFAULT NULL,
  `tipo_documento` enum('DNI','LIBRETA','PASAPORTE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK59ew6rrfpa4d9gys0xcswbcvh` FOREIGN KEY (`id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fisico`
--

LOCK TABLES `fisico` WRITE;
/*!40000 ALTER TABLE `fisico` DISABLE KEYS */;
INSERT INTO `fisico` VALUES (NULL,1,'Duren','Tobias',NULL,NULL),('0009-09-09',2,'Administrador','Administrador','1','DNI'),('1990-01-01',4,'Cuevas','Eduardo','7512313','DNI'),('1990-01-01',5,'Cuevas','Pedro','5341234123','DNI'),('1990-01-01',6,'Lopez','Juan','45432434','DNI'),('1990-01-01',7,'Garcia','Fernando','545234234','DNI'),('1990-01-01',8,'Ortiz','Jorge','3453453','DNI');
/*!40000 ALTER TABLE `fisico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hacersecargodeheladera`
--

DROP TABLE IF EXISTS `hacersecargodeheladera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hacersecargodeheladera` (
  `Id` int NOT NULL,
  `heladera_id` int DEFAULT NULL,
  `nombre_caracteristico` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKbukhfajbf6byw6vg65wvr00nt` (`heladera_id`),
  CONSTRAINT `FKbukhfajbf6byw6vg65wvr00nt` FOREIGN KEY (`heladera_id`) REFERENCES `heladera` (`id`),
  CONSTRAINT `FKo5oq90ikhqg43eblns6m4j7k` FOREIGN KEY (`Id`) REFERENCES `contribucion` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hacersecargodeheladera`
--

LOCK TABLES `hacersecargodeheladera` WRITE;
/*!40000 ALTER TABLE `hacersecargodeheladera` DISABLE KEYS */;
/*!40000 ALTER TABLE `hacersecargodeheladera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `heladera`
--

DROP TABLE IF EXISTS `heladera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `heladera` (
  `abierto` char(1) DEFAULT NULL,
  `cantidad_de_fallas` int DEFAULT NULL,
  `cantidad_de_viandas_depositadas` int DEFAULT NULL,
  `cantidad_de_viandas_retiradas` int DEFAULT NULL,
  `capacidadActual` int DEFAULT NULL,
  `capacidad_de_viandas` int DEFAULT NULL,
  `estaLlena` char(1) DEFAULT NULL,
  `fecha_de_puesta_en_marcha` date DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `persona_responsable_id` int DEFAULT NULL,
  `temperatura_actual` double DEFAULT NULL,
  `temperatura_maxima` double DEFAULT NULL,
  `temperatura_minima` double DEFAULT NULL,
  `calle` varchar(255) DEFAULT NULL,
  `latitud` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `longitud` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `estadoActual` enum('DISPONIBLE','NO_DISPONIBLE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmuwjjof8il4e4epan0jfcuhwd` (`persona_responsable_id`),
  CONSTRAINT `FKmuwjjof8il4e4epan0jfcuhwd` FOREIGN KEY (`persona_responsable_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heladera`
--

LOCK TABLES `heladera` WRITE;
/*!40000 ALTER TABLE `heladera` DISABLE KEYS */;
INSERT INTO `heladera` VALUES ('T',0,8,8,15,15,'F','2024-12-16',1,NULL,NULL,50,1,'Paparela','-34.5875','recoleta','-58.3974','412','DISPONIBLE'),('T',0,5,2,12,15,'F','2024-12-16',2,NULL,NULL,50,1,'Honduras','-34.6346','flores','-58.4444','412','DISPONIBLE');
/*!40000 ALTER TABLE `heladera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incidente`
--

DROP TABLE IF EXISTS `incidente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incidente` (
  `colaborador_id` int DEFAULT NULL,
  `heladera_id` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `solucionado` char(1) DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `fechaSolucionado` datetime(6) DEFAULT NULL,
  `tipo` varchar(31) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `tipo_alerta` enum('FALLA_EN_CONEXION','FRAUDE','TEMPERATURA') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi8xtbci9hnyqe62mmaf98ho3q` (`heladera_id`),
  KEY `FKjsnkdxswixy0iyqbcb62040jo` (`colaborador_id`),
  CONSTRAINT `FKi8xtbci9hnyqe62mmaf98ho3q` FOREIGN KEY (`heladera_id`) REFERENCES `heladera` (`id`),
  CONSTRAINT `FKjsnkdxswixy0iyqbcb62040jo` FOREIGN KEY (`colaborador_id`) REFERENCES `colaborador` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incidente`
--

LOCK TABLES `incidente` WRITE;
/*!40000 ALTER TABLE `incidente` DISABLE KEYS */;
/*!40000 ALTER TABLE `incidente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informacion`
--

DROP TABLE IF EXISTS `informacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `informacion` (
  `cantidadPersonas` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `reporte_id` int DEFAULT NULL,
  `barrio` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKki50o1x0t3fxt8t86j68jnhci` (`reporte_id`),
  CONSTRAINT `FKki50o1x0t3fxt8t86j68jnhci` FOREIGN KEY (`reporte_id`) REFERENCES `reportesalud` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informacion`
--

LOCK TABLES `informacion` WRITE;
/*!40000 ALTER TABLE `informacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `informacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informacion_personas`
--

DROP TABLE IF EXISTS `informacion_personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `informacion_personas` (
  `Informacion_id` int NOT NULL,
  `personas` varchar(255) DEFAULT NULL,
  KEY `FK4f0l3fnpykpkukgcq33d579x0` (`Informacion_id`),
  CONSTRAINT `FK4f0l3fnpykpkukgcq33d579x0` FOREIGN KEY (`Informacion_id`) REFERENCES `informacion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informacion_personas`
--

LOCK TABLES `informacion_personas` WRITE;
/*!40000 ALTER TABLE `informacion_personas` DISABLE KEYS */;
/*!40000 ALTER TABLE `informacion_personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juridico`
--

DROP TABLE IF EXISTS `juridico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `juridico` (
  `id` int NOT NULL,
  `razon_social` varchar(255) DEFAULT NULL,
  `tipoJuridico` enum('EMPRESA','GUBERNAMENTAL','INSTITUCION','ONG') DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKlx601o0dcvo3g9v76k9ac812m` FOREIGN KEY (`id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juridico`
--

LOCK TABLES `juridico` WRITE;
/*!40000 ALTER TABLE `juridico` DISABLE KEYS */;
INSERT INTO `juridico` VALUES (3,'coto','GUBERNAMENTAL');
/*!40000 ALTER TABLE `juridico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nviandasdisponibles`
--

DROP TABLE IF EXISTS `nviandasdisponibles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nviandasdisponibles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cantidad_de_viandas` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nviandasdisponibles`
--

LOCK TABLES `nviandasdisponibles` WRITE;
/*!40000 ALTER TABLE `nviandasdisponibles` DISABLE KEYS */;
/*!40000 ALTER TABLE `nviandasdisponibles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `observerheladera`
--

DROP TABLE IF EXISTS `observerheladera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `observerheladera` (
  `cantidad_de_viandas` int DEFAULT NULL,
  `colaborador_id` int DEFAULT NULL,
  `id_heladera` int DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tipo_subscripcion` varchar(31) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoaklxbnfbbcroi0v7qc3757nq` (`colaborador_id`),
  KEY `FKk9tbiquuiarkv4et4orachqed` (`id_heladera`),
  CONSTRAINT `FKk9tbiquuiarkv4et4orachqed` FOREIGN KEY (`id_heladera`) REFERENCES `heladera` (`id`),
  CONSTRAINT `FKoaklxbnfbbcroi0v7qc3757nq` FOREIGN KEY (`colaborador_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `observerheladera`
--

LOCK TABLES `observerheladera` WRITE;
/*!40000 ALTER TABLE `observerheladera` DISABLE KEYS */;
/*!40000 ALTER TABLE `observerheladera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ofrecerproducto`
--

DROP TABLE IF EXISTS `ofrecerproducto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ofrecerproducto` (
  `Id` int NOT NULL,
  `producto_id` int DEFAULT NULL,
  `puntos_necesarios` double DEFAULT NULL,
  `stock` int DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UKbj5yxcqapbsbxfqsgv6favuap` (`producto_id`),
  CONSTRAINT `FKjrd8a66mq4fssjwxgngbnc0jg` FOREIGN KEY (`Id`) REFERENCES `contribucion` (`Id`),
  CONSTRAINT `FKklu4ia8k5q725p4pplp0l4x94` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ofrecerproducto`
--

LOCK TABLES `ofrecerproducto` WRITE;
/*!40000 ALTER TABLE `ofrecerproducto` DISABLE KEYS */;
/*!40000 ALTER TABLE `ofrecerproducto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `alta` bit(1) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(31) NOT NULL,
  `calle` varchar(255) DEFAULT NULL,
  `codigo_de_notificacion` varchar(255) DEFAULT NULL,
  `contrasenia` varchar(255) DEFAULT NULL,
  `correo_electronico` varchar(255) DEFAULT NULL,
  `latitud` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `longitud` varchar(255) DEFAULT NULL,
  `medioDeNotificacion` varchar(255) DEFAULT NULL,
  `nombreUsuario` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `tipoUsuario` enum('ADMINISTRADOR','FISICO','JURIDICO') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (_binary '',1,'Fisico',NULL,'tduren@frba.utn.edu.ar','c4ca4238a0b923820dcc509a6f75849b','tduren@frba.utn.edu.ar',NULL,NULL,NULL,'Correo','tduren@frba.utn.edu.ar',NULL,'FISICO'),(_binary '',2,'Fisico','1','admin@admin.com','61cc0e405f4b518d264c089ac8b642ef','admin@admin.com',NULL,'barrio-norte',NULL,'Correo','admin','1','ADMINISTRADOR'),(_binary '',3,'Juridico','Honduras','tduren@frba.utn.edu.ar','48ae1b30bbaea617ba41b86ac1bc4066','tduren@frba.utn.edu.ar',NULL,'belgrano',NULL,'Correo','coto','353','JURIDICO'),(_binary '',4,'Fisico','Paparela',NULL,'2e30386cae53dbc989d91731a75a1815',NULL,NULL,'Palermo',NULL,'Correo','7512313','422','FISICO'),(_binary '',5,'Fisico','Paparela',NULL,'0bf943895a165911d18e1455c5ee19a4',NULL,NULL,'Belgrano',NULL,'Correo','5341234123','422','FISICO'),(_binary '',6,'Fisico','Paparela',NULL,'f9b06c8e59979f4ccfd96de8746576b5',NULL,NULL,'Belgrano',NULL,'Correo','45432434','422','FISICO'),(_binary '',7,'Fisico','Paparela',NULL,'a2926b49905fdc107b05e1f6f3257da5',NULL,NULL,'Belgrano',NULL,'Correo','545234234','422','FISICO'),(_binary '',8,'Fisico','Paparela',NULL,'46310eb402e184bb757862862f12606a',NULL,NULL,'Flores',NULL,'Correo','3453453','422','FISICO');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personavulnerable`
--

DROP TABLE IF EXISTS `personavulnerable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personavulnerable` (
  `fechaRegistro` date DEFAULT NULL,
  `flag_situacion_de_calle` bit(1) DEFAULT NULL,
  `id` int NOT NULL,
  `menores_a_cargo` int DEFAULT NULL,
  `tarjeta_codigo` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKp6s09ysqgdwqi68jxmu35hayi` (`tarjeta_codigo`),
  CONSTRAINT `FKk22cae1c5t3fvpvbx9ad5j7ba` FOREIGN KEY (`id`) REFERENCES `rol` (`id`),
  CONSTRAINT `FKr3p9yo3gk7558smfijmwvkyoj` FOREIGN KEY (`tarjeta_codigo`) REFERENCES `tarjeta` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personavulnerable`
--

LOCK TABLES `personavulnerable` WRITE;
/*!40000 ALTER TABLE `personavulnerable` DISABLE KEYS */;
INSERT INTO `personavulnerable` VALUES ('2024-12-16',_binary '\0',4,0,3),('2024-12-16',_binary '\0',5,0,4),('2024-12-16',_binary '\0',6,0,5),('2024-12-16',_binary '\0',7,0,6),('2024-12-16',_binary '\0',8,0,7);
/*!40000 ALTER TABLE `personavulnerable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `rubro` enum('ARTICULOS_PARA_HOGAR','ELECTRONICA','GASTRONOMIA') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro_de_uso`
--

DROP TABLE IF EXISTS `registro_de_uso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registro_de_uso` (
  `fecha` date DEFAULT NULL,
  `heladera_id` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `tarjeta_id` int DEFAULT NULL,
  `vianda_id` int DEFAULT NULL,
  `accion` enum('AGREGAR','QUITAR') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKfueenm2k9vk03npldr2mculbj` (`vianda_id`),
  KEY `FKt9sfu86s0mrf3prhokdjb6biv` (`heladera_id`),
  KEY `FKky14vx1mu2eswxh28klfkclbx` (`tarjeta_id`),
  CONSTRAINT `FKky14vx1mu2eswxh28klfkclbx` FOREIGN KEY (`tarjeta_id`) REFERENCES `tarjeta` (`codigo`),
  CONSTRAINT `FKnsq8jufd7odsxkp82k8tf5n0u` FOREIGN KEY (`vianda_id`) REFERENCES `vianda` (`id`),
  CONSTRAINT `FKt9sfu86s0mrf3prhokdjb6biv` FOREIGN KEY (`heladera_id`) REFERENCES `heladera` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro_de_uso`
--

LOCK TABLES `registro_de_uso` WRITE;
/*!40000 ALTER TABLE `registro_de_uso` DISABLE KEYS */;
INSERT INTO `registro_de_uso` VALUES ('2024-12-16',1,1,4,6,'QUITAR'),('2024-12-16',1,2,5,7,'QUITAR'),('2024-12-16',1,3,6,8,'QUITAR'),('2024-12-16',2,4,3,1,'QUITAR'),('2024-12-16',2,5,7,2,'QUITAR');
/*!40000 ALTER TABLE `registro_de_uso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro_visita_tecnica`
--

DROP TABLE IF EXISTS `registro_visita_tecnica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registro_visita_tecnica` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `id_persona` int DEFAULT NULL,
  `tecnico_visita_id` int DEFAULT NULL,
  `visitaExitosa` char(1) DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK9skgkc58vu9dcw9egacm2tbs4` (`tecnico_visita_id`),
  KEY `FKqha76a8175owsb2lsa0u50c1v` (`id_persona`),
  CONSTRAINT `FK9skgkc58vu9dcw9egacm2tbs4` FOREIGN KEY (`tecnico_visita_id`) REFERENCES `tecnico` (`id`),
  CONSTRAINT `FKqha76a8175owsb2lsa0u50c1v` FOREIGN KEY (`id_persona`) REFERENCES `incidente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro_visita_tecnica`
--

LOCK TABLES `registro_visita_tecnica` WRITE;
/*!40000 ALTER TABLE `registro_visita_tecnica` DISABLE KEYS */;
/*!40000 ALTER TABLE `registro_visita_tecnica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reporte`
--

DROP TABLE IF EXISTS `reporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reporte` (
  `fecha` date DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(31) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reporte`
--

LOCK TABLES `reporte` WRITE;
/*!40000 ALTER TABLE `reporte` DISABLE KEYS */;
/*!40000 ALTER TABLE `reporte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reporte_items`
--

DROP TABLE IF EXISTS `reporte_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reporte_items` (
  `reporte_id` int NOT NULL,
  `items` varbinary(255) DEFAULT NULL,
  KEY `FKn07icick12raccf73uqlwgci5` (`reporte_id`),
  CONSTRAINT `FKn07icick12raccf73uqlwgci5` FOREIGN KEY (`reporte_id`) REFERENCES `reporte` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reporte_items`
--

LOCK TABLES `reporte_items` WRITE;
/*!40000 ALTER TABLE `reporte_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `reporte_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reportesalud`
--

DROP TABLE IF EXISTS `reportesalud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reportesalud` (
  `fecha` date DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reportesalud`
--

LOCK TABLES `reportesalud` WRITE;
/*!40000 ALTER TABLE `reportesalud` DISABLE KEYS */;
/*!40000 ALTER TABLE `reportesalud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_persona` int DEFAULT NULL,
  `tipo` enum('COLABORADOR','TECNICO','VULNERABLE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh2xwftjm7yjmfrfffkredpo1a` (`id_persona`),
  CONSTRAINT `FKh2xwftjm7yjmfrfffkredpo1a` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,1,'COLABORADOR'),(2,2,'COLABORADOR'),(3,3,'COLABORADOR'),(4,4,'VULNERABLE'),(5,5,'VULNERABLE'),(6,6,'VULNERABLE'),(7,7,'VULNERABLE'),(8,8,'VULNERABLE');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicituddeapertura`
--

DROP TABLE IF EXISTS `solicituddeapertura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicituddeapertura` (
  `hora_limite` double DEFAULT NULL,
  `idSolicitudApertura` int NOT NULL AUTO_INCREMENT,
  `realizada` char(1) DEFAULT NULL,
  `tarjeta_id` int DEFAULT NULL,
  `accion` enum('DISTRIBUCION_VIANDAS','DONACION_DE_VIANDA','DONACION_DINERO','ENTREGA_TARJETAS','HACERSE_CARGO_DE_HELADERA','OFRECER_PRODUCTO') DEFAULT NULL,
  PRIMARY KEY (`idSolicitudApertura`),
  KEY `FKda4a3t7a2otdnq6hfgo7a6x9l` (`tarjeta_id`),
  CONSTRAINT `FKda4a3t7a2otdnq6hfgo7a6x9l` FOREIGN KEY (`tarjeta_id`) REFERENCES `tarjeta` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicituddeapertura`
--

LOCK TABLES `solicituddeapertura` WRITE;
/*!40000 ALTER TABLE `solicituddeapertura` DISABLE KEYS */;
/*!40000 ALTER TABLE `solicituddeapertura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarjeta`
--

DROP TABLE IF EXISTS `tarjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarjeta` (
  `cant_maxima_uso` int DEFAULT NULL,
  `codigo` int NOT NULL AUTO_INCREMENT,
  `colaborador_id` int DEFAULT NULL,
  `fechaUltUso` date DEFAULT NULL,
  `titular_id` int DEFAULT NULL,
  `usos_hoy` int DEFAULT NULL,
  `DTYPE` varchar(31) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `UKgkngcimft0wjmhuds8hn7fmry` (`titular_id`),
  KEY `FKmprdsfqmx1ib876n3ntvn8sdw` (`colaborador_id`),
  CONSTRAINT `FKmbrc78gmhuuq46p8cda6qdbj7` FOREIGN KEY (`titular_id`) REFERENCES `persona` (`id`),
  CONSTRAINT `FKmprdsfqmx1ib876n3ntvn8sdw` FOREIGN KEY (`colaborador_id`) REFERENCES `fisico` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarjeta`
--

LOCK TABLES `tarjeta` WRITE;
/*!40000 ALTER TABLE `tarjeta` DISABLE KEYS */;
INSERT INTO `tarjeta` VALUES (NULL,1,NULL,NULL,1,NULL,'tarjeta_accesos'),(NULL,2,NULL,NULL,2,NULL,'tarjeta_accesos'),(4,3,NULL,'2024-12-16',4,1,'tarjeta_alimentar'),(4,4,NULL,'2024-12-16',5,1,'tarjeta_alimentar'),(4,5,NULL,'2024-12-16',6,1,'tarjeta_alimentar'),(4,6,NULL,'2024-12-16',7,1,'tarjeta_alimentar'),(4,7,NULL,'2024-12-16',8,1,'tarjeta_alimentar');
/*!40000 ALTER TABLE `tarjeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tecnico`
--

DROP TABLE IF EXISTS `tecnico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tecnico` (
  `id` int NOT NULL,
  `cuil` varchar(255) DEFAULT NULL,
  `latitud` varchar(255) DEFAULT NULL,
  `longitud` varchar(255) DEFAULT NULL,
  `radio` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKi5uo6xjk7l8w9s211lvp5f92c` FOREIGN KEY (`id`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tecnico`
--

LOCK TABLES `tecnico` WRITE;
/*!40000 ALTER TABLE `tecnico` DISABLE KEYS */;
/*!40000 ALTER TABLE `tecnico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vianda`
--

DROP TABLE IF EXISTS `vianda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vianda` (
  `caloria` int DEFAULT NULL,
  `fecha_de_caducidad` date DEFAULT NULL,
  `id` int NOT NULL,
  `id_heladera` int DEFAULT NULL,
  `peso` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh4r6uaag3581y8se83vr1i2b8` (`id_heladera`),
  CONSTRAINT `FKh4r6uaag3581y8se83vr1i2b8` FOREIGN KEY (`id_heladera`) REFERENCES `heladera` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vianda`
--

LOCK TABLES `vianda` WRITE;
/*!40000 ALTER TABLE `vianda` DISABLE KEYS */;
INSERT INTO `vianda` VALUES (150,'2000-01-01',1,NULL,2,'Vianda de alimentos'),(150,'2000-01-01',2,NULL,2,'Vianda de alimentos'),(150,'2000-01-01',3,2,2,'Vianda de alimentos'),(150,'2000-01-01',4,2,2,'Vianda de alimentos'),(150,'2000-01-01',5,2,2,'Vianda de alimentos'),(150,'2000-01-01',6,NULL,2,'Vianda de alimentos'),(150,'2000-01-01',7,NULL,2,'Vianda de alimentos'),(150,'2000-01-01',8,NULL,2,'Vianda de alimentos');
/*!40000 ALTER TABLE `vianda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vianda_seq`
--

DROP TABLE IF EXISTS `vianda_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vianda_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vianda_seq`
--

LOCK TABLES `vianda_seq` WRITE;
/*!40000 ALTER TABLE `vianda_seq` DISABLE KEYS */;
INSERT INTO `vianda_seq` VALUES (101);
/*!40000 ALTER TABLE `vianda_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-16  9:49:28
