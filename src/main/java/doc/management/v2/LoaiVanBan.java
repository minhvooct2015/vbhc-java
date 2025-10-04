package doc.management.v2;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class LoaiVanBan {
    @Id
    @Column(length = 36)
    private String loaiVanBanId;

    @Column(nullable = false)
    private String tenLoai;

    public LoaiVanBan() {}

    public LoaiVanBan(String loaiVanBanId, String tenLoai) {
        this.loaiVanBanId = loaiVanBanId;
        this.tenLoai = tenLoai;
    }

    public String getLoaiVanBanId() {
        return loaiVanBanId;
    }

    public void setLoaiVanBanId(String loaiVanBanId) {
        this.loaiVanBanId = loaiVanBanId;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}