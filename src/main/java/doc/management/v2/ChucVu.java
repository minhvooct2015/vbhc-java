package doc.management.v2;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class ChucVu {
    @Id
    @Column(length = 36)
    private String chucVuId;

    @Column(nullable = false)
    private String tenChucVu;

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
}