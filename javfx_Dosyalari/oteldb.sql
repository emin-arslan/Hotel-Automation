-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 30, 2021 at 04:50 PM
-- Server version: 8.0.18
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `oteldb`
--

-- --------------------------------------------------------

--
-- Table structure for table `kullanicilar`
--

CREATE TABLE `kullanicilar` (
  `id` int(11) NOT NULL,
  `k_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `k_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `k_yetki` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `kullanicilar`
--

INSERT INTO `kullanicilar` (`id`, `k_id`, `k_password`, `k_yetki`) VALUES
(1, 'lyquis', 'del123ete', 0),
(2, 'deletedark', 'del123ete', 2),
(3, 'darkdelete', 'del123ete', 1);

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
  `log_id` int(11) NOT NULL,
  `yapilan_islem` text COLLATE utf8_turkish_ci NOT NULL,
  `k_id` varchar(20) COLLATE utf8_turkish_ci NOT NULL,
  `yapilan_tarih` timestamp NOT NULL,
  `islem_yapilan_id` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `islem_yapilan_kolon` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `islem_yapilan_tablo` varchar(50) COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `logs`
--

INSERT INTO `logs` (`log_id`, `yapilan_islem`, `k_id`, `yapilan_tarih`, `islem_yapilan_id`, `islem_yapilan_kolon`, `islem_yapilan_tablo`) VALUES
(8, 'Yeni bir rezervasyon eklenmiştir.', 'lyquis', '2021-05-30 13:39:19', '19', 'tüm kolonlar', 'rez_odalar'),
(9, 'Yeni bir müsteri eklenmiştir.!', 'lyquis', '2021-05-30 13:42:18', '2323', 'Tüm kolonlar', 'musteriler'),
(10, 'Yeni bir müsteri eklenmiştir.!', 'lyquis', '2021-05-30 13:49:38', '1236548744', 'Tüm kolonlar', 'musteriler'),
(11, 'Yeni bir oda girişi yapılmıştır.', 'lyquis', '2021-05-30 13:49:46', '102', 'Tüm kolonlar', 'odalar'),
(12, 'Yeni bir oda girişi yapılmıştır.', 'lyquis', '2021-05-30 13:51:09', '104', 'Tüm kolonlar', 'odalar'),
(13, 'Yeni bir rezervasyon eklenmiştir.', 'lyquis', '2021-05-30 13:53:57', '20', 'tüm kolonlar', 'rez_odalar'),
(14, 'Eski değer:5672201425 olan değer,5672201425 olarak güncellenmiştir', 'lyquis', '2021-05-30 13:54:46', '19', 'rez_tc', 'rez_odalar'),
(15, 'Eski değer:arslanee olan değer,arslanee olarak güncellenmiştir', 'lyquis', '2021-05-30 13:54:55', '19', 'rez_soyad', 'rez_odalar'),
(16, 'Silinen rezervasyon id=19', 'lyquis', '2021-05-30 13:55:03', '19', 'Tüm kolonlar', 'rez_odalar'),
(17, 'Yeni bir müsteri eklenmiştir.!', 'lyquis', '2021-05-30 13:55:21', '1236584798', 'Tüm kolonlar', 'musteriler'),
(18, 'Yeni bir oda girişi yapılmıştır.', 'lyquis', '2021-05-30 13:55:38', '101', 'Tüm kolonlar', 'odalar'),
(19, 'Yeni bir rezervasyon eklenmiştir.', 'lyquis', '2021-05-30 14:04:49', '21', 'tüm kolonlar', 'rez_odalar'),
(20, 'Silinen rezervasyon id=21', 'lyquis', '2021-05-30 14:05:02', '21', 'Tüm kolonlar', 'rez_odalar'),
(21, 'Yeni bir rezervasyon eklenmiştir.', 'lyquis', '2021-05-30 14:05:20', '22', 'tüm kolonlar', 'rez_odalar'),
(22, 'Yeni bir rezervasyon eklenmiştir.', 'lyquis', '2021-05-30 14:06:06', '23', 'tüm kolonlar', 'rez_odalar'),
(23, 'Eski değer:eee olan değer,eee olarak güncellenmiştir', 'lyquis', '2021-05-30 14:06:29', '23', 'rez_soyad', 'rez_odalar'),
(24, 'Eski değer:2021-07-01 olan değer,2021-07-01 olarak güncellenmiştir', 'lyquis', '2021-05-30 14:06:45', '23', 'rez_gt', 'rez_odalar'),
(25, 'Yeni bir müsteri eklenmiştir.!', 'lyquis', '2021-05-30 14:07:17', '125', 'Tüm kolonlar', 'musteriler'),
(26, 'Yeni bir oda girişi yapılmıştır.', 'lyquis', '2021-05-30 14:07:53', '101', 'Tüm kolonlar', 'odalar'),
(27, 'Yeni bir oda girişi yapılmıştır.', 'lyquis', '2021-05-30 15:07:38', '102', 'Tüm kolonlar', 'odalar'),
(28, 'Yeni bir oda girişi yapılmıştır.', 'lyquis', '2021-05-30 15:13:23', '103', 'Tüm kolonlar', 'odalar'),
(29, 'Yeni bir oda girişi yapılmıştır.', 'lyquis', '2021-05-30 15:17:50', '106', 'Tüm kolonlar', 'odalar'),
(30, 'Yeni bir oda girişi yapılmıştır.', 'lyquis', '2021-05-30 15:18:25', '107', 'Tüm kolonlar', 'odalar'),
(31, 'Yeni bir oda girişi yapılmıştır.', 'lyquis', '2021-05-30 15:28:54', '105', 'Tüm kolonlar', 'odalar'),
(32, 'Yeni bir müsteri eklenmiştir.!', 'lyquis', '2021-05-30 15:35:56', '12548796321', 'Tüm kolonlar', 'musteriler'),
(33, 'Yeni bir oda girişi yapılmıştır.', 'lyquis', '2021-05-30 15:35:57', '108', 'Tüm kolonlar', 'odalar'),
(34, 'Yeni bir müsteri eklenmiştir.!', 'lyquis', '2021-05-30 15:38:04', '25486953212', 'Tüm kolonlar', 'musteriler'),
(35, 'Yeni bir oda girişi yapılmıştır.', 'lyquis', '2021-05-30 15:38:14', '101', 'Tüm kolonlar', 'odalar'),
(36, 'Yeni bir rezervasyon eklenmiştir.', 'lyquis', '2021-05-30 15:38:34', '24', 'tüm kolonlar', 'rez_odalar'),
(37, 'Eski değer:uyu olan değer,uyu olarak güncellenmiştir', 'lyquis', '2021-05-30 15:39:06', '24', 'rez_soyad', 'rez_odalar'),
(38, 'Eski değer:2021-06-04 olan değer,2021-06-04 olarak güncellenmiştir', 'lyquis', '2021-05-30 15:39:25', '24', 'rez_gt', 'rez_odalar'),
(39, 'Silinen rezervasyon id=23', 'lyquis', '2021-05-30 15:39:33', '23', 'Tüm kolonlar', 'rez_odalar'),
(40, 'Yeni bir müsteri eklenmiştir.!', 'lyquis', '2021-05-30 15:48:38', '12548756984', 'Tüm kolonlar', 'musteriler'),
(41, 'Yeni bir oda girişi yapılmıştır.', 'lyquis', '2021-05-30 15:48:41', '101', 'Tüm kolonlar', 'odalar'),
(42, 'Eski değer:TextField[id=txt_guncelle, styleClass=text-input text-field] olan değer,2021-06-10olarak güncellenmiştir', 'lyquis', '2021-05-30 15:49:24', '24', 'rez_ct', 'rez_odalar'),
(43, 'Silinen rezervasyon id=24', 'lyquis', '2021-05-30 15:49:34', '24', 'Tüm kolonlar', 'rez_odalar'),
(44, 'Yeni bir rezervasyon eklenmiştir.', 'lyquis', '2021-05-30 15:50:01', '25', 'tüm kolonlar', 'rez_odalar'),
(45, 'Yeni bir müsteri eklenmiştir.!', 'lyquis', '2021-05-30 15:58:27', '25698563214', 'Tüm kolonlar', 'musteriler'),
(46, 'Yeni bir oda girişi yapılmıştır.', 'lyquis', '2021-05-30 15:58:28', '102', 'Tüm kolonlar', 'odalar'),
(47, 'Yeni bir müsteri eklenmiştir.!', 'lyquis', '2021-05-30 16:06:53', '10236547899', 'Tüm kolonlar', 'musteriler'),
(48, 'Yeni bir oda girişi yapılmıştır.', 'lyquis', '2021-05-30 16:06:54', '101', 'Tüm kolonlar', 'odalar'),
(49, 'Silinen rezervasyon id=25', 'lyquis', '2021-05-30 16:07:09', '25', 'Tüm kolonlar', 'rez_odalar'),
(50, 'Yeni bir rezervasyon eklenmiştir.', 'lyquis', '2021-05-30 16:07:31', '26', 'tüm kolonlar', 'rez_odalar'),
(51, 'Eski değer:TextField[id=txt_guncelle, styleClass=text-input text-field] olan değer,2021-06-20olarak güncellenmiştir', 'lyquis', '2021-05-30 16:08:03', '22', 'rez_ct', 'rez_odalar'),
(52, 'Eski değer:arslanee olan değer,arslanee olarak güncellenmiştir', 'lyquis', '2021-05-30 16:08:10', '22', 'rez_soyad', 'rez_odalar'),
(53, 'Silinen rezervasyon id=26', 'lyquis', '2021-05-30 16:14:51', '26', 'Tüm kolonlar', 'rez_odalar'),
(54, 'Yeni bir rezervasyon eklenmiştir.', 'lyquis', '2021-05-30 16:15:08', '27', 'tüm kolonlar', 'rez_odalar'),
(55, 'Eski değer:TextField[id=txt_guncelle, styleClass=text-input text-field] olan değer,2021-06-21olarak güncellenmiştir', 'lyquis', '2021-05-30 16:15:34', '22', 'rez_ct', 'rez_odalar'),
(56, 'Yeni bir müsteri eklenmiştir.!', 'lyquis', '2021-05-30 16:16:03', '12036547897', 'Tüm kolonlar', 'musteriler'),
(57, 'Yeni bir oda girişi yapılmıştır.', 'lyquis', '2021-05-30 16:16:04', '101', 'Tüm kolonlar', 'odalar'),
(58, 'Yeni bir müsteri eklenmiştir.!', 'lyquis', '2021-05-30 16:16:38', '45874523158', 'Tüm kolonlar', 'musteriler'),
(59, 'Yeni bir oda girişi yapılmıştır.', 'lyquis', '2021-05-30 16:16:41', '102', 'Tüm kolonlar', 'odalar');

-- --------------------------------------------------------

--
-- Table structure for table `musteriler`
--

CREATE TABLE `musteriler` (
  `m_id` int(11) NOT NULL,
  `m_ad` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `m_soyad` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `m_tc` varchar(11) COLLATE utf8_turkish_ci DEFAULT NULL,
  `m_tel` varchar(20) COLLATE utf8_turkish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `musteriler`
--

INSERT INTO `musteriler` (`m_id`, `m_ad`, `m_soyad`, `m_tc`, `m_tel`) VALUES
(82, 'Emin', 'Arslan', '2323', NULL),
(83, 'Emin', 'Arslan', '12036598477', NULL),
(84, 'Emin', 'Arslan', '12548695321', NULL),
(85, 'e', 'nmkj', '12036598478', 'null'),
(86, 'emin', 'arslan', '01236548744', '5539862868'),
(87, 'Emin', 'Arslana', '20136584798', 'null'),
(88, 'Emin', 'Arslan', '01236584798', '5539862868'),
(89, 'Emin', 'Arslan', '12369856897', 'null'),
(90, 'Emin', 'Arslan', '12523654120', NULL),
(91, 'Emin', 'Arslan', '12365489562', NULL),
(92, 'Emin', 'Arslan', '12036589475', NULL),
(93, 'Emin', 'Arslan', '12036548795', NULL),
(94, 'Emin', 'Arslan', '10236598563', NULL),
(95, 'asda', 'asd', '12351231234', NULL),
(96, 'Emin', 'Arslan', '10236589458', NULL),
(97, 'Emin', 'Arslan', '12365985632', NULL),
(98, 'Emin', 'Arslan', '15236845987', NULL),
(99, 'Emin', 'Arslan', '15236895421', NULL),
(100, 'emin', 'Arslan', '12568952365', NULL),
(101, 'Emin', 'Arslan', '12548796321', NULL),
(102, 'Emin', 'ARSLAN', '25486953212', NULL),
(103, 'Emin', 'Arslan', '12548756984', NULL),
(104, 'Emin', 'Arslan', '25698563214', NULL),
(105, 'Emin', 'Arslan', '10236547899', NULL),
(106, 'Emin', 'Arslan', '12036547897', NULL),
(107, 'Emin', 'Arslan', '45874523158', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `odalar`
--

CREATE TABLE `odalar` (
  `oda_id` int(11) NOT NULL,
  `oda_durum` tinyint(4) NOT NULL,
  `oda_ks` tinyint(4) NOT NULL,
  `oda_sorumlu` int(11) DEFAULT NULL,
  `oda_musteri_tc` longtext CHARACTER SET utf8 COLLATE utf8_turkish_ci,
  `oda_girist` datetime DEFAULT NULL,
  `oda_cikist` datetime DEFAULT NULL,
  `kayit_tip` tinyint(4) NOT NULL,
  `oda_ucret` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `odalar`
--

INSERT INTO `odalar` (`oda_id`, `oda_durum`, `oda_ks`, `oda_sorumlu`, `oda_musteri_tc`, `oda_girist`, `oda_cikist`, `kayit_tip`, `oda_ucret`) VALUES
(101, 1, 1, NULL, '12036547897,', '2021-06-04 00:00:00', '2021-06-04 00:00:00', 0, 100),
(102, 1, 1, NULL, '45874523158,', '2021-06-05 00:00:00', '2021-06-05 00:00:00', 0, 100),
(103, 1, 1, NULL, '12036548795,', '2021-06-04 00:00:00', '2021-06-04 00:00:00', 0, 100),
(104, 1, 1, NULL, '20136584798,', '2021-05-31 00:00:00', '2021-05-31 00:00:00', 0, 100),
(105, 1, 1, NULL, '15236985624,', '2021-06-01 00:00:00', '2021-06-01 00:00:00', 0, 100),
(106, 1, 1, NULL, '12036548795,', '2021-06-01 00:00:00', '2021-06-01 00:00:00', 0, 100),
(107, 1, 1, NULL, '10236589458,', '2021-06-04 00:00:00', '2021-06-04 00:00:00', 0, 100),
(108, 1, 1, NULL, '12548796321,', '2021-06-02 00:00:00', '2021-06-02 00:00:00', 0, 100),
(109, 0, 1, NULL, NULL, NULL, NULL, 0, 100),
(201, 0, 2, NULL, NULL, NULL, NULL, 0, 200),
(202, 0, 2, NULL, NULL, NULL, NULL, 0, 200),
(203, 0, 2, NULL, NULL, NULL, NULL, 0, 200),
(204, 0, 2, NULL, NULL, NULL, NULL, 0, 200),
(205, 0, 2, NULL, NULL, NULL, NULL, 0, 200),
(206, 0, 2, NULL, NULL, NULL, NULL, 0, 200),
(207, 0, 2, NULL, NULL, NULL, NULL, 0, 200),
(208, 0, 2, NULL, NULL, NULL, NULL, 0, 200),
(209, 0, 2, NULL, NULL, NULL, NULL, 0, 200),
(301, 0, 3, NULL, NULL, NULL, NULL, 0, 300),
(302, 0, 3, NULL, NULL, NULL, NULL, 0, 300),
(303, 0, 3, NULL, NULL, NULL, NULL, 0, 300),
(304, 0, 3, NULL, NULL, NULL, NULL, 0, 300),
(305, 0, 3, NULL, NULL, NULL, NULL, 0, 300),
(306, 0, 3, NULL, NULL, NULL, NULL, 0, 300),
(307, 0, 3, NULL, NULL, NULL, NULL, 0, 300),
(308, 0, 3, NULL, NULL, NULL, NULL, 0, 300),
(309, 0, 3, NULL, NULL, NULL, NULL, 0, 300),
(401, 0, 4, NULL, NULL, NULL, NULL, 0, 400),
(402, 0, 4, NULL, NULL, NULL, NULL, 0, 400),
(403, 0, 4, NULL, NULL, NULL, NULL, 0, 400),
(404, 0, 4, NULL, NULL, NULL, NULL, 0, 400),
(405, 0, 4, NULL, NULL, NULL, NULL, 0, 400),
(406, 0, 4, NULL, NULL, NULL, NULL, 0, 400),
(407, 0, 4, NULL, NULL, NULL, NULL, 0, 400),
(408, 0, 4, NULL, NULL, NULL, NULL, 0, 400),
(409, 0, 4, NULL, NULL, NULL, NULL, 0, 400);

-- --------------------------------------------------------

--
-- Table structure for table `oda_kayitlari`
--

CREATE TABLE `oda_kayitlari` (
  `islem_id` int(11) NOT NULL,
  `oda_id` int(11) NOT NULL,
  `giris_tarih` datetime NOT NULL,
  `cikis_tarih` datetime NOT NULL,
  `musteri_idleri` varchar(50) COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `otel_gorev`
--

CREATE TABLE `otel_gorev` (
  `gorev_id` int(11) NOT NULL,
  `gorev_ad` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `gorev_tanim` text COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `otel_gorev`
--

INSERT INTO `otel_gorev` (`gorev_id`, `gorev_ad`, `gorev_tanim`) VALUES
(1, 'Genel Temizlik Personeli', 'Otel içerisinde ; resepsiyon , salon genel havuz temizliği bahçe temizliginden sorumlu olmak'),
(2, 'Oda Temizligi', 'Otel odalarinin icerisinin temizliginden sorumlu olmak , koridor temizliği ve koridorda ki tuvaletlerin temizliginden sorumlu olmak');

-- --------------------------------------------------------

--
-- Table structure for table `otel_personeli`
--

CREATE TABLE `otel_personeli` (
  `per_id` int(11) NOT NULL,
  `per_ad` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci NOT NULL,
  `per_soyad` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `per_gorev` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `otel_personeli`
--

INSERT INTO `otel_personeli` (`per_id`, `per_ad`, `per_soyad`, `per_gorev`) VALUES
(4, 'deneme99', 'denem1', 1),
(5, 'Kral', 'Lion', 1),
(7, 'denem2', 'arbs', 2);

-- --------------------------------------------------------

--
-- Table structure for table `rez_odalar`
--

CREATE TABLE `rez_odalar` (
  `rez_id` int(11) NOT NULL,
  `rez_odaid` int(4) NOT NULL,
  `rez_ad` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `rez_soyad` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `rez_tc` varchar(11) COLLATE utf8_turkish_ci NOT NULL,
  `rez_tel` varchar(20) COLLATE utf8_turkish_ci NOT NULL,
  `rez_gt` datetime NOT NULL,
  `rez_ct` datetime NOT NULL,
  `rez_aktif` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `rez_odalar`
--

INSERT INTO `rez_odalar` (`rez_id`, `rez_odaid`, `rez_ad`, `rez_soyad`, `rez_tc`, `rez_tel`, `rez_gt`, `rez_ct`, `rez_aktif`) VALUES
(20, 104, 'Emin', 'Arslan', '20136598457', '5539862868', '2021-05-31 00:00:00', '2021-06-01 00:00:00', 1),
(22, 101, 'Emin', 'arslanee', '12014587965', '5539862868', '2021-06-03 00:00:00', '2021-06-21 00:00:00', 1),
(27, 101, 'Emin', 'Arslan', '21547896523', '5539862868', '2021-06-01 00:00:00', '2021-06-03 00:00:00', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kullanicilar`
--
ALTER TABLE `kullanicilar`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`log_id`);

--
-- Indexes for table `musteriler`
--
ALTER TABLE `musteriler`
  ADD PRIMARY KEY (`m_id`),
  ADD UNIQUE KEY `m_tc` (`m_tc`);

--
-- Indexes for table `odalar`
--
ALTER TABLE `odalar`
  ADD PRIMARY KEY (`oda_id`);

--
-- Indexes for table `oda_kayitlari`
--
ALTER TABLE `oda_kayitlari`
  ADD PRIMARY KEY (`islem_id`);

--
-- Indexes for table `otel_gorev`
--
ALTER TABLE `otel_gorev`
  ADD PRIMARY KEY (`gorev_id`);

--
-- Indexes for table `otel_personeli`
--
ALTER TABLE `otel_personeli`
  ADD PRIMARY KEY (`per_id`);

--
-- Indexes for table `rez_odalar`
--
ALTER TABLE `rez_odalar`
  ADD PRIMARY KEY (`rez_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kullanicilar`
--
ALTER TABLE `kullanicilar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT for table `musteriler`
--
ALTER TABLE `musteriler`
  MODIFY `m_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=108;

--
-- AUTO_INCREMENT for table `oda_kayitlari`
--
ALTER TABLE `oda_kayitlari`
  MODIFY `islem_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `otel_gorev`
--
ALTER TABLE `otel_gorev`
  MODIFY `gorev_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `otel_personeli`
--
ALTER TABLE `otel_personeli`
  MODIFY `per_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `rez_odalar`
--
ALTER TABLE `rez_odalar`
  MODIFY `rez_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
