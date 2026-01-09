-- Tạo bảng LoaiVanBan
CREATE TABLE LoaiVanBan (
loaiVanBanId INT PRIMARY KEY AUTO_INCREMENT,
tenLoai NVARCHAR(255) NOT NULL
);

-- Tạo bảng CoQuanDonVi
CREATE TABLE CoQuanDonVi (
coQuanDonViId INT PRIMARY KEY AUTO_INCREMENT,
tenCoQuanDonVi NVARCHAR(255) NOT NULL
);

-- Tạo bảng ChucVu
CREATE TABLE ChucVu (
chucVuId INT PRIMARY KEY AUTO_INCREMENT,
tenChucVu NVARCHAR(255) NOT NULL
);

-- Tạo bảng NguoiKy
CREATE TABLE NguoiKy (
nguoiKyId INT PRIMARY KEY AUTO_INCREMENT,
hoTenNguoiKy NVARCHAR(255) NOT NULL,
coQuanDonViId INT NOT NULL,
CONSTRAINT FK_NguoiKy_CoQuanDonVi FOREIGN KEY (coQuanDonViId) REFERENCES CoQuanDonVi(coQuanDonViId)
);

-- Tạo bảng NguoiDung
CREATE TABLE NguoiDung (
userId INT PRIMARY KEY AUTO_INCREMENT,
hoTen NVARCHAR(255) NOT NULL,
email NVARCHAR(255),
soDienThoai NVARCHAR(50),
tenDangNhap NVARCHAR(100) NOT NULL UNIQUE,
matKhau NVARCHAR(255) NOT NULL
);

-- Tạo bảng NguoiKyGiuChucVu (bảng liên kết n-n giữa NguoiKy và ChucVu)
CREATE TABLE NguoiKyGiuChucVu (
nguoiKyId INT NOT NULL,
chucVuId INT NOT NULL,
ngayBatDau DATE,
PRIMARY KEY (nguoiKyId, chucVuId),
CONSTRAINT FK_NguoiKyGiuChucVu_NguoiKy FOREIGN KEY (nguoiKyId) REFERENCES NguoiKy(nguoiKyId),
CONSTRAINT FK_NguoiKyGiuChucVu_ChucVu FOREIGN KEY (chucVuId) REFERENCES ChucVu(chucVuId)
);

-- Tạo bảng VanBanHanhChinh
CREATE TABLE VanBanHanhChinh (
Id INT PRIMARY KEY AUTO_INCREMENT,
trichYeu NVARCHAR(1000),
soHieu NVARCHAR(100),
ngayDen DATE,
ngayBanHanh DATE,
tepDinhKem NVARCHAR(255),
loaiVanBanId INT NOT NULL,
coQuanBanHanhId INT NOT NULL,
donViPhoBienId INT NOT NULL,
nguoiKyId INT NOT NULL,
userId INT NOT NULL,
CONSTRAINT FK_VBHC_LoaiVanBan FOREIGN KEY (loaiVanBanId) REFERENCES LoaiVanBan(loaiVanBanId),
CONSTRAINT FK_VBHC_CoQuanBanHanh FOREIGN KEY (coQuanBanHanhId) REFERENCES CoQuanDonVi(coQuanDonViId),
CONSTRAINT FK_VBHC_DonViPhoBien FOREIGN KEY (donViPhoBienId) REFERENCES CoQuanDonVi(coQuanDonViId),

[//]: # (CONSTRAINT FK_VBHC_NguoiKy FOREIGN KEY &#40;nguoiKyId&#41; REFERENCES NguoiKy&#40;nguoiKyId&#41;,)
CONSTRAINT FK_VBHC_NguoiDung FOREIGN KEY (userId) REFERENCES NguoiDung(userId)
);

CREATE TABLE TinhThanhPho (
tinhThanhPhoId VARCHAR(100) PRIMARY KEY,
tenTinhThanhPho NVARCHAR(255) NOT NULL
);

ALTER TABLE CoQuanDonVi
ADD tinhThanhPhoId VARCHAR(100),
ADD CONSTRAINT FK_CoQuanDonVi_TinhThanhPho FOREIGN KEY (tinhThanhPhoId) REFERENCES 
TinhThanhPho(tinhThanhPhoId);


ALTER TABLE VanBanHanhChinh
ADD chucVuId VARCHAR(100),
ADD CONSTRAINT FK_VanBanHanhChinh_NguoiKyGiuChucVu
FOREIGN KEY (nguoiKyId, chucVuId)
REFERENCES NguoiKyGiuChucVu(nguoiKyId, chucVuId);


INSERT INTO TinhThanhPho (tinhThanhPhoId, tenTinhThanhPho) VALUES
(UUID(), N'An Giang'),
(UUID(), N'Bà Rịa - Vũng Tàu'),
(UUID(), N'Bắc Giang'),
(UUID(), N'Bắc Kạn'),
(UUID(), N'Bạc Liêu'),
(UUID(), N'Bắc Ninh'),
(UUID(), N'Bến Tre'),
(UUID(), N'Bình Định'),
(UUID(), N'Bình Dương'),
(UUID(), N'Bình Phước'),
(UUID(), N'Bình Thuận'),
(UUID(), N'Cà Mau'),
(UUID(), N'Cần Thơ'),
(UUID(), N'Cao Bằng'),
(UUID(), N'Đà Nẵng'),
(UUID(), N'Đắk Lắk'),
(UUID(), N'Đắk Nông'),
(UUID(), N'Điện Biên'),
(UUID(), N'Đồng Nai'),
(UUID(), N'Đồng Tháp'),
(UUID(), N'Gia Lai'),
(UUID(), N'Hà Giang'),
(UUID(), N'Hà Nam'),
(UUID(), N'Hà Nội'),
(UUID(), N'Hà Tĩnh'),
(UUID(), N'Hải Dương'),
(UUID(), N'Hải Phòng'),
(UUID(), N'Hậu Giang'),
(UUID(), N'Hòa Bình'),
(UUID(), N'Hưng Yên'),
(UUID(), N'Khánh Hòa'),
(UUID(), N'Kiên Giang'),
(UUID(), N'Kon Tum'),
(UUID(), N'Lai Châu'),
(UUID(), N'Lâm Đồng'),
(UUID(), N'Lạng Sơn'),
(UUID(), N'Lào Cai'),
(UUID(), N'Long An'),
(UUID(), N'Nam Định'),
(UUID(), N'Nghệ An'),
(UUID(), N'Ninh Bình'),
(UUID(), N'Ninh Thuận'),
(UUID(), N'Phú Thọ'),
(UUID(), N'Phú Yên'),
(UUID(), N'Quảng Bình'),
(UUID(), N'Quảng Nam'),
(UUID(), N'Quảng Ngãi'),
(UUID(), N'Quảng Ninh'),
(UUID(), N'Quảng Trị'),
(UUID(), N'Sóc Trăng'),
(UUID(), N'Sơn La'),
(UUID(), N'Tây Ninh'),
(UUID(), N'Thái Bình'),
(UUID(), N'Thái Nguyên'),
(UUID(), N'Thanh Hóa'),
(UUID(), N'Thừa Thiên Huế'),
(UUID(), N'Tiền Giang'),
(UUID(), N'Tp. Hồ Chí Minh'),
(UUID(), N'Trà Vinh'),
(UUID(), N'Tuyên Quang'),
(UUID(), N'Vĩnh Long'),
(UUID(), N'Vĩnh Phúc'),
(UUID(), N'Yên Bái');



CREATE TABLE DonViPhoBien (
cqdvId varchar(100) NOT NULL,
vbhcId varchar(100) NOT NULL,
PRIMARY KEY (cqdvId, vbhcId),

FOREIGN KEY (vbhcId) REFERENCES VanBanHanhChinh(id),
FOREIGN KEY (cqdvId) REFERENCES CoQuanDonVi(coQuanDonViId)

);


add doc:
"{\"id\":\"650b6299-47bd-43bc-945c-9245632c850b\",\"trichYeu\":\"Về việc nâng cao chất lượng đào tạo\",\"soHieu\":\"QĐ-2025/DT\",\"loaiVanBan\":\"Quyết định\",\"coQuanBanHanh\":\"Bộ Giáo dục và Đào tạo\",\"nguoiKy\":\"Lê Văn Tám\",\"chucVuNguoiKy\":\"Thứ trưởng\",\"donViPhoBien\":[\"Phòng Đào tạo\"],\"ngayDen\":\"2025-09-06\",\"ngayBanHanh\":\"2025-09-01\",\"tepDinhKem\":\"quyetdinh241.pdf\",\"nguoiPhoBien\":\"admin\"}"
update:
"{\"id\":\"650b6299-47bd-43bc-945c-9245632c850b\",\"trichYeu\":\"Về việc nâng cao chất lượng đào tạo\",\"soHieu\":\"QĐ-2025/DT\",\"loaiVanBan\":\"Quyết định\",\"coQuanBanHanh\":\"Bộ Giáo dục và Đào tạo\",\"nguoiKy\":\"Lê Văn Tám\",\"chucVuNguoiKy\":\"Thứ trưởng\",\"donViPhoBien\":[\"Phòng Đào tạo\"],\"ngayDen\":\"2025-09-06\",\"ngayBanHanh\":\"2025-09-10\",\"tepDinhKem\":\"quyetdinh241.pdf\",\"nguoiPhoBien\":\"admin\"}"