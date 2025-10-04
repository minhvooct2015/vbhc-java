package doc.management.v2;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class CoQuanDonVi {
    @Id
    @Column(length = 36)
    private String coQuanDonViId;

    @Column(nullable = false)
    private String tenCoQuanDonVi;

    public CoQuanDonVi() {}

    public CoQuanDonVi(String coQuanDonViId, String tenCoQuanDonVi) {
        this.coQuanDonViId = coQuanDonViId;
        this.tenCoQuanDonVi = tenCoQuanDonVi;
    }

    public String getCoQuanDonViId() {
        return coQuanDonViId;
    }

    public void setCoQuanDonViId(String coQuanDonViId) {
        this.coQuanDonViId = coQuanDonViId;
    }

    public String getTenCoQuanDonVi() {
        return tenCoQuanDonVi;
    }

    public void setTenCoQuanDonVi(String tenCoQuanDonVi) {
        this.tenCoQuanDonVi = tenCoQuanDonVi;
    }
}