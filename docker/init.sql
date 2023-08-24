CREATE DATABASE IF NOT EXISTS mininews;

-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: mininews
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `news_item_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoy8lqpwp270y999urt9t6hi36` (`news_item_id`),
  CONSTRAINT `FKoy8lqpwp270y999urt9t6hi36` FOREIGN KEY (`news_item_id`) REFERENCES `news_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;


    INSERT INTO comment (text, created_at, news_item_id) VALUES
        ('Can''t believe it!', CONVERT_TZ(NOW(), @@session.time_zone, '+00:00'), 8),
        ('Impressive work!', CONVERT_TZ(NOW(), @@session.time_zone, '+00:00'), 8),
        ('Looking forward to more.', CONVERT_TZ(NOW(), @@session.time_zone, '+00:00'), 8),
        ('Incredible findings.', CONVERT_TZ(NOW(), @@session.time_zone, '+00:00'), 7),
        ('Mind-blowing technology.', CONVERT_TZ(NOW(), @@session.time_zone, '+00:00'), 6),
        ('Great insights.', CONVERT_TZ(NOW(), @@session.time_zone, '+00:00'), 6),
        ('Worth the read.', CONVERT_TZ(NOW(), @@session.time_zone, '+00:00'), 6),
        ('Important information.', CONVERT_TZ(NOW(), @@session.time_zone, '+00:00'), 5),
        ('Interesting perspective.', CONVERT_TZ(NOW(), @@session.time_zone, '+00:00'), 5),
        ('New comment', CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00'), 5),
        ('Another new comment', CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00'), 4),
        ('Yet another new comment', CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00'), 3),
        ('Another random comment', CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00'), 2),
        ('Yet another random comment', CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00'), 2),
        ('More random comments', CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00'), 1),
        ('Last random comment', CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00'), 1);


/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news_item`
--

DROP TABLE IF EXISTS `news_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `version` bigint DEFAULT 0,
  `votes` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_item`
--

LOCK TABLES `news_item` WRITE;
/*!40000 ALTER TABLE `news_item` DISABLE KEYS */;

INSERT INTO news_item (title, link, votes, created_at) VALUES
    ('Breaking News: New Scientific Discovery', 'https://example.com/scientific-discovery', FLOOR(RAND() * 100), CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00')),
    ('Tech Giant Unveils Revolutionary Product', 'https://example.com/revolutionary-product', FLOOR(RAND() * 100), CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00')),
    ('Health Tips for a Better Lifestyle', 'https://example.com/health-tips', FLOOR(RAND() * 100), CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00')),
    ('New Movie Release Receives Rave Reviews', 'https://example.com/new-movie-release', FLOOR(RAND() * 100), CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00')),
    ('Investors Watch as Stock Market Fluctuates', 'https://example.com/stock-market-fluctuations', FLOOR(RAND() * 100), CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00')),
    -- Add more random news items with varying titles, links, votes, and timestamps
    ('Art Exhibition Showcases Local Talent', 'https://example.com/art-exhibition', FLOOR(RAND() * 100), CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00')),
    ('Travel Destinations for Your Next Getaway', 'https://example.com/travel-destinations', FLOOR(RAND() * 100), CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00')),
    ('New Study Reveals Surprising Findings', 'https://example.com/surprising-findings', FLOOR(RAND() * 100), CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00')),
    -- Add more random news items as needed
    ('Fashion Trends That Are Making a Comeback', 'https://example.com/fashion-trends', FLOOR(RAND() * 100), CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00')),
    ('Local Community Comes Together for Charity', 'https://example.com/community-charity', FLOOR(RAND() * 100), CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00')),
    ('New Culinary Delights Await Food Enthusiasts', 'https://example.com/culinary-delights', FLOOR(RAND() * 100), CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00')),
    ('Tech Industry Leaders Discuss Future Trends', 'https://example.com/future-tech-trends', FLOOR(RAND() * 100), CONVERT_TZ(NOW() - INTERVAL FLOOR(RAND() * 432000) SECOND, @@session.time_zone, '+00:00'));

/*!40000 ALTER TABLE `news_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'mininews'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-24  1:25:52


