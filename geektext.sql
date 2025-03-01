CREATE DATABASE  IF NOT EXISTS `geektext` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `geektext`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: geektext
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `publisher_id` int unsigned NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `biography` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `publisher_id` (`publisher_id`),
  CONSTRAINT `author_ibfk_1` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,1,'author1FirstName','author1LastName','author1Biography'),(2,8,'author2FirstName','author2LastName','author2Biography'),(3,2,'author3FirstName','author3LastName','author3Biography'),(4,7,'author4FirstName','author4LastName','author4Biography'),(5,6,'author5FirstName','author5LastName','author5Biography'),(6,3,'author6FirstName','author6LastName','author6Biography'),(7,10,'author7FirstName','author7LastName','author7Biography'),(8,9,'author8FirstName','author8LastName','author8Biography'),(9,4,'author9FirstName','author9LastName','author9Biography'),(10,5,'author10FirstName','author10LastName','author10Biography');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `isbn` bigint unsigned NOT NULL,
  `author_id` int unsigned NOT NULL,
  `publisher_id` int unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `book_description` varchar(500) DEFAULT NULL,
  `price` decimal(5,2) NOT NULL,
  `genre` varchar(30) DEFAULT NULL,
  `year_published` date NOT NULL,
  `copies_sold` int unsigned NOT NULL DEFAULT '0',
  `avg_rating` decimal(3,2) DEFAULT NULL,
  PRIMARY KEY (`isbn`),
  KEY `author_id` (`author_id`),
  KEY `publisher_id` (`publisher_id`),
  KEY `avg_rating` (`avg_rating`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  CONSTRAINT `book_ibfk_2` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (9781212367897,7,10,'The Infinite Loop','A mind-bending sci-fi story about a hacker trapped in a time loop',18.50,'Science Fiction','2024-10-13',100000,2.00),(9781231047897,6,3,'Code Breakers','A thriller about cyber warfare and intelligence leaks',22.50,'Thriller','2021-03-19',300000,1.50),(9781234100297,5,6,'Echoes of the Deep','Marine biologists uncover a lost civilization beneath the ocean',20.99,'Adventure','2019-12-25',450000,4.20),(9781234142897,2,8,'The Mind’s Labyrinth','A psychological thriller that blurs the line between dreams and reality',21.25,'Psychological Thriller','2016-08-05',900000,4.80),(9781234567897,1,1,'The Quantum Paradox','A physicist unravels a mysterious quantum anomaly',19.99,'Science Fiction','2022-01-31',500000,4.00),(9781234577497,4,7,'Celestial Ties','A romance novel set among the stars, exploring love and destiny',13.75,'Romance','2023-07-04',200000,4.25),(9781234627897,3,2,'Shadows of the Forgotten','A historical fiction novel set in 18th-century France',14.99,'Historical','2018-02-10',750000,3.00),(9781234991897,8,9,'Arcane Codex','A fantasy epic about a lost magical book that reshapes the world',17.99,'Fantasy','2015-09-16',500000,4.90),(9781294877897,10,5,'The Last Outpost','A dystopian novel about survival after global collapse',16.50,'Dystopian','2017-03-14',1200000,3.00),(9781534567832,9,4,'Artificial Minds','An AI researcher develops a sentient program',18.99,'Sci-Fi','2020-05-25',600000,5.00);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `user_id` int unsigned NOT NULL,
  `book_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`book_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,9781212367897),(2,9781231047897),(4,9781231047897),(1,9781234577497),(5,9781234577497),(6,9781234577497),(3,9781234991897),(4,9781294877897),(8,9781294877897),(4,9781534567832);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments_on`
--

DROP TABLE IF EXISTS `comments_on`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments_on` (
  `user_id` int unsigned NOT NULL,
  `book_id` bigint unsigned NOT NULL,
  `comment` varchar(500) NOT NULL,
  `post_date` date NOT NULL,
  PRIMARY KEY (`user_id`,`book_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `comments_on_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `comments_on_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments_on`
--

LOCK TABLES `comments_on` WRITE;
/*!40000 ALTER TABLE `comments_on` DISABLE KEYS */;
INSERT INTO `comments_on` VALUES (1,9781212367897,'comment1','2025-02-10'),(2,9781234577497,'comment3','2023-11-14'),(2,9781294877897,'comment10','2020-01-17'),(3,9781212367897,'comment2','2024-03-02'),(4,9781231047897,'comment8','2022-03-04'),(5,9781234577497,'comment4','2022-08-12'),(6,9781294877897,'comment9','2021-12-11'),(8,9781234567897,'comment6','2016-04-23'),(8,9781534567832,'comment7','2019-06-26'),(9,9781234627897,'comment5','2017-07-05');
/*!40000 ALTER TABLE `comments_on` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_card`
--

DROP TABLE IF EXISTS `credit_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credit_card` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `card_num` bigint unsigned NOT NULL,
  `cvv` varchar(4) NOT NULL,
  `exp_date` date NOT NULL,
  `cardholder` varchar(50) NOT NULL,
  PRIMARY KEY (`id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `credit_card_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_card`
--

LOCK TABLES `credit_card` WRITE;
/*!40000 ALTER TABLE `credit_card` DISABLE KEYS */;
INSERT INTO `credit_card` VALUES (1,1,9000111122223333,'123','2025-12-01','John Doe'),(2,2,4000111122227777,'456','2024-09-01','Jane Smith'),(3,3,5000111122224444,'789','2026-05-01','Michael Johnson'),(4,4,6000111166663333,'234','2027-03-01','Emily Brown'),(5,4,7000111188883333,'567','2028-07-01','David White'),(6,7,7000111199993333,'890','2028-06-01','Sarah Lee'),(7,8,7000111177773333,'321','2025-02-01','William Green'),(8,8,2000111122225555,'654','2026-06-01','Olivia Adams'),(9,9,3000555522223333,'987','2024-04-01','Ethan Harris'),(10,10,1000777722223333,'432','2027-08-01','Sophia Clark');
/*!40000 ALTER TABLE `credit_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lists`
--

DROP TABLE IF EXISTS `lists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lists` (
  `wishlist_id` int unsigned NOT NULL,
  `user_id` int unsigned NOT NULL,
  `book_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`wishlist_id`,`user_id`,`book_id`),
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `lists_ibfk_1` FOREIGN KEY (`wishlist_id`) REFERENCES `wishlist` (`id`),
  CONSTRAINT `lists_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `lists_ibfk_3` FOREIGN KEY (`book_id`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lists`
--

LOCK TABLES `lists` WRITE;
/*!40000 ALTER TABLE `lists` DISABLE KEYS */;
INSERT INTO `lists` VALUES (1,1,9781212367897),(1,1,9781234100297),(3,3,9781212367897),(5,4,9781234627897),(7,4,9781234991897),(4,5,9781234567897),(9,6,9781534567832),(6,8,9781234627897),(6,8,9781294877897),(10,10,9781231047897);
/*!40000 ALTER TABLE `lists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publisher` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `publisher_name` varchar(40) NOT NULL,
  `discount` decimal(3,2) NOT NULL DEFAULT '1.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'Nova Press',1.00),(2,'Heritage Books',0.85),(3,'CyberEdge Publishing',0.95),(4,'FutureTech Reads',0.88),(5,'Endgame Press',0.80),(6,'Oceanic Books',0.92),(7,'Starbound Publishing',1.00),(8,'MindMaze Books',0.93),(9,'Mystic Quill',0.90),(10,'Timewarp Publishing',0.94);
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rates`
--

DROP TABLE IF EXISTS `rates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rates` (
  `user_id` int unsigned NOT NULL,
  `book_id` bigint unsigned NOT NULL,
  `rating` decimal(3,2) NOT NULL,
  `rate_date` date DEFAULT NULL,
  PRIMARY KEY (`user_id`,`book_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `rates_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `rates_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rates`
--

LOCK TABLES `rates` WRITE;
/*!40000 ALTER TABLE `rates` DISABLE KEYS */;
INSERT INTO `rates` VALUES (1,9781234577497,4.50,'2015-05-17'),(2,9781294877897,4.00,'2022-11-09'),(3,9781234627897,3.00,'2019-03-31'),(4,9781294877897,2.00,'2018-12-31'),(6,9781212367897,0.50,'2025-02-10'),(6,9781234577497,4.00,'2020-01-09'),(7,9781234567897,4.00,'2023-02-28'),(8,9781212367897,3.50,'2011-03-11'),(8,9781534567832,5.00,'2020-06-19'),(10,9781231047897,1.50,'2013-10-10');
/*!40000 ALTER TABLE `rates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `user_password` varchar(30) NOT NULL,
  `full_name` varchar(50) DEFAULT NULL,
  `email_address` varchar(30) DEFAULT NULL,
  `home_address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'user_Number_1','passwordOfUser1','user1s Name','my_user1@gmail.com','3336 Simpson Square'),(2,'user_Number_2','passwordOfUser2','user2s Name','my_user2@gmail.com','2381 Tea Berry Lane'),(3,'user_Number_3','passwordOfUser3','user3s Name','my_user3@gmail.com',NULL),(4,'user_Number_4','passwordOfUser4','user4s Name','my_user4@gmail.com','2227 Lucy Lane'),(5,'user_Number_5','passwordOfUser5',NULL,'my_user0@gmail.com','1439 Parrill Court'),(6,'user_Number_6','passwordOfUser6','user6s Name','my_user6@gmail.com','2269 Rinehart Road'),(7,'user_Number_7','passwordOfUser7','user7s Name',NULL,NULL),(8,'user_Number_8','passwordOfUser8','user8s Name','my_user8@gmail.com','2596 Marietta Street'),(9,'user_Number_9','passwordOfUser9',NULL,'my_user9@gmail.com','4731 Fulton Street'),(10,'user_Number_10','passwordOfUser10',NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wishlist` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `wishlist_name` varchar(50) NOT NULL DEFAULT 'Untitled Wishlist',
  PRIMARY KEY (`id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `wishlist_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (1,1,'wishlist1'),(2,1,'wishlist2'),(3,3,'wishlist3'),(4,5,'wishlist4'),(5,4,'wishlist5'),(6,8,'wishlist6'),(7,4,'wishlist7'),(8,7,'wishlist8'),(9,6,'wishlist9'),(10,10,'wishlist10');
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'geektext'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-13 17:22:20
