package doc.management;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DangNhapDTO {
    private String tenDangNhap;
    private String matKhau;

    // Getter and Setter for tenDangNhap
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    // Getter and Setter for matKhau
    public String getMatKhau() {
        return matKhau;
    }
}
