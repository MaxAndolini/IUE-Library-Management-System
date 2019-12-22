-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 22 Ara 2019, 13:34:17
-- Sunucu sürümü: 10.1.38-MariaDB
-- PHP Sürümü: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `iuelibrary`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `books`
--

CREATE TABLE `books` (
  `ID` int(11) NOT NULL,
  `title` varchar(64) COLLATE utf8_turkish_ci NOT NULL,
  `author` varchar(64) COLLATE utf8_turkish_ci NOT NULL,
  `categoryid` int(11) NOT NULL,
  `shelfid` int(11) NOT NULL,
  `available` int(11) NOT NULL DEFAULT '-1',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `books`
--

INSERT INTO `books` (`ID`, `title`, `author`, `categoryid`, `shelfid`, `available`, `createdate`, `updatedate`) VALUES
(1, 'Harry Potter and the Prisoner of Azkaban', 'J.K. Rowling', 21, 1, -1, '2019-12-18 20:43:46', '2019-12-21 17:02:38'),
(2, 'Never Cry Wolf', 'Farley Mowat', 32, 1, -1, '2019-12-20 00:24:21', '2019-12-22 12:30:30'),
(3, 'The Great Gatsby', 'F. Scott Fitzgerald', 3, 2, -1, '2019-12-22 12:27:26', '2019-12-22 12:27:26'),
(4, 'Wuthering Heights', 'Emily Brontë', 3, 2, -1, '2019-12-22 12:27:46', '2019-12-22 12:27:46'),
(5, 'Frankenstein', 'Mary Wollstonecraft Shelley', 3, 2, -1, '2019-12-22 12:28:04', '2019-12-22 12:28:04'),
(6, 'Deadly Stakes', 'J.A. Jance', 5, 1, -1, '2019-12-22 12:28:47', '2019-12-22 12:28:47'),
(7, 'Bad Monkey', 'Carl Hiaasen', 5, 1, -1, '2019-12-22 12:29:07', '2019-12-22 12:29:07'),
(8, 'Midnight in the Garden of Good and Evil: A Savannah Story', 'John Berendt', 32, 1, -1, '2019-12-22 12:29:34', '2019-12-22 12:29:34'),
(9, 'PMP Exam Prep : Rita\'s Course in a Book for Passing the PMP Exam', 'Rita Mulcahy', 32, 1, -1, '2019-12-22 12:29:53', '2019-12-22 12:29:53'),
(10, 'On Writing', 'Stephen King', 24, 2, -1, '2019-12-22 12:31:26', '2019-12-22 12:31:26'),
(11, 'In the Dream House : A Memoir', 'Carmen Maria Machado', 24, 2, -1, '2019-12-22 12:31:50', '2019-12-22 12:31:50'),
(12, 'The Battle for Skandia', 'John Flanagan', 11, 1, -1, '2019-12-22 12:32:39', '2019-12-22 12:32:39'),
(13, 'A Good Night for Ghosts : Magic Tree House', 'Mary Pope Osborne', 11, 1, -1, '2019-12-22 12:32:58', '2019-12-22 12:32:58');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `borrowedbooks`
--

CREATE TABLE `borrowedbooks` (
  `ID` int(11) NOT NULL,
  `bookid` int(11) NOT NULL,
  `borrowedby` int(11) NOT NULL DEFAULT '-1',
  `borrowedto` int(11) NOT NULL DEFAULT '-1',
  `borrowdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `retrievedby` int(11) NOT NULL DEFAULT '-1',
  `retrievedate` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `borrowedbooks`
--

INSERT INTO `borrowedbooks` (`ID`, `bookid`, `borrowedby`, `borrowedto`, `borrowdate`, `retrievedby`, `retrievedate`) VALUES
(1, 1, 1, 1, '2019-12-21 11:02:54', -1, NULL),
(2, 2, 1, 1, '2019-12-21 11:03:20', 1, '2019-12-21 17:02:43'),
(5, 2, 1, 1, '2019-12-21 17:07:58', 1, '2019-12-21 17:10:27'),
(6, 2, 1, 1, '2019-12-22 00:16:27', 1, '2019-12-22 00:21:11'),
(7, 2, 1, 2, '2019-12-22 00:22:53', 1, '2019-12-22 00:22:59'),
(8, 2, 1, 2, '2019-12-22 00:29:52', 1, '2019-12-22 00:30:01');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `categories`
--

CREATE TABLE `categories` (
  `ID` int(11) NOT NULL,
  `name` varchar(64) COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `categories`
--

INSERT INTO `categories` (`ID`, `name`) VALUES
(1, 'Action and Adventure'),
(2, 'Anthology'),
(3, 'Classic'),
(4, 'Comic and Graphic Novel'),
(5, 'Crime and Detective'),
(6, 'Drama'),
(7, 'Fable'),
(8, 'Fairy Tale'),
(9, 'Fan-Fiction'),
(10, 'Fantasy'),
(11, 'Historical Fiction'),
(12, 'Horror'),
(13, 'Humor'),
(14, 'Legend'),
(15, 'Magical Realism'),
(16, 'Mystery'),
(17, 'Mythology'),
(18, 'Realistic Fiction'),
(19, 'Romance'),
(20, 'Satire'),
(21, 'Science Fiction (Sci-Fi)'),
(22, 'Short Story'),
(23, 'Suspense/Thriller'),
(24, 'Biography/Autobiography'),
(25, 'Essay'),
(26, 'Memoir'),
(27, 'Narrative Nonfiction'),
(28, 'Periodicals'),
(29, 'Reference Books'),
(30, 'Self-help Book'),
(31, 'Speech'),
(32, 'Textbook'),
(33, 'Poetry');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `shelfs`
--

CREATE TABLE `shelfs` (
  `ID` int(11) NOT NULL,
  `name` varchar(64) COLLATE utf8_turkish_ci NOT NULL,
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `shelfs`
--

INSERT INTO `shelfs` (`ID`, `name`, `createdate`, `updatedate`) VALUES
(1, 'Shelf A-1', '2019-12-20 00:29:06', '2019-12-22 12:33:27'),
(2, 'Shelf A-2', '2019-12-22 03:53:46', '2019-12-22 12:33:31');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `users`
--

CREATE TABLE `users` (
  `ID` int(11) NOT NULL,
  `username` varchar(64) COLLATE utf8_turkish_ci NOT NULL,
  `firstname` varchar(32) COLLATE utf8_turkish_ci NOT NULL,
  `lastname` varchar(32) COLLATE utf8_turkish_ci NOT NULL,
  `password` varchar(64) COLLATE utf8_turkish_ci NOT NULL,
  `securitypassword` varchar(64) COLLATE utf8_turkish_ci NOT NULL,
  `color` varchar(10) COLLATE utf8_turkish_ci NOT NULL DEFAULT '',
  `abilitylevel` int(1) NOT NULL,
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `users`
--

INSERT INTO `users` (`ID`, `username`, `firstname`, `lastname`, `password`, `securitypassword`, `color`, `abilitylevel`, `createdate`, `updatedate`) VALUES
(1, 'ercan', 'Ercan', 'Acar', 'deneme', 'guvenlik', '0xb36680ff', 2, '2019-12-19 17:46:43', '2019-12-22 03:50:59'),
(2, 'deneme', 'Test', 'Deneme', 'asdas', 'adasdasdas', '', 0, '2019-12-21 20:54:58', '2019-12-22 03:51:38'),
(3, 'riza', 'Rıza', 'Alaca', 'asdas', 'dsfdf', '', 1, '2019-12-21 22:40:00', '2019-12-22 12:22:40'),
(4, 'meto', 'Mehmet', 'Selim', 'asdas', 'sifremiz', '0x804d80ff', 0, '2019-12-21 22:40:00', '2019-12-22 12:23:17'),
(5, 'kadir', 'kadir', 'Gürlü', 'dfdfd', 'dfdfzdfd', '', 0, '2019-12-21 22:40:00', '2019-12-22 12:23:17');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`ID`);

--
-- Tablo için indeksler `borrowedbooks`
--
ALTER TABLE `borrowedbooks`
  ADD PRIMARY KEY (`ID`);

--
-- Tablo için indeksler `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`ID`);

--
-- Tablo için indeksler `shelfs`
--
ALTER TABLE `shelfs`
  ADD PRIMARY KEY (`ID`);

--
-- Tablo için indeksler `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `books`
--
ALTER TABLE `books`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Tablo için AUTO_INCREMENT değeri `borrowedbooks`
--
ALTER TABLE `borrowedbooks`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Tablo için AUTO_INCREMENT değeri `categories`
--
ALTER TABLE `categories`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- Tablo için AUTO_INCREMENT değeri `shelfs`
--
ALTER TABLE `shelfs`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Tablo için AUTO_INCREMENT değeri `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
