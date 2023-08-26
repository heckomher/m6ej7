CREATE DATABASE  IF NOT EXISTS `sprint7_sala3` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sprint7_sala3`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: sprint7_sala3
-- ------------------------------------------------------
-- Server version	8.0.34-0ubuntu0.22.04.1

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
-- Table structure for table `administrativo`
--

DROP TABLE IF EXISTS `administrativo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrativo` (
                                  `id_administrativo` bigint NOT NULL AUTO_INCREMENT,
                                  `area` varchar(255) DEFAULT NULL,
                                  `date_created` datetime(6) NOT NULL,
                                  `experiencia_previa` varchar(255) DEFAULT NULL,
                                  `last_updated` datetime(6) NOT NULL,
                                  `usuario_id` bigint DEFAULT NULL,
                                  PRIMARY KEY (`id_administrativo`),
                                  UNIQUE KEY `UK_b35fmrga4kg9evd9x9vk52y81` (`usuario_id`),
                                  CONSTRAINT `FKeon5cu8vmqitva2bohog01si9` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrativo`
--

LOCK TABLES `administrativo` WRITE;
/*!40000 ALTER TABLE `administrativo` DISABLE KEYS */;
INSERT INTO `administrativo` VALUES (1,'Ingeniería','2023-08-24 13:06:02.844957','12 meses','2023-08-24 23:40:54.879026',6),(2,'Contabilidad','2023-08-24 13:06:02.844957','0 meses','2023-08-24 23:40:54.879026',1),(3,'Volador','2023-08-24 22:05:09.000000','3 años','2023-08-24 22:04:54.000000',3);
/*!40000 ALTER TABLE `administrativo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `capacitacion`
--

DROP TABLE IF EXISTS `capacitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `capacitacion` (
                                `num_capacitacion` int NOT NULL AUTO_INCREMENT,
                                `cantidad_asistentes` int NOT NULL,
                                `date_created` datetime(6) NOT NULL,
                                `detalle` varchar(255) NOT NULL,
                                `dia_semana` varchar(255) DEFAULT NULL,
                                `duracion` time(6) DEFAULT NULL,
                                `hora` time(6) DEFAULT NULL,
                                `last_updated` datetime(6) NOT NULL,
                                `lugar` varchar(20) NOT NULL,
                                `nombre` varchar(255) NOT NULL,
                                PRIMARY KEY (`num_capacitacion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `capacitacion`
--

LOCK TABLES `capacitacion` WRITE;
/*!40000 ALTER TABLE `capacitacion` DISABLE KEYS */;
INSERT INTO `capacitacion` VALUES (1,21,'2023-08-22 01:49:13.409191','No te caigas','Lunes','04:00:00.000000','15:00:00.000000','2023-08-24 21:30:33.377044','Abajo del puente Lla','Uso de arnés');
/*!40000 ALTER TABLE `capacitacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checklist`
--

DROP TABLE IF EXISTS `checklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checklist` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `date_created` datetime(6) NOT NULL,
                             `descripcion` varchar(255) NOT NULL,
                             `estado` int DEFAULT NULL,
                             `fecha_check` date NOT NULL,
                             `last_updated` datetime(6) NOT NULL,
                             `usuario_id` bigint DEFAULT NULL,
                             `visita_id` bigint DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `FKa0sfbtmg0xasoccwo6t0wheed` (`usuario_id`),
                             KEY `FKhs44d5q9mo0kxpm8ycg495w78` (`visita_id`),
                             CONSTRAINT `FKa0sfbtmg0xasoccwo6t0wheed` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`),
                             CONSTRAINT `FKhs44d5q9mo0kxpm8ycg495w78` FOREIGN KEY (`visita_id`) REFERENCES `visita` (`id_visita`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checklist`
--

LOCK TABLES `checklist` WRITE;
/*!40000 ALTER TABLE `checklist` DISABLE KEYS */;
INSERT INTO `checklist` VALUES (1,'2023-08-25 23:16:16.000000','Revisar lineas , Pintado correcto y demarcado',1,'2023-08-25','2023-08-25 23:16:39.000000',5,1),(3,'2023-08-26 04:27:01.190085','Revisión de andamios ',1,'2023-08-26','2023-08-26 04:27:01.190085',5,3);
/*!40000 ALTER TABLE `checklist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
                           `id_cliente` bigint NOT NULL AUTO_INCREMENT,
                           `afp` varchar(255) DEFAULT NULL,
                           `apellidos` varchar(255) DEFAULT NULL,
                           `comuna` varchar(255) DEFAULT NULL,
                           `date_created` datetime(6) NOT NULL,
                           `direccion` varchar(255) DEFAULT NULL,
                           `edad` varchar(255) DEFAULT NULL,
                           `last_updated` datetime(6) NOT NULL,
                           `rut` varchar(12) DEFAULT NULL,
                           `sistema_salud` int DEFAULT NULL,
                           `telefono` varchar(255) DEFAULT NULL,
                           `usuario_id` bigint DEFAULT NULL,
                           PRIMARY KEY (`id_cliente`),
                           UNIQUE KEY `UK_id7jmosqg8hkqiqw4vf50xipm` (`usuario_id`),
                           CONSTRAINT `FKc3u631ocxdrtm3ccpme0kjlmu` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Pobreza xdd','Aranguiz Molina','San Bernardo','2023-08-24 19:18:09.612918','Los Maitenes 1155','46','2023-08-24 21:26:08.489632','11.125.698-8',1,'956547785',7);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacto`
--

DROP TABLE IF EXISTS `contacto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contacto` (
                            `id_contacto` int NOT NULL AUTO_INCREMENT,
                            `correo` varchar(50) NOT NULL,
                            `date_created` datetime(6) NOT NULL,
                            `last_updated` datetime(6) NOT NULL,
                            `mensaje` varchar(255) NOT NULL,
                            `nombre` varchar(20) NOT NULL,
                            PRIMARY KEY (`id_contacto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacto`
--

LOCK TABLES `contacto` WRITE;
/*!40000 ALTER TABLE `contacto` DISABLE KEYS */;
/*!40000 ALTER TABLE `contacto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos`
--

DROP TABLE IF EXISTS `pagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagos` (
                         `id_pago` bigint NOT NULL AUTO_INCREMENT,
                         `date_created` datetime(6) NOT NULL,
                         `fecha_pago` date NOT NULL,
                         `last_updated` datetime(6) NOT NULL,
                         `monto` int NOT NULL,
                         `usuario_id` bigint DEFAULT NULL,
                         PRIMARY KEY (`id_pago`),
                         KEY `FKb7sg36gdbkh7v66b8sm6bp980` (`usuario_id`),
                         CONSTRAINT `FKb7sg36gdbkh7v66b8sm6bp980` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos`
--

LOCK TABLES `pagos` WRITE;
/*!40000 ALTER TABLE `pagos` DISABLE KEYS */;
INSERT INTO `pagos` VALUES (1,'2023-08-26 03:44:59.005866','2023-08-25','2023-08-26 03:44:59.005866',350000,7);
/*!40000 ALTER TABLE `pagos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesional`
--

DROP TABLE IF EXISTS `profesional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesional` (
                               `id_profesional` bigint NOT NULL AUTO_INCREMENT,
                               `date_created` datetime(6) NOT NULL,
                               `fecha_ingreso` date NOT NULL,
                               `last_updated` datetime(6) NOT NULL,
                               `titulo` varchar(255) DEFAULT NULL,
                               `usuario_id` bigint DEFAULT NULL,
                               PRIMARY KEY (`id_profesional`),
                               UNIQUE KEY `UK_lgxydif5g0mhgysjlhlcnqyd6` (`usuario_id`),
                               CONSTRAINT `FK9uieq3kcwfmhte74wlsp9b0qs` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesional`
--

LOCK TABLES `profesional` WRITE;
/*!40000 ALTER TABLE `profesional` DISABLE KEYS */;
INSERT INTO `profesional` VALUES (1,'2023-08-24 13:04:00.674413','2023-08-06','2023-08-24 13:04:00.674413','Prevencionista',5);
/*!40000 ALTER TABLE `profesional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
                           `id_usuario` bigint NOT NULL AUTO_INCREMENT,
                           `apellido` varchar(255) DEFAULT NULL,
                           `date_created` datetime(6) NOT NULL,
                           `last_updated` datetime(6) NOT NULL,
                           `nombre` varchar(255) DEFAULT NULL,
                           `nombre_usuario` varchar(255) NOT NULL,
                           `tipo_usuario` varchar(255) NOT NULL,
                           `contrasena` varchar(255) NOT NULL,
                           PRIMARY KEY (`id_usuario`),
                           UNIQUE KEY `UK_puhr3k3l7bj71hb7hk7ktpxn0` (`nombre_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Perez','2023-08-21 23:42:16.139214','2023-08-21 23:42:16.139214','Juan','jperez','Administrativo','987654'),(3,NULL,'2023-08-24 02:16:13.571668','2023-08-24 02:16:13.571668','El Pulento','admin','Administrativo','123456'),(4,NULL,'2023-08-24 11:19:11.794402','2023-08-24 11:19:11.794402','Daniela Muñozz','mikitoledo','Administrativo','123456'),(5,NULL,'2023-08-24 13:03:55.408367','2023-08-24 13:03:55.408367','Julio Torres','julio','Profesional','123456'),(6,NULL,'2023-08-24 13:05:59.226628','2023-08-24 23:38:20.010887','Geraldine León','leong','Administrativo','123456'),(7,NULL,'2023-08-24 19:18:07.527259','2023-08-24 19:18:07.527259','Carolina','carito','Cliente','123456');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visita`
--

DROP TABLE IF EXISTS `visita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visita` (
                          `id_visita` bigint NOT NULL AUTO_INCREMENT,
                          `date_created` datetime(6) NOT NULL,
                          `detalle` varchar(255) NOT NULL,
                          `fecha_visita` date NOT NULL,
                          `last_updated` datetime(6) NOT NULL,
                          `cliente_id` bigint DEFAULT NULL,
                          `usuario_id` bigint DEFAULT NULL,
                          PRIMARY KEY (`id_visita`),
                          KEY `FKd74xc2i91nhpglql3dqlybi3y` (`cliente_id`),
                          KEY `FK3jrxhmdpecykfl1gpyihtwpk5` (`usuario_id`),
                          CONSTRAINT `FK3jrxhmdpecykfl1gpyihtwpk5` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`),
                          CONSTRAINT `FKd74xc2i91nhpglql3dqlybi3y` FOREIGN KEY (`cliente_id`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visita`
--

LOCK TABLES `visita` WRITE;
/*!40000 ALTER TABLE `visita` DISABLE KEYS */;
INSERT INTO `visita` VALUES (1,'2023-08-25 18:05:53.006641','Demarcación de lineas de transito e inspección','2023-08-10','2023-08-25 23:43:51.109506',7,5),(2,'2023-08-25 18:13:13.051611','Recorrido a las instalaciones de secado','2023-08-23','2023-08-25 23:55:37.806381',7,5),(3,'2023-08-26 03:17:33.317101','Buen estado de los andamios y certificación','2023-08-30','2023-08-26 03:17:55.898108',7,5);
/*!40000 ALTER TABLE `visita` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-26  9:53:12
