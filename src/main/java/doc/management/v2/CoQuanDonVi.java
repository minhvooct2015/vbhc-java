package doc.management.v2;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class CoQuanDonVi {
    @Id
    private String coQuanDonViId;

    @Column(nullable = false)
    private String tenCoQuanDonVi;

    @ManyToOne
    @JoinColumn(name = "tinhThanhPhoId")
    private TinhThanhPho tinhThanhPho;


    // links to join entity
    @OneToMany(mappedBy = "coQuanDonVi", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<DonViPhoBien> donViPhoBienLinks = new HashSet<>();

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

    public Set<DonViPhoBien> getDonViPhoBienLinks() {
        return donViPhoBienLinks;
    }

    public void setDonViPhoBienLinks(Set<DonViPhoBien> donViPhoBienLinks) {
        this.donViPhoBienLinks = donViPhoBienLinks;
    }

    public void setTinhThanhPho(TinhThanhPho tinhThanhPho) {
        this.tinhThanhPho = tinhThanhPho;
    }

    public List<VanBanHanhChinh> getVanBanPhoBien() {
        Set<VanBanHanhChinh> result = new HashSet<>();
        for (DonViPhoBien link : donViPhoBienLinks) {
            if (link.getVanBanHanhChinh() != null) {
                result.add(link.getVanBanHanhChinh());
            }
        }
        return result.stream().toList();
    }


    // equals/hashCode based on primary key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoQuanDonVi that = (CoQuanDonVi) o;
        return coQuanDonViId != null && coQuanDonViId.equals(that.coQuanDonViId);
    }

    @Override
    public int hashCode() {
        return (coQuanDonViId != null) ? coQuanDonViId.hashCode() : 0;
    }
}