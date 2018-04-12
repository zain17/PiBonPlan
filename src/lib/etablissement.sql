-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 09 avr. 2018 à 19:19
-- Version du serveur :  5.7.19
-- Version de PHP :  7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bonplan`
--

-- --------------------------------------------------------

--
-- Structure de la table `etablissement`
--

DROP TABLE IF EXISTS `etablissement`;
CREATE TABLE IF NOT EXISTS `etablissement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adresse` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gouvernorat` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ville` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `note` double DEFAULT NULL,
  `horraire` datetime DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `est_active` tinyint(1) DEFAULT NULL,
  `description` longtext COLLATE utf8_unicode_ci,
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `horraire_f` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `etablissement`
--

INSERT INTO `etablissement` (`id`, `nom`, `adresse`, `gouvernorat`, `ville`, `note`, `horraire`, `longitude`, `latitude`, `est_active`, `description`, `type`, `photo`, `horraire_f`) VALUES
(15, 'ZainEtab', 'Rue', 'Ariana', 'Ville', 10, '1970-01-01 06:00:00', 36.22, 21.22, 1, 'desc', 'cafe', '7def146befe6bb7622eea34a56afe332.jpeg', NULL),
(16, 'Have FOOD', 'addresse', 'Ariana', 'Ville', 10, '1970-01-01 18:19:00', 36.8598543, 10.1965471, 1, 'Desc', 'cafe', '07ea6b3ac24620ec50b7b6da29b03e55.jpeg', NULL),
(17, 'OuniEtablissement', 'Adresse', 'Ariana', 'Ville', 12, '1970-01-01 16:16:00', 36.8598543, 10.1965471, 1, 'Description', 'cafe', '7ad7524eb58ac312417099176c0bae33.jpeg', NULL),
(18, 'Pino', 'adresse', 'Ariana', 'Ville', 10, '1970-01-01 01:01:00', 36.8598543, 10.1965471, 1, 'description', 'restaurant', 'e19e2b4cbfe600b694caebebdc39dbd1.jpeg', NULL),
(19, 'Hope', 'adresse', 'Ariana', 'Ville', 20, '1970-01-01 17:18:00', 36, 20, 1, 'Description', 'cafe', 'f3b0826c30cfb7123e3774cd71ec804c.jpeg', NULL),
(20, 'EtablissementA', 'Rue', 'Gouv', 'Ville', 10, '1907-08-07 00:00:00', 10, 12, 1, 'Photo', 'cafe', 'descr', NULL),
(21, 'EtablissementA', 'Rue', 'Gouv', 'Ville', 10, '1907-08-07 00:00:00', 10, 12, 1, 'Photo', 'cafe', 'descr', NULL),
(22, NULL, NULL, 'Ariana', 'Ville', NULL, '1970-01-01 00:00:00', NULL, NULL, 0, NULL, 'cafe', '025d790e1c8ff20d065670f0fe33c482.jpeg', NULL),
(23, NULL, NULL, 'Ariana', 'Ville', NULL, '1970-01-01 00:00:00', NULL, NULL, 0, NULL, 'cafe', '6d67f780962498521917fa5892d99f80.jpeg', NULL),
(24, NULL, NULL, 'Ariana', 'Ville', NULL, '1970-01-01 00:00:00', NULL, NULL, 0, NULL, 'cafe', '0ed8fc403a6de982fc545ec813876d0e.jpeg', NULL),
(25, NULL, NULL, 'Ariana', 'Ville', NULL, '1970-01-01 00:00:00', NULL, NULL, 0, NULL, 'cafe', 'f413a50ba68146e091b9780a703b124b.jpeg', NULL),
(26, NULL, NULL, 'Ariana', 'Ville', NULL, '1970-01-01 00:00:00', NULL, NULL, 0, NULL, 'cafe', '57b96034bec985d909d18ad1f5cce8e6.jpeg', NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
