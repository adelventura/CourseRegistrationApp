-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: students
-- ------------------------------------------------------
-- Server version	8.0.11

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
-- Table structure for table `lee_table`
--

DROP TABLE IF EXISTS `lee_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lee_table` (
  `Course_Num` int(11) NOT NULL,
  `Course_Name` varchar(45) NOT NULL,
  `Days` varchar(45) DEFAULT NULL,
  `Time` varchar(45) DEFAULT NULL,
  `Credits` int(11) NOT NULL,
  `Instructor` varchar(45) DEFAULT NULL,
  `Room_Number` int(11) NOT NULL,
  `department` varchar(45) NOT NULL,
  PRIMARY KEY (`Course_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lee_table`
--

LOCK TABLES `lee_table` WRITE;
/*!40000 ALTER TABLE `lee_table` DISABLE KEYS */;
INSERT INTO `lee_table` VALUES (2211,'Calculus of One Variable I','T/R','2:45p.m.-4:00p.m.',4,'Smith',50,'Math'),(2420,'Discrete Mathematics','M/W','12:00p.m.-1:15p.m.',3,'Bishop',90,'Math');
/*!40000 ALTER TABLE `lee_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_list`
--

DROP TABLE IF EXISTS `student_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student_list` (
  `student id` int(11) NOT NULL AUTO_INCREMENT,
  `First_Name` varchar(45) NOT NULL,
  `Last_Name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`student id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_list`
--

LOCK TABLES `student_list` WRITE;
/*!40000 ALTER TABLE `student_list` DISABLE KEYS */;
INSERT INTO `student_list` VALUES (1,'Stacy','James','stacyG@gmail.com'),(2,'Stacy','James','stacyG@gmail.com'),(3,'Sandra','Long','sandraL@gmail.com'),(4,'James','Lee','jamesl@gmail.com'),(5,'James','Lee','jamesl@gmail.com');
/*!40000 ALTER TABLE `student_list` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-07 14:30:17
-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: department_tables
-- ------------------------------------------------------
-- Server version	8.0.11

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
-- Table structure for table `all_courses`
--

DROP TABLE IF EXISTS `all_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `all_courses` (
  `Course_Num` int(11) NOT NULL,
  `Course_Name` varchar(45) NOT NULL,
  `Days` varchar(45) NOT NULL,
  `Time` varchar(45) NOT NULL,
  `Credits` int(11) NOT NULL,
  `Instructor` varchar(45) NOT NULL,
  `Room_Number` int(11) NOT NULL,
  `department` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Course_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `all_courses`
--

LOCK TABLES `all_courses` WRITE;
/*!40000 ALTER TABLE `all_courses` DISABLE KEYS */;
INSERT INTO `all_courses` VALUES (2211,'Calculus of One Variable I','T/R','2:45p.m.-4:00p.m.',4,'Smith',50,'Math'),(2212,'Calculus of One Variable II','M/W','8:00a.m.-9:15a.m.',4,'Lee',733,'Math'),(1111,'College Algebra','T/R','11:45a.m.-1:00p.m.',3,'Garcia',4,'Math'),(4210,'Computer Architecture','T/R','2:45p.m.-4:00p.m.',4,'Bishop',308,'Computer Science'),(3210,'Computer Org & Programming','T/R','8:00a.m.-9:15a.m.',3,'Roberts',705,'Computer Science'),(2720,'Data Structures','M/W','12:00p.m.-1:15p.m.',3,'Lozano',2223,'Computer Science'),(2420,'Discrete Mathematics','M/W','12:00p.m.-1:15p.m.',3,'Bishop',90,'Math'),(1101,'Intro to Chem I','M/W','12:00p.m.-1:15p.m.',4,'Jones',101,'Chemistry'),(1102,'Intro to Chem II','M/W','8:00a.m.-9:15a.m.',4,'Smith',205,'Chemistry'),(1111,'Intro to Physics I','T/R/F','5:00p.m.-6:15p.m.',4,'Bishop',656,'Physics'),(1112,'Intro to Physics II','T/R/F','2:45p.m.-4:00p.m.',4,'Lee',700,'Physics'),(3030,'Mathematical Models for Computer Science','T/R','5:00p.m.-6:15p.m.',3,'Roberts',6,'Math'),(3401,'Modern Physics I','T/R','1:00p.m.-3:15p.m.',4,'Atkins',273,'Physics'),(3402,'Modern Physics II','T/R','5:00p.m.-6:15p.m.',4,'Garcia',120,'Physics'),(4320,'Operating Systems','T/R','11:45a.m.-1:00p.m.',4,'Atkins',506,'Computer Science'),(2400,'Organic Chem','M/W/F','5:00p.m.-6:15p.m.',4,'Atkins',545,'Chemistry'),(3410,'Organic Chem II','T/R/F','12:00p.m.-1:15p.m.',4,'Garcia',1,'Chemistry'),(1113,'Precalculus','M/W','1:00p.m.-3:15p.m.',3,'Lozano',1000,'Math'),(1211,'Principles of Chem I','M/W','1:00p.m.-3:15p.m.',4,'Bishop',2,'Chemistry'),(1212,'Principles of Chem II','M/W','2:45p.m.-4:00p.m.',4,'Lozano',19,'Chemistry'),(1301,'Principles of Computer Science','M/W/F','1:00p.m.-3:15p.m.',3,'Lozano',351,'Computer Science'),(1302,'Principles of Computer Science II','M/W/F','1:00p.m.-3:15p.m.',3,'Lee',789,'Computer Science'),(2211,'Principles of Physics I','T/R','12:00p.m.-1:15p.m.',4,'Roberts',255,'Physics'),(2212,'Principles of Physics II','M/W','8:00a.m.-9:15a.m.',4,'Smith',988,'Physics'),(4330,'Programming Language Concepts','T/R','5:00p.m.-6:15p.m.',4,'Smith',209,'Computer Science'),(1151,'Survey of Chem I','T/R','10:00a.m.-11:15a.m.',4,'Lee',233,'Chemistry'),(1152,'Survey of Chem II','T/R','11:45a.m.-1:00p.m.',4,'Roberts',455,'Chemistry'),(3320,'System-Level Programming','M/W','11:45a.m.-1:00p.m.',3,'Atkins',707,'Computer Science'),(4370,'Web Programming','M/W','1:00p.m.-3:15p.m.',4,'Garcia',555,'Computer Science');
/*!40000 ALTER TABLE `all_courses` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-07 14:30:18
