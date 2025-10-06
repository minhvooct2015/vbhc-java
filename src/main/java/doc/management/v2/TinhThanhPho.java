package doc.management.v2;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class TinhThanhPho {
    @Id
    @Column(length = 36)
    private String tinhThanhPhoId;

    @Column(nullable = false)
    private String tenTinhThanhPho;

    public TinhThanhPho() {}

    public TinhThanhPho(String tinhThanhPhoId, String tenTinhThanhPho) {
        this.tinhThanhPhoId = tinhThanhPhoId;
        this.tenTinhThanhPho = tenTinhThanhPho;
    }

    public String getTinhThanhPhoId() {
        return tinhThanhPhoId;
    }

    public void setTinhThanhPhoId(String tinhThanhPhoId) {
        this.tinhThanhPhoId = tinhThanhPhoId;
    }

    public String getTenTinhThanhPho() {
        return tenTinhThanhPho;
    }

    public void setTenTinhThanhPho(String tenTinhThanhPho) {
        this.tenTinhThanhPho = tenTinhThanhPho;
    }
}