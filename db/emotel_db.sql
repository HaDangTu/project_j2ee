-- create db
CREATE DATABASE `emotel` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE emotel;

-- gender table
CREATE TABLE `genders` (
  `id` varchar(3) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO genders (id, name) VALUES ('G01','Nam');
INSERT INTO genders (id, name) VALUES ('G02','Nữ');
INSERT INTO genders (id, name) VALUES ('G03','Khác');

-- room_types table
CREATE TABLE `room_types` (
  `id` varchar(3) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `num_of_guest` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO room_types (id, name, num_of_guest, price) VALUES ('T01','Phòng 2 người',2,1200000);
INSERT INTO room_types (id, name, num_of_guest, price) VALUES ('T02','Phòng 4 người',4,2400000);
INSERT INTO room_types (id, name, num_of_guest, price) VALUES ('T03','Phòng 6 người',6,4800000);

-- states table
CREATE TABLE `states` (
  `id` varchar(3) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO states (id, name) VALUES ('S01','Đang ở');
INSERT INTO states (id, name) VALUES ('S02','Đã chuyển đi');

-- roles table
CREATE TABLE `roles` (
  `id` varchar(3) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO roles (id, name) VALUES ('R01', 'Owner');
INSERT INTO roles (id, name) VALUES ('R02', 'Guest');

-- accounts table 
CREATE TABLE `accounts` (
  `id` varchar(8) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `account_role_idx` (`role_id`),
  CONSTRAINT `account_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO accounts (id, username, password, role_id) VALUES ('U00001','Owner','6A8129AD617CC0354B01E986E529A2E6','R01');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00002','Room 101','4B22F47CCF5BABC51F6B38462125F730','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00003','Room 102','76E48850B9FD9C56155CDBE9E1160265','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00004','Room 103','A687473A627D926E64CF7CB38EE58051','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00005','Room 104','908D483DCE9844D2825A283C0C2E6651','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00006','Room 105','103E9BB4A66926C9F34B362F1E16BBA3','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00007','Room 106','F57C52217991C62E55B5946FE5B9CA3E','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00008','Room 107','1C121D127AF9346BC8E1C3FA4FFE761E','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00009','Room 108','ED2FBBA13A2303CF6E5F2DCCBED16515','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00010','Room 109','E2CECC8E15F0CA32FAF9FFAD1842AD94','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00011','Room 110','E1D4FEE426DD4BA8DFD6CBB15280905E','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00012','Room 201','1D9BFB3F44ACA4044D82D99A8B46BC60','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00013','Room 202','836B1BFB1E0504231B041B66155904EB','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00014','Room 203','3E876A354B10B7B9A8A4F3918985E362','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00015','Room 204','5B074BEBF6D1028861D863F21C05BA15','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00016','Room 205','8337AFEC7F7355CBD4B45967C0D23144','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00017','Room 206','BC0527F1CE22DB0FF9D61A3E22952A61','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00018','Room 207','74C0BE5E65623D4113B298409A2D3094','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00019','Room 208','58C2A5AD47B5FDC89D8B380E27EE3AA9','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00020','Room 209','7C3F5469536A5C13BB12DF416FEAF2B1','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00021','Room 210','6B54C34917049288E32039C777BBE224','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00022','Room 301','5FCDB97AB3C38A0EF9D8648067EBFB6E','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00023','Room 302','125B24841088BCBC0D4A3BDF2173ACE3','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00024','Room 303','FF8B2EE785C72DB35A2F53C2A78EA354','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00025','Room 304','345D3E80382C3B32619ECDD098F1FB42','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00026','Room 305','3F834F99B305D355B040C7818EFE80DF','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00027','Room 306','A01710706F4A0BFDBAC9C5AF77715315','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00028','Room 307','39BF8C77FC11B56DA6A8C9333D0C4159','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00029','Room 308','3DC4536987DEFD6057CD77B16B55B910','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00030','Room 309','20FF0C444261982DBFDD6A6B27EB3516','R02');
INSERT INTO accounts (id, username, password, role_id) VALUES ('U00031','Room 310','9FF6E7158674F25762A90F6C024880D3','R02');

-- rooms table
CREATE TABLE `rooms` (
  `id` varchar(5) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `room_type_id` varchar(3) DEFAULT NULL,
  `user_id` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `room_type_id` (`room_type_id`),
  KEY `rooms_ibfk_2_idx` (`user_id`),
  CONSTRAINT `rooms_ibfk_1` FOREIGN KEY (`room_type_id`) REFERENCES `room_types` (`id`),
  CONSTRAINT `rooms_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0001','Phòng 101','T01','U00002');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0002','Phòng 102','T01','U00003');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0003','Phòng 103','T01','U00004');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0004','Phòng 104','T01','U00005');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0005','Phòng 105','T01','U00006');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0006','Phòng 106','T01','U00007');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0007','Phòng 107','T01','U00008');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0008','Phòng 108','T01','U00009');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0009','Phòng 109','T01','U00010');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0010','Phòng 110','T01','U00011');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0011','Phòng 201','T02','U00012');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0012','Phòng 202','T02','U00013');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0013','Phòng 203','T02','U00014');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0014','Phòng 204','T02','U00015');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0015','Phòng 205','T02','U00016');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0016','Phòng 206','T02','U00017');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0017','Phòng 207','T02','U00018');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0018','Phòng 208','T02','U00019');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0019','Phòng 209','T02','U00020');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0020','Phòng 210','T02','U00021');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0021','Phòng 301','T03','U00022');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0022','Phòng 302','T03','U00023');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0023','Phòng 303','T03','U00024');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0024','Phòng 304','T03','U00025');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0025','Phòng 305','T03','U00026');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0026','Phòng 306','T03','U00027');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0027','Phòng 307','T03','U00028');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0028','Phòng 308','T03','U00029');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0029','Phòng 309','T03','U00030');
INSERT INTO rooms (id, name, room_type_id, user_id) VALUES ('R0030','Phòng 310','T03','U00031');

-- guests table 
CREATE TABLE `guests` (
  `id` varchar(8) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `gender_id` varchar(3) DEFAULT NULL,
  `identity_number` varchar(20) DEFAULT NULL,
  `home_town` varchar(50) DEFAULT NULL,
  `occupation` varchar(50) DEFAULT NULL,
  `state_id` varchar(3) DEFAULT NULL,
  `room_id` varchar(5) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gender_id_idx` (`gender_id`),
  KEY `state_id_idx` (`state_id`),
  KEY `room_id_idx` (`room_id`),
  CONSTRAINT `gender_id` FOREIGN KEY (`gender_id`) REFERENCES `genders` (`id`),
  CONSTRAINT `room_id` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`),
  CONSTRAINT `state_id` FOREIGN KEY (`state_id`) REFERENCES `states` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000001','Nguyễn Văn An','1996-10-10 00:00:00','G01',272748234,'Vĩnh Phúc','Công nhân','R0013','S01','2020-01-01 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000002','Hồ Thị Trang','1996-10-20 00:00:00','G02',272748235,'Vĩnh Phúc','Công nhân','R0013','S01','2020-01-01 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000003','Nguyễn Văn Bình','2010-02-03 00:00:00','G01',272748236,'Vĩnh Phúc','Học sinh','R0013','S01','2020-01-01 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000004','Nguyễn Văn Chí','2012-05-08 00:00:00','G01',272748237,'Vĩnh Phúc','Học sinh','R0013','S01','2020-01-01 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000005','Nguyễn Cẩm Tú','2000-05-20 00:00:00','G02',272748238,'Bình Phước','Sinh viên','R0001','S01','2020-01-02 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000006','Hồ Thị Huyền','2000-04-16 00:00:00','G02',272748239,'Bình Phước','Sinh viên','R0001','S01','2020-01-02 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000007','Trần Thu Trang','1994-10-10 00:00:00','G02',272748240,'Đăk Lăk','Công nhân','R0011','S01','2020-01-04 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000008','Nguyễn Hoàng Tài','1995-04-10 00:00:00','G01',272748241,'Đăk Lăk','Công nhân','R0011','S01','2020-01-04 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000009','Nguyễn Hoàng Mĩ Linh','2010-10-10 00:00:00','G02',272748242,'Đăk Lăk','Học sinh','R0011','S01','2020-01-04 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000010','Nguyễn Thu Huyền','2012-08-04 00:00:00','G02',272748243,'Đăk Lăk','Học sinh','R0011','S01','2020-01-04 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000011','Trần Văn Hoàng','1999-08-04 00:00:00','G01',272748244,'Bình Dương','Sinh viên','R0022','S01','2020-01-08 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000012','Nguyễn Quang Huy','1999-08-05 00:00:00','G01',272748245,'Bình Dương','Sinh viên','R0022','S01','2020-01-08 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000013','Huỳnh Bất Nhân','1999-08-06 00:00:00','G01',272748246,'Bình Dương','Sinh viên','R0022','S01','2020-01-08 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000014','Nguyễn Chí Tài','1999-08-07 00:00:00','G01',272748247,'Bình Dương','Sinh viên','R0022','S01','2020-01-08 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000015','Cao Văn Long','1999-08-08 00:00:00','G01',272748248,'Bình Dương','Sinh viên','R0022','S01','2020-01-08 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000016','Đỗ Hoàng Nguyên','1999-08-09 00:00:00','G01',272748249,'Bình Dương','Sinh viên','R0022','S01','2020-01-08 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000017','Nguyễn Thị Cẩm Vân','2000-10-11 00:00:00','G02',272748250,'Đồng Nai','Sinh viên','R0024','S01','2020-01-10 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000018','Ngô Trúc Thủy','2000-01-23 00:00:00','G02',272748251,'Đồng Nai','Sinh viên','R0024','S01','2020-01-10 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000019','Hồ Thị Mĩ Phượng','2000-03-04 00:00:00','G02',272748252,'Đồng Nai','Sinh viên','R0024','S01','2020-01-10 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000020','Nguyễn Thị Nga','2000-12-01 00:00:00','G02',272748253,'Đồng Nai','Sinh viên','R0024','S01','2020-01-10 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000021','Đỗ Thu Trang','2000-08-02 00:00:00','G02',272748254,'Đồng Nai','Sinh viên','R0024','S01','2020-01-10 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000022','Đỗ Văn Quốc','1995-02-03 00:00:00','G01',272748255,'Cà Mau','Nhân viên','R0004','S01','2020-01-09 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000023','Nguyễn Đình Toàn','1995-01-20 00:00:00','G01',272748256,'Quảng Nam','Nhân viên','R0014','S01','2020-01-11 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000024','Trần Huyền My','1995-02-02 00:00:00','G02',272748257,'Quảng Nam','Công nhân','R0014','S01','2020-01-11 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000025','Nguyễn Đình Tâm','2019-10-20 00:00:00','G01',272748258,'Quảng Nam','Học sinh','R0014','S01','2020-01-11 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000026','Hoàng Thu Thảo','2000-10-10 00:00:00','G02',272748259,'Long An','Sinh viên','R0015','S01','2020-01-14 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000027','Đinh Thị Thùy An','2000-01-03 00:00:00','G02',272748260,'Long An','Sinh viên','R0015','S01','2020-01-14 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000028','Nguyễn Thúy Kiều','2000-09-02 00:00:00','G02',272748261,'Long An','Sinh viên','R0015','S01','2020-01-14 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000029','Nguyễn Thanh Tâm','2000-09-23 00:00:00','G02',272748262,'Long An','Sinh viên','R0015','S01','2020-01-14 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000030','Nguyễn Minh Hiếu','1998-10-10 00:00:00','G01',272748263,'Bình Thuận','Nhân viên','R0003','S01','2020-01-15 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000031','Trương Thị Nga','1995-03-03 00:00:00','G02',272748264,'Quảng Nam','Nhân viên','R0005','S01','2020-01-16 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000032','Nguyễn Hoàng Trâm','1995-09-20 00:00:00','G02',272748265,'Quảng Nam','Nhân viên','R0005','S01','2020-01-16 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000033','Trần Trung Trực','1998-11-10 00:00:00','G01',272748266,'Gia Lai','Sinh viên','R0016','S01','2020-01-25 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000034','Trần Nam Khang','1998-09-11 00:00:00','G01',272748267,'Gia Lai','Sinh viên','R0016','S01','2020-01-25 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000035','Nguyễn Minh Nhật','1998-10-12 00:00:00','G01',272748268,'Gia Lai','Sinh viên','R0016','S01','2020-01-25 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000036','Đỗ Hoàng Khang','1998-11-13 00:00:00','G01',272748269,'Gia Lai','Sinh viên','R0016','S01','2020-01-25 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000037','Lê Thu Trang','1999-01-10 00:00:00','G02',272748270,'Nha Trang','Sinh viên','R0021','S01','2020-01-24 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000038','Nguyễn Mị Nương','1999-08-11 00:00:00','G02',272748271,'Nha Trang','Sinh viên','R0021','S01','2020-01-24 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000039','Đỗ Minh Trang','1999-10-12 00:00:00','G02',272748272,'Nha Trang','Sinh viên','R0021','S01','2020-01-24 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000040','Trần Thị Thu Huyền','1999-11-13 00:00:00','G02',272748273,'Nha Trang','Sinh viên','R0021','S01','2020-01-24 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000041','Ngô Thị Trúc','1999-12-14 00:00:00','G02',272748274,'Nha Trang','Sinh viên','R0021','S01','2020-01-24 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000042','Nguyễn Thị Thanh Thủy','1999-07-15 00:00:00','G02',272748275,'Nha Trang','Sinh viên','R0021','S01','2020-01-24 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000043','Trần Hoàng Trung','1995-09-20 00:00:00','G01',272748276,'Cà Mau','Công nhân','R0012','S01','2020-01-26 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000044','Nguyễn Văn Thanh','1995-10-21 00:00:00','G01',272748277,'Cà Mau','Công nhân','R0012','S01','2020-01-26 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000045','Đoàn Văn Đức','1995-01-22 00:00:00','G01',272748278,'Cà Mau','Công nhân','R0012','S01','2020-01-26 00:00:00');
INSERT INTO guests (id, name, birthday, gender_id, identity_number, home_town, occupation, room_id, state_id, start_date) VALUES ('G000046','Nguyễn Phước Hoàng','1995-02-23 00:00:00','G01',272748279,'Cà Mau','Công nhân','R0012','S01','2020-01-26 00:00:00');

-- power_infos table 
CREATE TABLE `power_infos` (
  `id` varchar(13) NOT NULL,
  `room_id` varchar(5) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `electricity_index` int(11) DEFAULT NULL,
  `water_index` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `room_id` (`room_id`),
  CONSTRAINT `power_infos_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000001','R0013','2020-01-01 00:00:00',12473,1273);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000002','R0001','2020-01-02 00:00:00',13740,1246);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000003','R0011','2020-01-04 00:00:00',16389,3284);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000004','R0022','2020-01-08 00:00:00',16249,1943);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000005','R0024','2020-01-10 00:00:00',11227,1231);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000006','R0004','2020-01-09 00:00:00',26183,2345);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000007','R0014','2020-01-11 00:00:00',13843,3234);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000008','R0015','2020-01-14 00:00:00',13943,1233);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000009','R0003','2020-01-15 00:00:00',17834,1234);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000010','R0005','2020-01-16 00:00:00',17393,3223);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000011','R0016','2020-01-25 00:00:00',12734,1233);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000012','R0021','2020-01-24 00:00:00',19393,1312);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000013','R0012','2020-01-26 00:00:00',12887,1364);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000014','R0013','2020-02-01 00:00:00',12623,1373);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000015','R0001','2020-02-02 00:00:00',13900,1356);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000016','R0011','2020-02-04 00:00:00',16559,3404);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000017','R0022','2020-02-08 00:00:00',16429,2073);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000018','R0024','2020-02-10 00:00:00',11417,1371);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000019','R0004','2020-02-09 00:00:00',26383,2495);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000020','R0014','2020-02-11 00:00:00',14053,3394);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000021','R0015','2020-02-14 00:00:00',14163,1403);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000022','R0003','2020-02-15 00:00:00',18064,1414);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000023','R0005','2020-02-16 00:00:00',17633,3413);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000024','R0016','2020-02-25 00:00:00',12984,1433);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000025','R0021','2020-02-24 00:00:00',19653,1522);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000026','R0012','2020-02-26 00:00:00',13157,1584);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000027','R0013','2020-03-01 00:00:00',12783,1483);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000028','R0001','2020-03-02 00:00:00',14075,1478);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000029','R0011','2020-03-04 00:00:00',16749,3538);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000030','R0022','2020-03-08 00:00:00',16634,2219);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000031','R0024','2020-03-10 00:00:00',11637,1529);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000032','R0004','2020-03-09 00:00:00',26618,2665);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000033','R0014','2020-03-11 00:00:00',14303,3576);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000034','R0015','2020-03-14 00:00:00',14428,1597);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000035','R0003','2020-03-15 00:00:00',18344,1620);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000036','R0005','2020-03-16 00:00:00',17928,3631);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000037','R0016','2020-03-25 00:00:00',13294,1663);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000038','R0021','2020-03-24 00:00:00',19978,1764);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000039','R0012','2020-03-26 00:00:00',13497,1838);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000040','R0001','2020-04-02 00:00:00',14175,1588);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000041','R0003','2020-04-15 00:00:00',18465,1730);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000042','R0004','2020-04-09 00:00:00',26719,2765);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000043','R0005','2020-04-16 00:00:00',18028,3741);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000044','R0011','2020-04-04 00:00:00',16860,3640);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000045','R0012','2020-04-26 00:00:00',13597,1938);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000046','R0013','2020-04-01 00:00:00',12900,1570);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000047','R0014','2020-04-11 00:00:00',15303,3680);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000048','R0015','2020-04-14 00:00:00',14540,1700);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000049','R0016','2020-04-25 00:00:00',13380,1773);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000050','R0021','2020-04-24 00:00:00',20123,1864);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000051','R0022','2020-04-08 00:00:00',16744,2320);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000052','R0024','2020-04-10 00:00:00',11740,1630);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000053','R0001','2020-05-02 00:00:00',14275,1688);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000054','R0003','2020-05-15 00:00:00',18565,1830);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000055','R0004','2020-05-09 00:00:00',26819,2865);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000056','R0005','2020-05-16 00:00:00',18128,3841);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000057','R0011','2020-05-04 00:00:00',16960,3740);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000058','R0012','2020-05-26 00:00:00',13697,2038);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000059','R0013','2020-05-01 00:00:00',13000,1670);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000060','R0014','2020-05-11 00:00:00',15403,3780);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000061','R0015','2020-05-14 00:00:00',14640,1820);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000062','R0016','2020-05-25 00:00:00',13480,1873);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000063','R0021','2020-05-24 00:00:00',20223,1964);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000064','R0022','2020-05-08 00:00:00',16844,2420);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000065','R0024','2020-05-10 00:00:00',11840,1730);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000066','R0001','2020-06-02 00:00:00',15300,1790);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000067','R0003','2020-06-15 00:00:00',18700,1900);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000068','R0004','2020-06-09 00:00:00',26900,2900);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000069','R0005','2020-06-16 00:00:00',18320,3941);
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000070', 'R0011', '2020-06-04 00:00:00', '17060', '3840');
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000071', 'R0012', '2020-06-26 00:00:00', '13790', '2100');
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000072', 'R0013', '2020-06-01 00:00:00', '13100', '1780');
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000073', 'R0014', '2020-06-11 00:00:00', '15503', '3880');
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000074', 'R0015', '2020-06-14 00:00:00', '14740', '1920');
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000075', 'R0016', '2020-06-25 00:00:00', '13580', '1970');
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000076', 'R0021', '2020-06-24 00:00:00', '20320', '2064');
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000077', 'R0022', '2020-06-08 00:00:00', '16940', '2520');
INSERT INTO power_infos (id, room_id, date, electricity_index, water_index) VALUES ('IEW20000078', 'R0024', '2020-06-10 00:00:00', '11940', '1830');

-- invoices table
CREATE TABLE `invoices` (
  `id` varchar(13) NOT NULL,
  `room_id` varchar(5) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `collection_date` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `debt` double DEFAULT NULL,
  `proceeds` double DEFAULT NULL,
  `excess_cash` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `room_id` (`room_id`),
  CONSTRAINT `invoices_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000001','R0013','2020-01-01 00:00:00','2020-02-03 00:00:00','Tiền điện nước tháng 1',0,650000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000002','R0013','2020-01-01 00:00:00','2020-02-03 00:00:00','Tiền phòng tháng 1',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000003','R0001','2020-01-02 00:00:00','2020-02-03 00:00:00','Tiền điện nước tháng 1',0,700000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000004','R0001','2020-01-02 00:00:00','2020-02-03 00:00:00','Tiền phòng tháng 1',0,1200000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000005','R0011','2020-01-04 00:00:00','2020-02-04 00:00:00','Tiền điện nước tháng 1',0,750000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000006','R0011','2020-01-04 00:00:00','2020-02-04 00:00:00','Tiền phòng tháng 1',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000007','R0022','2020-01-08 00:00:00','2020-02-08 00:00:00','Tiền điện nước tháng 1',0,800000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000008','R0022','2020-01-08 00:00:00','2020-02-08 00:00:00','Tiền phòng tháng 1',0,4800000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000009','R0024','2020-01-10 00:00:00','2020-02-10 00:00:00','Tiền điện nước tháng 1',0,850000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000010','R0024','2020-01-10 00:00:00','2020-02-10 00:00:00','Tiền phòng tháng 1',0,4800000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000011','R0004','2020-01-09 00:00:00','2020-02-10 00:00:00','Tiền điện nước tháng 1',0,900000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000012','R0004','2020-01-09 00:00:00','2020-02-10 00:00:00','Tiền phòng tháng 1',0,1200000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000013','R0014','2020-01-11 00:00:00','2020-02-12 00:00:00','Tiền điện nước tháng 1',0,950000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000014','R0014','2020-01-11 00:00:00','2020-02-12 00:00:00','Tiền phòng tháng 1',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000015','R0015','2020-01-14 00:00:00','2020-02-15 00:00:00','Tiền điện nước tháng 1',0,1000000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000016','R0015','2020-01-14 00:00:00','2020-02-15 00:00:00','Tiền phòng tháng 1',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000017','R0003','2020-01-15 00:00:00','2020-02-15 00:00:00','Tiền điện nước tháng 1',0,1050000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000018','R0003','2020-01-15 00:00:00','2020-02-15 00:00:00','Tiền phòng tháng 1',0,1200000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000019','R0005','2020-01-16 00:00:00','2020-02-25 00:00:00','Tiền điện nước tháng 1',0,1100000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000020','R0005','2020-01-16 00:00:00','2020-02-25 00:00:00','Tiền phòng tháng 1',0,1200000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000021','R0016','2020-01-25 00:00:00','2020-02-25 00:00:00','Tiền điện nước tháng 1',0,1150000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000022','R0016','2020-01-25 00:00:00','2020-02-25 00:00:00','Tiền phòng tháng 1',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000023','R0021','2020-01-24 00:00:00','2020-02-25 00:00:00','Tiền điện nước tháng 1',0,1200000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000024','R0021','2020-01-24 00:00:00','2020-02-25 00:00:00','Tiền phòng tháng 1',0,4800000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000025','R0012','2020-01-26 00:00:00','2020-02-27 00:00:00','Tiền điện nước tháng 1',0,1250000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000026','R0012','2020-01-26 00:00:00','2020-02-27 00:00:00','Tiền phòng tháng 1',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000027','R0013','2020-02-01 00:00:00','2020-03-03 00:00:00','Tiền điện nước tháng 2',0,700000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000028','R0013','2020-02-01 00:00:00','2020-03-03 00:00:00','Tiền phòng tháng 2',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000029','R0001','2020-02-02 00:00:00','2020-03-03 00:00:00','Tiền điện nước tháng 2',0,769000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000030','R0001','2020-02-02 00:00:00','2020-03-03 00:00:00','Tiền phòng tháng 2',0,1200000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000031','R0011','2020-02-04 00:00:00','2020-03-05 00:00:00','Tiền điện nước tháng 2',0,838000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000032','R0011','2020-02-04 00:00:00','2020-03-05 00:00:00','Tiền phòng tháng 2',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000033','R0022','2020-02-08 00:00:00','2020-03-08 00:00:00','Tiền điện nước tháng 2',0,907000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000034','R0022','2020-02-08 00:00:00','2020-03-08 00:00:00','Tiền phòng tháng 2',0,4800000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000035','R0024','2020-02-10 00:00:00','2020-03-11 00:00:00','Tiền điện nước tháng 2',0,976000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000036','R0024','2020-02-10 00:00:00','2020-03-11 00:00:00','Tiền phòng tháng 2',0,4800000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000037','R0004','2020-02-09 00:00:00','2020-03-11 00:00:00','Tiền điện nước tháng 2',0,1045000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000038','R0004','2020-02-09 00:00:00','2020-03-11 00:00:00','Tiền phòng tháng 2',0,1200000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000039','R0014','2020-02-11 00:00:00','2020-03-11 00:00:00','Tiền điện nước tháng 2',0,1114000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000040','R0014','2020-02-11 00:00:00','2020-03-11 00:00:00','Tiền phòng tháng 2',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000041','R0015','2020-02-14 00:00:00','2020-03-15 00:00:00','Tiền điện nước tháng 2',0,1183000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000042','R0015','2020-02-14 00:00:00','2020-03-15 00:00:00','Tiền phòng tháng 2',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000043','R0003','2020-02-15 00:00:00','2020-03-15 00:00:00','Tiền điện nước tháng 2',0,1252000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000044','R0003','2020-02-15 00:00:00','2020-03-15 00:00:00','Tiền phòng tháng 2',0,1200000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000045','R0005','2020-02-16 00:00:00','2020-03-17 00:00:00','Tiền điện nước tháng 2',0,1321000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000046','R0005','2020-02-16 00:00:00','2020-03-17 00:00:00','Tiền phòng tháng 2',0,1200000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000047','R0016','2020-02-25 00:00:00','2020-03-25 00:00:00','Tiền điện nước tháng 2',0,1390000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000048','R0016','2020-02-25 00:00:00','2020-03-25 00:00:00','Tiền phòng tháng 2',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000049','R0021','2020-02-24 00:00:00','2020-03-25 00:00:00','Tiền điện nước tháng 2',0,1459000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000050','R0021','2020-02-24 00:00:00','2020-03-25 00:00:00','Tiền phòng tháng 2',0,4800000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000051','R0012','2020-02-26 00:00:00','2020-02-26 00:00:00','Tiền điện nước tháng 2',0,1528000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000052','R0012','2020-02-26 00:00:00','2020-02-26 00:00:00','Tiền phòng tháng 2',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000053','R0001','2020-03-02 00:00:00','2020-04-24 00:00:00','Tiền phòng tháng 3',0,1200000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000054','R0003','2020-03-15 00:00:00','2020-04-25 00:00:00','Tiền phòng tháng 3',0,1200000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000055','R0004','2020-03-09 00:00:00','2020-04-25 00:00:00','Tiền phòng tháng 3',0,1200000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000056','R0005','2020-03-16 00:00:00','2020-04-25 00:00:00','Tiền phòng tháng 3',0,1200000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000057','R0011','2020-03-04 00:00:00','2020-04-26 00:00:00','Tiền phòng tháng 3',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000058','R0001','2020-03-02 00:00:00','2020-04-26 00:00:00','Tiền điện nước tháng 3',0,520000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000059','R0003','2020-03-15 00:00:00','2020-04-26 00:00:00','Tiền điện nước tháng 3',0,583000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000060','R0012','2020-03-26 00:00:00','2020-04-26 00:00:00','Tiền phòng tháng 3',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000061','R0013','2020-03-01 00:00:00','2020-04-26 00:00:00','Tiền phòng tháng 3',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000062','R0014','2020-03-11 00:00:00','2020-04-26 00:00:00','Tiền phòng tháng 3',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000063','R0015','2020-03-14 00:00:00','2020-04-26 00:00:00','Tiền phòng tháng 3',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000064','R0016','2020-03-25 00:00:00','2020-04-26 00:00:00','Tiền phòng tháng 3',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000065','R0021','2020-03-24 00:00:00','2020-04-26 00:00:00','Tiền phòng tháng 3',0,4800000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000066','R0022','2020-03-08 00:00:00','2020-04-26 00:00:00','Tiền phòng tháng 3',0,4800000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000067','R0024','2020-03-10 00:00:00','2020-04-26 00:00:00','Tiền phòng tháng 3',0,4800000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000068','R0004','2020-03-09 00:00:00','2020-04-26 00:00:00','Tiền điện nước tháng 3',0,503000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000069','R0005','2020-03-16 00:00:00','2020-04-26 00:00:00','Tiền điện nước tháng 3',0,520000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000070','R0011','2020-03-04 00:00:00','2020-04-26 00:00:00','Tiền điện nước tháng 3',0,537000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000071','R0012','2020-03-26 00:00:00','2020-04-26 00:00:00','Tiền điện nước tháng 3',0,500000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000072','R0013','2020-03-01 00:00:00','2020-04-26 00:00:00','Tiền điện nước tháng 3',0,525000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000073','R0014','2020-03-11 00:00:00','2020-04-26 00:00:00','Tiền điện nước tháng 3',0,3210000,2000);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000074','R0015','2020-03-14 00:00:00','2020-04-26 00:00:00','Tiền điện nước tháng 3',0,560000,18000);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000075','R0016','2020-03-25 00:00:00','2020-04-26 00:00:00','Tiền điện nước tháng 3',0,480000,2000);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000076','R0021','2020-03-24 00:00:00','2020-04-26 00:00:00','Tiền điện nước tháng 3',0,635000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000077','R0022','2020-03-08 00:00:00','2020-04-26 00:00:00','Tiền điện nước tháng 3',0,532000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000078','R0024','2020-03-10 00:00:00','2020-04-26 00:00:00','Tiền điện nước tháng 3',0,511000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000079','R0013','2020-04-01 00:00:00','2020-06-10 00:00:00','Tiền điện nước tháng 4',0,500000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000080','R0001','2020-04-02 00:00:00','2020-06-14 00:00:00','Tiền phòng tháng 4',0,1200000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000081','R0013','2020-05-01 00:00:00','2020-06-14 00:00:00','Tiền điện nước tháng 5',0,500000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000082','R0013','2020-04-01 00:00:00','2020-06-14 00:00:00','Tiền phòng tháng 4',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000083','R0013','2020-05-01 00:00:00','2020-06-14 00:00:00','Tiền phòng tháng 5',0,2400000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000084','R0003','2020-04-15 00:00:00','2020-06-14 00:00:00','Tiền phòng tháng 4',0,1100000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000085','R0014','2020-04-11 00:00:00','2020-06-14 00:00:00','Tiền phòng tháng 4',0,2300000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000086','R0003','2020-05-15 00:00:00','2020-06-17 00:00:00','Tiền phòng tháng 5',0,1300000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000087','R0001','2020-04-02 00:00:00','2020-06-17 00:00:00','Tiền điện nước tháng 4',0,500000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000088','R0001','2020-05-02 00:00:00','2020-06-17 00:00:00','Tiền điện nước tháng 5',0,500000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000089','R0001','2020-05-02 00:00:00','2020-06-20 00:00:00','Tiền phòng tháng 5',0,1200000,0);
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000090', 'R0001', '2020-07-02 00:00:00', '2020-07-17 00:00:00', 'Tiền phòng tháng 7', '0', '1200000', '0');
INSERT INTO invoices (id, room_id, date, collection_date, content, debt, proceeds, excess_cash) VALUES ('I2000000091', 'R0001', '2020-06-02 00:00:00', '2020-07-18 00:00:00', 'Tiền điện nước tháng 6', '0', '279000', '0');

-- parameters table
CREATE TABLE `parameters` (
  `id` varchar(4) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO parameters (id, name, type, value, state) VALUES ('P001','Giá điện','INT',3000, true);
INSERT INTO parameters (id, name, type, value, state) VALUES ('P002','Giá nước','INT',2000, true);

