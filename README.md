# doc-management-service

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/doc-management-service-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)


mvn io.quarkus:quarkus-maven-plugin:3.11.1:create -DprojectGroupId=doc.management -DprojectArtifactId=doc-management-service -DclassName=doc.management.UserResource -Dpath=/login



docker pull mysql:8.0

docker run --name doc-database -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 -d mysql:8.0


docker run --name doc-database-v2 -e MYSQL_ROOT_PASSWORD=123456 -p 3308:3306 -d mysql:8.0

docker exec -it doc-database mysql -u root -p
CREATE DATABASE docdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE docdb;
jdk 21

CREATE TABLE VanBanHanhChinh (
Id VARCHAR(255) PRIMARY KEY,
trichYeu VARCHAR(1000),
soHieu VARCHAR(255),
loaiVanBan VARCHAR(255),
coQuanBanHanh VARCHAR(255),
nguoiKy VARCHAR(255),
chucVuNguoiKy VARCHAR(255),
donViPhoBien VARCHAR(255),
ngayDen DATE,
ngayBanHanh DATE,
nguoiPhoBien VARCHAR(255),
tepDinhKem VARCHAR(500)
);

CREATE TABLE NguoiDung (
userId VARCHAR(255) PRIMARY KEY,
hoTen VARCHAR(255),
email VARCHAR(255),
soDienThoai VARCHAR(50),
tenDangNhap VARCHAR(100),
matKhau VARCHAR(255)
);


{
"hoTen": "Quan tri vien",
"email": "Admin@docMana.com",
"soDienThoai": "0123456789",
"tenDangNhap": "admin",
"password": "123456"
}


http://192.168.1.252:9000/login
ipconfig getifaddr en0


"{\"id\":\"VBHC-123\",\"trichYeu\":\"Về việc nâng cao chất lượng đào tạo\",\"soHieu\":\"QĐ-2025/DT\",\"loaiVanBan\":\"Quyết định\",\"coQuanBanHanh\":\"Bộ Giáo dục và Đào tạo\",\"nguoiKy\":\"Lê Văn Tám\",\"chucVuNguoiKy\":\"Thứ trưởng\",\"donViPhoBien\":\"Phòng Đào tạo\",\"ngayDen\":\"2025-09-06\",\"ngayBanHanh\":\"2025-09-01\",\"nguoiPhoBien\":\"Nguyễn Thị Bảy\",\"tepDinhKem\":\"quyetdinh-dt.pdf\",\"tenTaiKhoan\":\"nguyenvana\"}"
add doc:
curl -X 'POST' \
'http://localhost:9000/doc' \
-H 'accept: */*' \
-H 'Content-Type: multipart/form-data' \
-F 'file=@Cong-Van_247.pdf;type=application/pdf' \
-F 'docInfo={  "trichYeu": "Quy định về an toàn lao động",   "soHieu": "QĐ-2023/ATLD",   "loaiVanBan": "Quyết định",   "coQuanBanHanh": "Bộ Lao động",   "nguoiKy": "Nguyễn Văn A",   "chucVuNguoiKy": "Bộ trưởng",   "donViPhoBien": "Phòng Nhân sự",   "ngayDen": "2025-09-01",   "ngayBanHanh": "2025-08-25",   "nguoiPhoBien": "Trần Thị B",   "tepDinhKem": "quydinh-atld.pdf" };type=text/plain'