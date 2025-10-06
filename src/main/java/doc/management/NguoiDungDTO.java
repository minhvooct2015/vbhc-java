//package doc.management;
//
//public class NguoiDungDTO {
//    private String hoTen;
//    private String email;
//    private String soDienThoai;
//    private String tenDangNhap;
//    private String matKhau;
//
//    // No-arg constructor
//    public NguoiDungDTO() {}
//
//    // All-arguments constructor
//    public NguoiDungDTO(String hoTen, String email, String soDienThoai, String tenDangNhap, String matKhau) {
//        this.hoTen = hoTen;
//        this.email = email;
//        this.soDienThoai = soDienThoai;
//        this.tenDangNhap = tenDangNhap;
//        this.matKhau = matKhau;
//    }
//
//    // Getters and Setters
//    public String getHoTen() {
//        return hoTen;
//    }
//
//    public void setHoTen(String hoTen) {
//        this.hoTen = hoTen;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getSoDienThoai() {
//        return soDienThoai;
//    }
//
//    public void setSoDienThoai(String soDienThoai) {
//        this.soDienThoai = soDienThoai;
//    }
//
//    public String getTenDangNhap() {
//        return tenDangNhap;
//    }
//
//    public void setTenDangNhap(String tenDangNhap) {
//        this.tenDangNhap = tenDangNhap;
//    }
//
//    public String getMatKhau() {
//        return matKhau;
//    }
//
//    public void setMatKhau(String matKhau) {
//        this.matKhau = matKhau;
//    }
//
//    public void setFromEntity(NguoiDung nguoiDung) {
//        this.hoTen = nguoiDung.getHoTen();
//        this.email = nguoiDung.getEmail();
//        this.soDienThoai = nguoiDung.getSoDienThoai();
//        this.tenDangNhap = nguoiDung.getTenDangNhap();
//    }
//}