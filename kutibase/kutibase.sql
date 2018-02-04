-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 26.01.2018 klo 13:56
-- Palvelimen versio: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kutibase`
--

-- --------------------------------------------------------

--
-- Rakenne taululle `lukijat`
--

CREATE TABLE `lukijat` (
  `ovi_ID` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Vedos taulusta `lukijat`
--

INSERT INTO `lukijat` (`ovi_ID`) VALUES
('U1'),
('U2'),
('U3'),
('S1'),
('S2'),
('S3'),
('S4');

-- --------------------------------------------------------

--
-- Rakenne taululle `tapahtumat`
--

CREATE TABLE `tapahtumat` (
  `aika` timestamp NOT NULL,
  `ovi_ID` varchar(2) NOT NULL,
  `user_ID` int(4) NOT NULL,
  `nimi` varchar(30) NOT NULL,
  `virheet` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Vedos taulusta `tapahtumat`
--

INSERT INTO `tapahtumat` (`aika`, `ovi_ID`, `user_ID`, `nimi`, `virheet`) VALUES
('2017-12-17 00:00:59', 'U1', 1002, 'Benson George', 0),
('2017-12-17 00:01:43', 'S1', 1011, 'invalid user', 1),
('2017-12-17 00:05:28', 'S1', 1004, 'Havukas Hellevi', 0),
('2017-12-17 00:09:57', 'U1', 1008, 'Redford Eerikki', 0),
('2017-12-17 00:17:29', 'S1', 1007, 'Singh Rainer', 0),
('2017-12-17 00:23:18', 'U3', 1003, 'Jormakka Jorma', 0),
('2017-12-17 00:30:12', 'S2', 1006, 'Kurttulainen Dimitri', 0),
('2017-12-17 00:30:35', 'U1', 1011, 'invalid user', 1),
('2017-12-17 00:33:19', 'U2', 1007, 'Singh Rainer', 0),
('2017-12-17 00:43:40', 'S1', 1008, 'Redford Eerikki', 0),
('2017-12-17 00:43:52', 'U3', 1001, 'Vahanen Risto', 0),
('2017-12-17 00:48:37', 'U2', 1003, 'Jormakka Jorma', 0),
('2017-12-17 00:54:37', 'U3', 1008, 'Redford Eerikki', 0),
('2017-12-17 00:56:46', 'S1', 1004, 'Havukas Hellevi', 0),
('2017-12-17 01:04:09', 'S3', 1002, 'Benson George', 0),
('2017-12-17 01:07:07', 'S4', 1008, 'Redford Eerikki', 0),
('2017-12-17 01:13:19', 'U2', 1011, 'invalid user', 1),
('2017-12-17 01:28:11', 'U3', 1010, 'Asell Mauritzio', 0),
('2017-12-17 01:29:01', 'S1', 1007, 'Singh Rainer', 0),
('2017-12-17 01:30:23', 'U1', 1010, 'Asell Mauritzio', 0),
('2017-12-17 01:34:59', 'U1', 1007, 'Singh Rainer', 0),
('2017-12-17 01:37:05', 'S4', 1004, 'Havukas Hellevi', 0),
('2017-12-17 01:45:15', 'S4', 1002, 'Benson George', 0),
('2017-12-17 01:45:42', 'S1', 1001, 'Vahanen Risto', 0),
('2017-12-17 01:47:40', 'S2', 1006, 'Kurttulainen Dimitri', 0),
('2017-12-17 01:48:02', 'S4', 1001, 'Vahanen Risto', 0),
('2017-12-17 01:49:12', 'S4', 1006, 'Kurttulainen Dimitri', 0);

-- --------------------------------------------------------

--
-- Rakenne taululle `users`
--

CREATE TABLE `users` (
  `ID` int(4) NOT NULL,
  `Nimi` varchar(30) NOT NULL,
  `PIN` int(4) NOT NULL,
  `OikeusSisa` varchar(8) NOT NULL,
  `OikeusUlko` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Vedos taulusta `users`
--

INSERT INTO `users` (`ID`, `Nimi`, `PIN`, `OikeusSisa`, `OikeusUlko`) VALUES
(1001, 'Vahanen Risto', 1122, 'N', 'Y'),
(1002, 'Benson George', 2233, 'N', 'Y'),
(1003, 'Jormakka Jorma', 3344, 'N', 'Y'),
(1004, 'Havukas Hellevi', 4455, 'N', 'Y'),
(1005, 'Sippola Mercedes', 5566, 'N', 'Y'),
(1006, 'Kurttulainen Dimitri', 6677, 'Y', 'Y'),
(1007, 'Singh Rainer', 7788, 'Y', 'Y'),
(1008, 'Redford Eerikki', 8899, 'Y', 'Y'),
(1009, 'Kankkunen Ritva', 9900, 'Y', 'Y'),
(1010, 'Asell Mauritzio', 1234, 'Y', 'Y');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
