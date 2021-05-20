-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: May 09, 2021 at 04:52 PM
-- Server version: 8.0.18
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chatapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
CREATE TABLE IF NOT EXISTS `groups` (
  `ID_GRP` int(11) NOT NULL,
  `ID_USERS` int(11) NOT NULL,
  `STATUS` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
CREATE TABLE IF NOT EXISTS `messages` (
  `ID_MESSAGE` int(11) NOT NULL AUTO_INCREMENT,
  `ID_RECIEVER` varchar(25) COLLATE utf8mb4_bin NOT NULL,
  `ID_SENDER` varchar(25) COLLATE utf8mb4_bin NOT NULL,
  `ID_GRP` int(11) NOT NULL,
  `MSG_TEXT` text COLLATE utf8mb4_bin NOT NULL,
  `DATETIME` datetime NOT NULL,
  `NAME` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `PATH` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `DATA` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`ID_MESSAGE`)
) ENGINE=MyISAM AUTO_INCREMENT=170 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`ID_MESSAGE`, `ID_RECIEVER`, `ID_SENDER`, `ID_GRP`, `MSG_TEXT`, `DATETIME`, `NAME`, `PATH`, `DATA`) VALUES
(43, 'med', 'niaa', 0, 'hola', '2021-05-07 02:16:16', '', '', ''),
(42, 'oussama', 'niaa', 0, 'cava !!', '2021-05-07 02:16:03', '', '', ''),
(131, '#1', 'med', 1, 'g', '2021-05-09 15:53:27', '', '', ''),
(41, 'oussama', 'niaa', 0, 'ahleeen', '2021-05-07 02:15:58', '', '', ''),
(40, 'niaa', 'oussama', 0, 'salam', '2021-05-07 02:15:42', '', '', ''),
(39, 'med', 'oussama', 0, 'holaa', '2021-05-07 02:15:17', '', '', ''),
(38, 'oussama', 'med', 0, 'walo?', '2021-05-07 02:15:12', '', '', ''),
(37, 'med', 'oussama', 0, 'jewbb', '2021-05-06 19:55:56', '', '', ''),
(132, '#1', 'oussama', 1, 'a', '2021-05-09 15:57:43', '', '', ''),
(133, '#1(med)', 'med', 1, 'hola', '2021-05-09 15:59:47', '', '', ''),
(134, '#1(med)', 'med', 1, 'hey', '2021-05-09 15:59:53', '', '', ''),
(123, 'med', 'oussama', 0, 'kdfv', '2021-05-09 02:31:46', '', '', ''),
(124, 'med', 'oussama', 0, 'lkdfm', '2021-05-09 02:31:47', '', '', ''),
(125, '#1', 'med', 1, 'hada grp', '2021-05-09 02:31:53', '', '', ''),
(126, 'med', 'oussama', 0, 'hada prv', '2021-05-09 02:32:01', '', '', ''),
(127, '#1', 'oussama', 1, 'hola', '2021-05-09 15:48:30', '', '', ''),
(128, '#1', 'niaa', 1, 'ahlaan', '2021-05-09 15:48:36', '', '', ''),
(129, '#1', 'niaa', 1, 'cava', '2021-05-09 15:48:53', '', '', ''),
(130, '#1', 'oussama', 1, 'a', '2021-05-09 15:53:19', '', '', ''),
(122, 'med', 'oussama', 0, ',mdf,', '2021-05-09 02:31:45', '', '', ''),
(121, 'oussama', 'med', 0, 'kn', '2021-05-09 02:31:39', '', '', ''),
(120, 'oussama', 'med', 0, 'lsdmlk', '2021-05-09 02:31:39', '', '', ''),
(119, 'oussama', 'med', 0, 'lkdsl', '2021-05-09 02:31:37', '', '', ''),
(67, 'oussama', 'med', 0, 'a', '2021-05-09 01:34:31', '', '', ''),
(169, 'med', 'oussama', 0, 'si a ba oussama', '2021-05-09 16:47:23', '', '', ''),
(168, 'oussama', 'med', 0, 'HADA PRv a khay med', '2021-05-09 16:47:17', '', '', ''),
(167, '#1', 'oussama', 1, 'ANA OUSSAMA F GRPPPPP', '2021-05-09 16:47:07', '', '', ''),
(166, '#1', 'med', 1, 'hada med in GRP', '2021-05-09 16:47:00', '', '', ''),
(165, '#1', 'med', 1, 'anaaa meed', '2021-05-09 16:46:55', '', '', ''),
(164, '#1', 'oussama', 1, 'ana oussama', '2021-05-09 16:46:49', '', '', ''),
(163, 'med', 'oussama', 0, 'ana med', '2021-05-09 16:45:13', '', '', ''),
(162, 'oussama', 'med', 0, 'ana oussama', '2021-05-09 16:45:09', '', '', ''),
(161, 'oussama', 'med', 0, 'akher msg oussama', '2021-05-09 16:40:27', '', '', ''),
(160, 'med', 'oussama', 0, 'akher msg med', '2021-05-09 16:40:16', '', '', ''),
(159, 'med', 'oussama', 0, 'holaa', '2021-05-09 16:40:12', '', '', ''),
(158, 'oussama', 'med', 0, 'hola', '2021-05-09 16:40:06', '', '', ''),
(157, 'oussama', 'med', 0, 'b', '2021-05-09 16:35:02', '', '', ''),
(156, 'med', 'oussama', 0, 'a', '2021-05-09 16:35:00', '', '', ''),
(155, 'med', 'oussama', 0, 'xx', '2021-05-09 16:33:17', '', '', ''),
(154, 'oussama', 'med', 0, 'xx', '2021-05-09 16:33:15', '', '', ''),
(153, 'oussama', 'med', 0, 'hola', '2021-05-09 16:33:02', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `ID_USER` int(11) NOT NULL AUTO_INCREMENT,
  `LOGIN` varchar(25) COLLATE utf8mb4_bin NOT NULL,
  `PASSWORD` varchar(25) COLLATE utf8mb4_bin NOT NULL,
  `ID_GRP` int(11) NOT NULL,
  `STATUS` int(11) NOT NULL,
  `LAST_CONN` datetime NOT NULL,
  PRIMARY KEY (`ID_USER`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`ID_USER`, `LOGIN`, `PASSWORD`, `ID_GRP`, `STATUS`, `LAST_CONN`) VALUES
(2, 'oussama', '12345', 1, 0, '2021-05-06 07:21:21'),
(14, 'med', '123', 1, 0, '2021-05-08 20:47:56'),
(12, 'niaa', 'niaa', 1, 0, '2021-05-06 11:17:41'),
(13, 'ousmar', 'ousmar', 1, 0, '2021-05-08 11:22:11');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
