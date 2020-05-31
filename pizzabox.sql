-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 31 mai 2020 à 11:01
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `pizzabox`
--

-- --------------------------------------------------------

--
-- Structure de la table `t_composer`
--

DROP TABLE IF EXISTS `t_composer`;
CREATE TABLE IF NOT EXISTS `t_composer` (
  `id` int(11) NOT NULL,
  `numIngredient` int(11) NOT NULL,
  PRIMARY KEY (`id`,`numIngredient`),
  KEY `id` (`id`,`numIngredient`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `t_composer`
--

INSERT INTO `t_composer` (`id`, `numIngredient`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15),
(1, 16),
(2, 1),
(3, 1),
(3, 9),
(4, 1),
(4, 2),
(4, 3),
(4, 4),
(4, 5),
(4, 6),
(4, 7),
(4, 8),
(4, 9),
(4, 10),
(4, 11),
(4, 12),
(4, 13),
(4, 14),
(4, 15),
(4, 16),
(5, 1),
(5, 12),
(5, 13),
(5, 14),
(5, 15),
(5, 16),
(6, 2),
(6, 7),
(6, 14),
(7, 1),
(7, 2),
(7, 4),
(7, 14),
(7, 15),
(10, 1),
(10, 3),
(10, 4),
(10, 5),
(10, 6),
(10, 7),
(10, 9),
(10, 10),
(10, 11),
(10, 12),
(10, 13),
(10, 15),
(10, 16),
(12, 4),
(12, 5),
(12, 6),
(12, 16),
(13, 1),
(13, 2),
(13, 3),
(13, 4),
(13, 5),
(13, 6),
(13, 7),
(13, 9),
(13, 11),
(13, 12),
(13, 15),
(13, 16),
(15, 1),
(15, 5),
(15, 10),
(15, 11),
(523, 1),
(523, 2),
(523, 5),
(523, 9),
(524, 15),
(524, 16),
(525, 2),
(525, 4),
(526, 2),
(527, 1),
(527, 12),
(527, 16);

-- --------------------------------------------------------

--
-- Structure de la table `t_ingredient`
--

DROP TABLE IF EXISTS `t_ingredient`;
CREATE TABLE IF NOT EXISTS `t_ingredient` (
  `numIngredient` int(11) NOT NULL AUTO_INCREMENT,
  `nomIngredient` varchar(255) NOT NULL,
  PRIMARY KEY (`numIngredient`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `t_ingredient`
--

INSERT INTO `t_ingredient` (`numIngredient`, `nomIngredient`) VALUES
(1, 'Crème fraiche'),
(2, 'Sauce Tomate'),
(3, 'Ognions'),
(4, 'Olives'),
(5, 'Câpres'),
(6, 'Oeuf'),
(7, 'Lardons'),
(8, 'Jambon de dinde'),
(9, 'Poulet'),
(10, 'Thon'),
(11, 'Mozzarella'),
(12, 'Emmental'),
(13, 'Chèvre'),
(14, 'Boursin'),
(15, 'Gouda'),
(16, 'Champignions');

-- --------------------------------------------------------

--
-- Structure de la table `t_pizza`
--

DROP TABLE IF EXISTS `t_pizza`;
CREATE TABLE IF NOT EXISTS `t_pizza` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `DesignPizz` varchar(255) DEFAULT NULL,
  `TarifPizz` decimal(5,2) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT 'notfound.png',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=528 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

--
-- Déchargement des données de la table `t_pizza`
--

INSERT INTO `t_pizza` (`id`, `DesignPizz`, `TarifPizz`, `description`, `image`) VALUES
(1, 'La Regina', '10.00', 'Regina est la meilleure pizza', '1.jpg\r\n'),
(2, 'La Seguin', '13.50', 'ezrz', '2.jpg\r\n'),
(3, 'La From', '14.50', 'teet', '3.jpg'),
(4, 'La Fermière', '12.50', '', '4.jpg'),
(5, 'Le Chausson', '13.00', '', '5.jpg'),
(6, 'La Provençale', '13.00', '', '6.jpg'),
(7, 'La Caramba', '14.50', '', '7.jpg'),
(8, 'La tomate', '14.50', '', '8.jpg'),
(9, 'La Gourmande', '14.50', '', '9.jpg'),
(10, 'La primeure', '12.50', '', '10.jpg'),
(11, 'La Péloponèse', '14.50', '', '11.jpg'),
(12, 'La Savoyarde', '14.50', '', 'notfound.png'),
(13, 'la Pêcheur', '13.50', '', '13.jpg'),
(14, 'greenwinch', '65.00', '', '14.jpg'),
(15, 'La camenbert', '45.00', '', '15.png'),
(16, 'saumon', '27.00', '', '16.png'),
(17, 'saumon fumé ', '37.50', '', '3.jpg'),
(18, 'saumon fumé new', '120.00', '', '15.jpg');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
