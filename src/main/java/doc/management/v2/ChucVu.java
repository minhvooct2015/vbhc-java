package doc.management.v2;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class ChucVu {
    @Id
    @Column(length = 36)
    private String chucVuId;

    @Column(nullable = false)
    private String tenChucVu;

    @OneToMany(mappedBy = "chucVu")
    private List<NguoiKyGiuChucVu> nguoiKyGiuChucVu;

    public ChucVu() {}

    public ChucVu(String chucVuId, String tenChucVu) {
        this.chucVuId = chucVuId;
        this.tenChucVu = tenChucVu;
    }

    public String getChucVuId() {
        return chucVuId;
    }

    public void setChucVuId(String chucVuId) {
        this.chucVuId = chucVuId;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    public List<NguoiKyGiuChucVu> getNguoiKyGiuChucVu() {
        return nguoiKyGiuChucVu;
    }

    public void setNguoiKyGiuChucVu(List<NguoiKyGiuChucVu> nguoiKyGiuChucVu) {
        this.nguoiKyGiuChucVu = nguoiKyGiuChucVu;
    }
}