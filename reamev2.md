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
CONSTRAINT FK_VBHC_NguoiKy FOREIGN KEY (nguoiKyId) REFERENCES NguoiKy(nguoiKyId),
CONSTRAINT FK_VBHC_NguoiDung FOREIGN KEY (userId) REFERENCES NguoiDung(userId)
);