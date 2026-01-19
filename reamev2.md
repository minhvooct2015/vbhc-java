CREATE DATABASE vbhcDb;




CREATE TABLE DonViPhoBien (
cqdvId varchar(100) NOT NULL,
vbhcId varchar(100) NOT NULL,
PRIMARY KEY (cqdvId, vbhcId),

FOREIGN KEY (vbhcId) REFERENCES VanBanHanhChinh(id),
FOREIGN KEY (cqdvId) REFERENCES CoQuanDonVi(coQuanDonViId)

);


add doc:create table ChucVu
(
chucVuId  varchar(100)                 not null
primary key,
tenChucVu varchar(255) charset utf8mb3 not null
);

create table DataJson
(
Id           varchar(100) not null
primary key,
OrgDocJson   longtext     null,
OrgLatestDoc longtext     null
);

create table LoaiVanBan
(
loaiVanBanId varchar(100)                               not null
primary key,
tenLoai      varchar(255) collate utf8mb4_vietnamese_ci null
);

create table NguoiDung
(
userId      varchar(100)                 not null
primary key,
hoTen       varchar(255) charset utf8mb3 not null,
email       varchar(255) charset utf8mb3 null,
soDienThoai varchar(50) charset utf8mb3  null,
tenDangNhap varchar(100) charset utf8mb3 not null,
matKhau     varchar(255) charset utf8mb3 not null,
constraint tenDangNhap
unique (tenDangNhap)
);

create table TinhThanhPho
(
tinhThanhPhoId  varchar(100)                 not null
primary key,
tenTinhThanhPho varchar(255) charset utf8mb3 not null
);

create table CoQuanDonVi
(
coQuanDonViId  varchar(100)                 not null
primary key,
tenCoQuanDonVi varchar(255) charset utf8mb3 not null,
tinhThanhPhoId varchar(100)                 null,
constraint FK_CoQuanDonVi_TinhThanhPho
foreign key (tinhThanhPhoId) references TinhThanhPho (tinhThanhPhoId)
);

create table NguoiKy
(
nguoiKyId     varchar(100)                 not null
primary key,
hoTenNguoiKy  varchar(255) charset utf8mb3 not null,
coQuanDonViId varchar(100)                 not null,
constraint FK_NguoiKy_CoQuanDonVi
foreign key (coQuanDonViId) references CoQuanDonVi (coQuanDonViId)
);

create table NguoiKyGiuChucVu
(
nguoiKyId  varchar(100) not null,
chucVuId   varchar(100) not null,
ngayBatDau date         null,
primary key (nguoiKyId, chucVuId),
constraint FK_NguoiKyGiuChucVu_ChucVu
foreign key (chucVuId) references ChucVu (chucVuId),
constraint FK_NguoiKyGiuChucVu_NguoiKy
foreign key (nguoiKyId) references NguoiKy (nguoiKyId)
);

create table VanBanHanhChinh
(
Id              varchar(100)                  not null
primary key,
trichYeu        varchar(1000) charset utf8mb3 null,
soHieu          varchar(100) charset utf8mb3  null,
ngayDen         date                          null,
ngayBanHanh     date                          null,
tepDinhKem      varchar(255) charset utf8mb3  null,
loaiVanBanId    varchar(100)                  not null,
coQuanBanHanhId varchar(100)                  not null,
nguoiKyId       varchar(100)                  not null,
userId          varchar(100)                  not null,
chucVuId        varchar(100)                  null,
ghiChu          longtext charset utf8mb3      null,
constraint FK_VBHC_CoQuanBanHanh
foreign key (coQuanBanHanhId) references CoQuanDonVi (coQuanDonViId),
constraint FK_VBHC_LoaiVanBan
foreign key (loaiVanBanId) references LoaiVanBan (loaiVanBanId),
constraint FK_VBHC_NguoiDung
foreign key (userId) references NguoiDung (userId),
constraint FK_VanBanHanhChinh_NguoiKyGiuChucVu
foreign key (nguoiKyId, chucVuId) references NguoiKyGiuChucVu (nguoiKyId, chucVuId)
);

create table DonViPhoBien
(
cqdvId varchar(100) not null,
vbhcId varchar(100) not null,
primary key (cqdvId, vbhcId),
constraint DonViPhoBien_ibfk_1
foreign key (vbhcId) references VanBanHanhChinh (Id),
constraint DonViPhoBien_ibfk_2
foreign key (cqdvId) references CoQuanDonVi (coQuanDonViId)
);

create index vbhcId
on DonViPhoBien (vbhcId);




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

"{\"id\":\"650b6299-47bd-43bc-945c-9245632c850b\",\"trichYeu\":\"Về việc nâng cao chất lượng đào tạo\",\"soHieu\":\"QĐ-2025/DT\",\"loaiVanBan\":\"Quyết định\",\"coQuanBanHanh\":\"Bộ Giáo dục và Đào tạo\",\"nguoiKy\":\"Lê Văn Tám\",\"chucVuNguoiKy\":\"Thứ trưởng\",\"donViPhoBien\":[\"Phòng Đào tạo\"],\"ngayDen\":\"2025-09-06\",\"ngayBanHanh\":\"2025-09-01\",\"tepDinhKem\":\"quyetdinh241.pdf\",\"nguoiPhoBien\":\"admin\"}"
update:
"{\"id\":\"650b6299-47bd-43bc-945c-9245632c850b\",\"trichYeu\":\"Về việc nâng cao chất lượng đào tạo\",\"soHieu\":\"QĐ-2025/DT\",\"loaiVanBan\":\"Quyết định\",\"coQuanBanHanh\":\"Bộ Giáo dục và Đào tạo\",\"nguoiKy\":\"Lê Văn Tám\",\"chucVuNguoiKy\":\"Thứ trưởng\",\"donViPhoBien\":[\"Phòng Đào tạo\"],\"ngayDen\":\"2025-09-06\",\"ngayBanHanh\":\"2025-09-10\",\"tepDinhKem\":\"quyetdinh241.pdf\",\"nguoiPhoBien\":\"admin\"}"

Phai them admin vao ngNhap, neu khong se loi nha

[//]: # (postgres init)


create table ChucVu
(
chucVuId  varchar(100)                 not null
primary key,
tenChucVu varchar(255) charset utf8mb3 not null
);

create table DataJson
(
Id           varchar(100) not null
primary key,
OrgDocJson   longtext     null,
OrgLatestDoc longtext     null
);

create table LoaiVanBan
(
loaiVanBanId varchar(100)                               not null
primary key,
tenLoai      varchar(255) collate utf8mb4_vietnamese_ci null
);

create table NguoiDung
(
userId      varchar(100)                 not null
primary key,
hoTen       varchar(255) charset utf8mb3 not null,
email       varchar(255) charset utf8mb3 null,
soDienThoai varchar(50) charset utf8mb3  null,
tenDangNhap varchar(100) charset utf8mb3 not null,
matKhau     varchar(255) charset utf8mb3 not null,
constraint tenDangNhap
unique (tenDangNhap)
);

create table TinhThanhPho
(
tinhThanhPhoId  varchar(100)                 not null
primary key,
tenTinhThanhPho varchar(255) charset utf8mb3 not null
);

create table CoQuanDonVi
(
coQuanDonViId  varchar(100)                 not null
primary key,
tenCoQuanDonVi varchar(255) charset utf8mb3 not null,
tinhThanhPhoId varchar(100)                 null,
constraint FK_CoQuanDonVi_TinhThanhPho
foreign key (tinhThanhPhoId) references TinhThanhPho (tinhThanhPhoId)
);

create table NguoiKy
(
nguoiKyId     varchar(100)                 not null
primary key,
hoTenNguoiKy  varchar(255) charset utf8mb3 not null,
coQuanDonViId varchar(100)                 not null,
constraint FK_NguoiKy_CoQuanDonVi
foreign key (coQuanDonViId) references CoQuanDonVi (coQuanDonViId)
);

create table NguoiKyGiuChucVu
(
nguoiKyId  varchar(100) not null,
chucVuId   varchar(100) not null,
ngayBatDau date         null,
primary key (nguoiKyId, chucVuId),
constraint FK_NguoiKyGiuChucVu_ChucVu
foreign key (chucVuId) references ChucVu (chucVuId),
constraint FK_NguoiKyGiuChucVu_NguoiKy
foreign key (nguoiKyId) references NguoiKy (nguoiKyId)
);

create table VanBanHanhChinh
(
Id              varchar(100)                  not null
primary key,
trichYeu        varchar(1000) charset utf8mb3 null,
soHieu          varchar(100) charset utf8mb3  null,
ngayDen         date                          null,
ngayBanHanh     date                          null,
tepDinhKem      varchar(255) charset utf8mb3  null,
loaiVanBanId    varchar(100)                  not null,
coQuanBanHanhId varchar(100)                  not null,
nguoiKyId       varchar(100)                  not null,
userId          varchar(100)                  not null,
chucVuId        varchar(100)                  null,
ghiChu          longtext charset utf8mb3      null,
constraint FK_VBHC_CoQuanBanHanh
foreign key (coQuanBanHanhId) references CoQuanDonVi (coQuanDonViId),
constraint FK_VBHC_LoaiVanBan
foreign key (loaiVanBanId) references LoaiVanBan (loaiVanBanId),
constraint FK_VBHC_NguoiDung
foreign key (userId) references NguoiDung (userId),
constraint FK_VanBanHanhChinh_NguoiKyGiuChucVu
foreign key (nguoiKyId, chucVuId) references NguoiKyGiuChucVu (nguoiKyId, chucVuId)
);

create table DonViPhoBien
(
cqdvId varchar(100) not null,
vbhcId varchar(100) not null,
primary key (cqdvId, vbhcId),
constraint DonViPhoBien_ibfk_1
foreign key (vbhcId) references VanBanHanhChinh (Id),
constraint DonViPhoBien_ibfk_2
foreign key (cqdvId) references CoQuanDonVi (coQuanDonViId)
);

create index vbhcId
on DonViPhoBien (vbhcId);




INSERT INTO TinhThanhPho (tinhThanhPhoId, tenTinhThanhPho) VALUES
(gen_random_uuid()::varchar, N'An Giang'),
(gen_random_uuid()::varchar, N'Bà Rịa - Vũng Tàu'),
(gen_random_uuid()::varchar, N'Bắc Giang'),
(gen_random_uuid()::varchar, N'Bắc Kạn'),
(gen_random_uuid()::varchar, N'Bạc Liêu'),
(gen_random_uuid()::varchar, N'Bắc Ninh'),
(gen_random_uuid()::varchar, N'Bến Tre'),
(gen_random_uuid()::varchar, N'Bình Định'),
(gen_random_uuid()::varchar, N'Bình Dương'),
(gen_random_uuid()::varchar, N'Bình Phước'),
(gen_random_uuid()::varchar, N'Bình Thuận'),
(gen_random_uuid()::varchar, N'Cà Mau'),
(gen_random_uuid()::varchar, N'Cần Thơ'),
(gen_random_uuid()::varchar, N'Cao Bằng'),
(gen_random_uuid()::varchar, N'Đà Nẵng'),
(gen_random_uuid()::varchar, N'Đắk Lắk'),
(gen_random_uuid()::varchar, N'Đắk Nông'),
(gen_random_uuid()::varchar, N'Điện Biên'),
(gen_random_uuid()::varchar, N'Đồng Nai'),
(gen_random_uuid()::varchar, N'Đồng Tháp'),
(gen_random_uuid()::varchar, N'Gia Lai'),
(gen_random_uuid()::varchar, N'Hà Giang'),
(gen_random_uuid()::varchar, N'Hà Nam'),
(gen_random_uuid()::varchar, N'Hà Nội'),
(gen_random_uuid()::varchar, N'Hà Tĩnh'),
(gen_random_uuid()::varchar, N'Hải Dương'),
(gen_random_uuid()::varchar, N'Hải Phòng'),
(gen_random_uuid()::varchar, N'Hậu Giang'),
(gen_random_uuid()::varchar, N'Hòa Bình'),
(gen_random_uuid()::varchar, N'Hưng Yên'),
(gen_random_uuid()::varchar, N'Khánh Hòa'),
(gen_random_uuid()::varchar, N'Kiên Giang'),
(gen_random_uuid()::varchar, N'Kon Tum'),
(gen_random_uuid()::varchar, N'Lai Châu'),
(gen_random_uuid()::varchar, N'Lâm Đồng'),
(gen_random_uuid()::varchar, N'Lạng Sơn'),
(gen_random_uuid()::varchar, N'Lào Cai'),
(gen_random_uuid()::varchar, N'Long An'),
(gen_random_uuid()::varchar, N'Nam Định'),
(gen_random_uuid()::varchar, N'Nghệ An'),
(gen_random_uuid()::varchar, N'Ninh Bình'),
(gen_random_uuid()::varchar, N'Ninh Thuận'),
(gen_random_uuid()::varchar, N'Phú Thọ'),
(gen_random_uuid()::varchar, N'Phú Yên'),
(gen_random_uuid()::varchar, N'Quảng Bình'),
(gen_random_uuid()::varchar, N'Quảng Nam'),
(gen_random_uuid()::varchar, N'Quảng Ngãi'),
(gen_random_uuid()::varchar, N'Quảng Ninh'),
(gen_random_uuid()::varchar, N'Quảng Trị'),
(gen_random_uuid()::varchar, N'Sóc Trăng'),
(gen_random_uuid()::varchar, N'Sơn La'),
(gen_random_uuid()::varchar, N'Tây Ninh'),
(gen_random_uuid()::varchar, N'Thái Bình'),
(gen_random_uuid()::varchar, N'Thái Nguyên'),
(gen_random_uuid()::varchar, N'Thanh Hóa'),
(gen_random_uuid()::varchar, N'Thừa Thiên Huế'),
(gen_random_uuid()::varchar, N'Tiền Giang'),
(gen_random_uuid()::varchar, N'Tp. Hồ Chí Minh'),
(gen_random_uuid()::varchar, N'Trà Vinh'),
(gen_random_uuid()::varchar, N'Tuyên Quang'),
(gen_random_uuid()::varchar, N'Vĩnh Long'),
(gen_random_uuid()::varchar, N'Vĩnh Phúc'),
(gen_random_uuid()::varchar, N'Yên Bái');
