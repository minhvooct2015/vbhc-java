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


curl -X 'POST' \
'http://localhost:9000/doc/v2' \
-H 'accept: */*' \
-H 'Content-Type: multipart/form-data' \
-F 'file=@/Users/rickdal/Downloads/matrix.png;type=image/jpeg' \
-F 'docInfo={"id":"650b6299-47bd-43bc-945c-9245632c850b","trichYeu":"Về việc nâng cao chất lượng đào tạo","soHieu":"QĐ-2025/DT","loaiVanBan":"Quyết định","coQuanBanHanh":"Bộ Giáo dục và Đào tạo","nguoiKy":"Lê Văn Tám","chucVuNguoiKy":"Thứ trưởng","donViPhoBien":["Phòng Đào tạo"],"ngayDen":"2025-09-06","ngayBanHanh":"2025-09-01","tepDinhKem":"quyetdinh241.pdf","nguoiPhoBien":"admin"};type=text/plain' \
-F 'orgDoc={"id":"650b6299-47bd-43bc-945c-9245632c850b","trichYeu":"Về việc nâng cao chất lượng đào tạo","soHieu":"QĐ-2025/DT","loaiVanBan":"Quyết định","coQuanBanHanh":"Bộ Giáo dục và Đào tạo","nguoiKy":"Lê Văn Tám","chucVuNguoiKy":"Thứ trưởng","donViPhoBien":["Phòng Đào tạo"],"ngayDen":"2025-09-06","ngayBanHanh":"2025-09-10","tepDinhKem":"quyetdinh241.pdf","nguoiPhoBien":"admin"};type=text/plain'


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

write dockerFile
mvn clean package -DskipTests
docker build -t doc-management-service:1.0.0 .

docker network create doc-net
doc-database-pg

Put your DB container on the doc-net network

docker run -d \
--name doc-database-pg \
--network doc-net \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=123456 \
-e POSTGRES_DB=vbhcdb \
-p 5432:5432 \
postgres:16

If doc-database-pg is already running on another network, connect it:
docker network connect doc-net doc-database-pg


docker run -d \
--name doc-management-service \
--network doc-net \
-p 9000:9000 \
-e DB_URL=jdbc:postgresql://doc-database-pg:5432/vbhcdb \
-e DB_USER=postgres \
-e DB_PASSWORD=123456 \
doc-management-service:1.0.0


ls -R /opt/app/images : list fie in folder

docker logs -f doc-management-service
https://github.com/copilot/c/44467aec-97a7-47ae-b899-29c818fc5a5f



[//]: # (Deploy no docker)
1. Install jdk-21
sudo apt install openjdk-21-jre -y

2. scp -r target/quarkus-app vanban@172.18.54.38:/home/vanban

3SSH into the server.
 ssh vanban@172.18.54.38
5. cd /home/vanban
6. Create a file named run-app.sh in /home/vanban:
   nano run-app.sh
#!/bin/bash
# Set environment variables
export DB_HOST=172.18.54.38
export DB_PORT=5432
export DB_NAME=postgres
export DB_USER=vanban
export APP_IMAGE_DIRECTORY=/home/vanban/imagesp
export DB_PASSWORD=Ctu33333$

# Ensure the images directory exists
mkdir -p /home/vanban/images

# Navigate to the application directory
cd /home/vanban/quarkus-app

# Start the application
# java -jar quarkus-run.jar
nohup java -jar quarkus-run.jar > app.log 2>&1 &
8. chmod +x run-app.sh
9. Run the application
./run-app.sh
10. Verify the Application
    tail -f /home/vanban/app.log
    http://172.18.54.38:9000


psql -U vanban -d postgres -h 172.18.54.38 -p 5432
   To keep the application running after you log out of the server, use nohup:
   nohup java -jar quarkus-run.jar > app.log 2>&1 &
   [//]: # (Create a .env File)


echo $DB_HOST
echo $DB_PORT
echo $DB_NAME
echo $DB_USER
echo $DB_PASSWORD

kill process
ps aux | grep java
kill -9 861013
 rm -r folder
echo "DB_HOST=172.18.54.38
DB_PORT=5432
DB_NAME=postgres
DB_USER=vanban
DB_PASSWORD=Ctu33333$
APP_IMAGE_DIRECTORY=/home/vanban/images" > /home/vanban/.env

Ensure /home/vanban/images exists:
bash
ls -ld /home/vanban/images
If the directory does not exist, create it:
bash
mkdir -p /home/vanban/images

[//]: # (===server 2:)
verify my steps to deploy:
1. Install jdk-21
   sudo apt install openjdk-21-jre -y
2. Compress the target/quarkus-app/ directory on your local machine:

mvn clean package -DskipTests -Dquarkus.package.type=uber-jar
3. scp target/doc-management-service-1.0.0-SNAPSHOT-runner.jar vanban@172.18.54.38:/home/vanban
   if the transfer is slow, you can compress the file first using .tar.gz, or switch to rsync (as previously explained). Example:
   tar -czvf doc-management-service.tar.gz -C target doc-management-service-1.0.0-SNAPSHOT-runner.jar
   scp doc-management-service.tar.gz vanban@172.18.54.38:/home/vanban

4. SSH into the server.
   ssh vanban@172.18.54.38
5. tar -xzvf doc-management-service.tar.gz (extract file)
5. cd /home/vanban
7. Create a file named run-app.sh in /home/vanban:
   nano run-app.sh
   #!/bin/bash
# Set environment variables
export DB_HOST=172.18.54.38
export DB_PORT=5432
export DB_NAME=postgres
export DB_USER=vanban
export APP_IMAGE_DIRECTORY=/home/vanban/images
export DB_PASSWORD=Ctu33333$

# Ensure the images directory exists
mkdir -p /home/vanban/images

nohup java -jar quarkus-run.jar > app.log 2>&1 &

8. chmod +x run-app.sh
9. Run the application
   ./run-app.sh
10. Verify the Application
    tail -f /home/vanban/app.log
    http://172.18.54.38:9000
# Stop previous instance
pkill -f "java -jar doc-management-service-1.0.0-SNAPSHOT-runner.jar"
Database connect:
psql -h 172.18.54.38 -U vanban -d postgres -p 5432

https://github.com/copilot/c/2e9d294d-3b5c-46fb-87a3-797159b0dcc0