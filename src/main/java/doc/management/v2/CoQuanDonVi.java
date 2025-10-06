package doc.management.v2;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

import jakarta.persistence.*;

@Entity
public class CoQuanDonVi {
    @Id
    private String coQuanDonViId;

    @Column(nullable = false)
    private String tenCoQuanDonVi;

    @ManyToOne
    @JoinColumn(name = "tinhThanhPhoId")
    private TinhThanhPho tinhThanhPho;

    public CoQuanDonVi() {}

    public CoQuanDonVi(String coQuanDonViId, String tenCoQuanDonVi, TinhThanhPho tinhThanhPho) {
        this.coQuanDonViId = coQuanDonViId;
        this.tenCoQuanDonVi = tenCoQuanDonVi;
        this.tinhThanhPho = tinhThanhPho;
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

    public TinhThanhPho getTinhThanhPho() {
        return tinhThanhPho;
    }

    public void setTinhThanhPho(TinhThanhPho tinhThanhPho) {
        this.tinhThanhPho = tinhThanhPho;
    }
}