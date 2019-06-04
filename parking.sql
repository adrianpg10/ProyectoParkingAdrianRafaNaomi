-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: parking6
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `clientes` (
  `matricula` char(7) NOT NULL,
  `dni` char(9) DEFAULT NULL,
  `nombre` varchar(10) DEFAULT NULL,
  `apellido1` varchar(20) DEFAULT NULL,
  `apellido2` varchar(20) DEFAULT NULL,
  `numTarjetaCredito` char(16) DEFAULT NULL,
  `tipoAbono` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`matricula`),
  CONSTRAINT `fk_clientes_vehiculos` FOREIGN KEY (`matricula`) REFERENCES `vehiculos` (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plazas`
--

DROP TABLE IF EXISTS `plazas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `plazas` (
  `numplaza` int(11) NOT NULL,
  `tipoPlaza` varchar(100) DEFAULT NULL,
  `estadoplaza` varchar(100) DEFAULT NULL,
  `tarifa` decimal(10,2) DEFAULT NULL,
  `estadoReservado` int(11) DEFAULT NULL,
  PRIMARY KEY (`numplaza`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reservas` (
  `matricula` char(7) NOT NULL,
  `numplaza` int(11) NOT NULL,
  `pin_fijo` char(6) DEFAULT NULL,
  `feciniabono` date DEFAULT NULL,
  `fecfinabono` date DEFAULT NULL,
  `importe` int(11) DEFAULT NULL,
  PRIMARY KEY (`matricula`,`numplaza`),
  UNIQUE KEY `pin_fijo` (`pin_fijo`),
  KEY `fk_reservas_plazas` (`numplaza`),
  CONSTRAINT `fk_reservas_plazas` FOREIGN KEY (`numplaza`) REFERENCES `plazas` (`numplaza`),
  CONSTRAINT `fk_reservas_vehiculos` FOREIGN KEY (`matricula`) REFERENCES `vehiculos` (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tickets` (
  `codticket` int(11) NOT NULL,
  `numplaza` int(11) DEFAULT NULL,
  `matricula` char(7) NOT NULL,
  `pin_desechable` char(6) DEFAULT NULL,
  `fecinipin` date DEFAULT NULL,
  `fecfinpin` date DEFAULT NULL,
  `horaInicio` time DEFAULT NULL,
  `horaFin` time DEFAULT NULL,
  `importeAbonado` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`codticket`,`matricula`),
  KEY `fk_tickets_vehiculos` (`matricula`),
  KEY `fk_tickets_plazas` (`numplaza`),
  CONSTRAINT `fk_tickets_plazas` FOREIGN KEY (`numplaza`) REFERENCES `plazas` (`numplaza`),
  CONSTRAINT `fk_tickets_vehiculos` FOREIGN KEY (`matricula`) REFERENCES `vehiculos` (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vehiculos`
--

DROP TABLE IF EXISTS `vehiculos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vehiculos` (
  `matricula` char(7) NOT NULL,
  `tipoVehiculo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-04 11:11:55
