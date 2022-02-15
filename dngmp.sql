-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 15 Lut 2022, 23:53
-- Wersja serwera: 10.4.22-MariaDB
-- Wersja PHP: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `dngmp`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `auction`
--

CREATE TABLE `auction` (
  `id` bigint(20) NOT NULL,
  `category` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `uuid` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `auction`
--

INSERT INTO `auction` (`id`, `category`, `createdAt`, `description`, `price`, `title`, `uuid`, `user_id`) VALUES
(2, 'HOME', '2022-02-15 15:17:39', 'Nie wiem co to dokladnie jest, służyło jako ozdoba', '123.00', 'Stary przedmiot ze strychu', '89a173db-7936-4a3b-853a-14dc3636e750', 3),
(5, 'ELECTRONICS', '2022-02-15 15:18:24', 'Opis ogloszenia numer 1 Jest to czesc elektorniczna', '102.00', 'Ogloszenie 1', '93650ada-943f-4c9c-82dc-bc5921b1c3c6', 3),
(6, 'ELECTRONICS', '2022-02-15 15:18:25', 'Opis wspanialego przedmiotu na sprzedaz na tym cudownym portalu drugiej jakośći', '152.00', 'Aukcja 2', '896cb201-dbad-4e17-9ce6-dc19ba432f7e', 4),
(7, 'GARDEN', '2022-02-15 15:19:57', 'Świetnie grabie, niewiele używane ', '233.00', 'Grabie fiskars do liści', 'dc6736de-e2cb-4358-9033-8ed438dc91eb', 4);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(12);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `message`
--

CREATE TABLE `message` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `message` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `uuid` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `auction_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `message`
--

INSERT INTO `message` (`id`, `createdAt`, `message`, `uuid`, `auction_id`, `user_id`) VALUES
(8, '2022-02-15 15:44:15', 'Dzień dobry, proszę mi dodać za darmo', 'a56aa269-3b05-47af-9039-37f8b3a661ae', 2, 3),
(9, '2022-02-15 17:17:21', 'Witam, jaka cena ostateczna?', '973c6c56-724c-48fd-a2eb-d627886c7057', 7, 3),
(10, '2022-02-15 19:06:08', 'Czesc ciekawy przedmiot! Cena ostateczna?', '7717452c-9325-40d1-a442-01692e7dc278', 7, 3),
(11, '2022-02-15 19:15:57', 'Mój sąsiad sprzedał taki przedmiot połowę taniej 30 lat temu...', 'dc2ea43d-6a71-4c4e-ac2e-15909730eff0', 7, 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `login` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `phoneNumber` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `uuid` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`id`, `email`, `login`, `password`, `phoneNumber`, `uuid`) VALUES
(3, 'admin@adm.pl', 'admin', '21232f297a57a5a743894a0e4a801fc3', '12353443', '79ea4c1d-9b81-43a7-bed6-af8a24fc46cd'),
(4, 'user@usr.pl', 'user', 'ee11cbb19052e40b07aac0ca060c23ee', '123521443', '7543fc68-6d1f-4216-bacb-b8b551a79668');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `auction`
--
ALTER TABLE `auction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7uh9m3atuas3wr8vqbk9t8ixw` (`user_id`);

--
-- Indeksy dla tabeli `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmvvyqfl3exkphqk4krokgpdqx` (`auction_id`),
  ADD KEY `FKfkj0bfandt6mbdmw5dim9q7nl` (`user_id`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_587tdsv8u5cvheyo9i261xhry` (`login`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
