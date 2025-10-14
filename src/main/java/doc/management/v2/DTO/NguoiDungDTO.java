package doc.management.v2.DTO;

public class NguoiDungDTO {
    private String userId;
    private String hoTen;
    private String email;
    private String soDienThoai;
    private String tenDangNhap;
    private String matKhau;

    public NguoiDungDTO() {}

    public NguoiDungDTO(String userId, String hoTen, String email, String soDienThoai, String tenDangNhap, String matKhau) {
        this.userId = userId;
        this.hoTen = hoTen;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }

    public NguoiDungDTO(String userId, String hoTen, String email, String soDienThoai, String tenDangNhap) {
        this.userId = userId;
        this.hoTen = hoTen;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.tenDangNhap = tenDangNhap;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
