-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 16, 2022 lúc 10:30 AM
-- Phiên bản máy phục vụ: 10.4.20-MariaDB
-- Phiên bản PHP: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quan-ly-lop-hoc`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `academic_staff`
--

CREATE TABLE `academic_staff` (
  `id` int(11) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `room`
--

CREATE TABLE `room` (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `student`
--

CREATE TABLE `student` (
  `mssv` varchar(255) CHARACTER SET utf8 NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `student_subject`
--

CREATE TABLE `student_subject` (
  `id` int(11) NOT NULL,
  `mssv` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `subject_code` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `session_one` tinyint(1) DEFAULT NULL,
  `session_two` tinyint(1) DEFAULT NULL,
  `session_three` tinyint(1) DEFAULT NULL,
  `session_four` tinyint(1) DEFAULT NULL,
  `session_five` tinyint(1) DEFAULT NULL,
  `session_six` tinyint(1) DEFAULT NULL,
  `session_seven` tinyint(1) DEFAULT NULL,
  `session_eight` tinyint(1) DEFAULT NULL,
  `session_nine` tinyint(1) DEFAULT NULL,
  `session_ten` tinyint(1) DEFAULT NULL,
  `session_eleven` tinyint(1) DEFAULT NULL,
  `session_twelve` tinyint(1) DEFAULT NULL,
  `session_thirteen` tinyint(1) DEFAULT NULL,
  `session_fourteen` tinyint(1) DEFAULT NULL,
  `session_fifteen` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `subject`
--

CREATE TABLE `subject` (
  `code` varchar(255) CHARACTER SET utf8 NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `date_start` date DEFAULT NULL,
  `date_end` date DEFAULT NULL,
  `day_of_week` int(11) DEFAULT NULL,
  `time_start` timestamp NULL DEFAULT NULL,
  `time_end` timestamp NULL DEFAULT NULL,
  `id_room` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `academic_staff`
--
ALTER TABLE `academic_staff`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `academic_staff_username_uindex` (`username`);

--
-- Chỉ mục cho bảng `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`mssv`),
  ADD UNIQUE KEY `student_username_uindex` (`username`);

--
-- Chỉ mục cho bảng `student_subject`
--
ALTER TABLE `student_subject`
  ADD PRIMARY KEY (`id`),
  ADD KEY `student_subject_student_mssv_fk` (`mssv`),
  ADD KEY `student_subject_subject_code_fk` (`subject_code`);

--
-- Chỉ mục cho bảng `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`code`),
  ADD KEY `subject_room_id_fk` (`id_room`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `academic_staff`
--
ALTER TABLE `academic_staff`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `student_subject`
--
ALTER TABLE `student_subject`
  ADD CONSTRAINT `student_subject_student_mssv_fk` FOREIGN KEY (`mssv`) REFERENCES `student` (`mssv`),
  ADD CONSTRAINT `student_subject_subject_code_fk` FOREIGN KEY (`subject_code`) REFERENCES `subject` (`code`);

--
-- Các ràng buộc cho bảng `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `subject_room_id_fk` FOREIGN KEY (`id_room`) REFERENCES `room` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
