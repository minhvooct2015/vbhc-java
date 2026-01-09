package doc.management.v2;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "DonViPhoBien")
@IdClass(DonViPhoBien.DonViPhoBienId.class)
public class DonViPhoBien implements Serializable {

    @Id
    @Column(name = "cqdvId")
    private String cqdvId;

    @Id
    @Column(name = "vbhcId")
    private String vbhcId;

    @ManyToOne
    @JoinColumn(name = "cqdvId", insertable = false, updatable = false)
    private CoQuanDonVi coQuanDonVi;

    @ManyToOne
    @JoinColumn(name = "vbhcId", insertable = false, updatable = false)
    private VanBanHanhChinh vanBanHanhChinh;

    public DonViPhoBien() {}

    public DonViPhoBien(String cqdvId, String vbhcId) {
        this.cqdvId = cqdvId;
        this.vbhcId = vbhcId;
    }

    public String getCqdvId() {
        return cqdvId;
    }

    public void setCqdvId(String cqdvId) {
        this.cqdvId = cqdvId;
    }

    public String getVbhcId() {
        return vbhcId;
    }

    public void setVbhcId(String vbhcId) {
        this.vbhcId = vbhcId;
    }

    public CoQuanDonVi getCoQuanDonVi() {
        return coQuanDonVi;
    }

    public void setCoQuanDonVi(CoQuanDonVi coQuanDonVi) {
        this.coQuanDonVi = coQuanDonVi;
    }

    public VanBanHanhChinh getVanBanHanhChinh() {
        return vanBanHanhChinh;
    }

    public void setVanBanHanhChinh(VanBanHanhChinh vanBanHanhChinh) {
        this.vanBanHanhChinh = vanBanHanhChinh;
    }

    public static class DonViPhoBienId implements Serializable {
        private String cqdvId;
        private String vbhcId;

        public DonViPhoBienId() {}

        public DonViPhoBienId(String cqdvId, String vbhcId) {
            this.cqdvId = cqdvId;
            this.vbhcId = vbhcId;
        }

        public String getCqdvId() {
            return cqdvId;
        }
        public void setCqdvId(String cqdvId) {
            this.cqdvId = cqdvId;
        }
        public String getVbhcId() {
            return vbhcId;
        }
        public void setVbhcId(String vbhcId) {
            this.vbhcId = vbhcId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DonViPhoBienId that = (DonViPhoBienId) o;
            return Objects.equals(cqdvId, that.cqdvId) &&
                    Objects.equals(vbhcId, that.vbhcId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cqdvId, vbhcId);
        }
    }
}