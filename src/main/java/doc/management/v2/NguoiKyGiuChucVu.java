package doc.management.v2;


import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@IdClass(NguoiKyGiuChucVu.NguoiKyGiuChucVuId.class)
public class NguoiKyGiuChucVu {

    @Id
    @ManyToOne
    @JoinColumn(name = "nguoiKyId")
    private NguoiKy nguoiKy;

    @Id
    @ManyToOne
    @JoinColumn(name = "chucVuId")
    private ChucVu chucVu;

    private LocalDate ngayBatDau;

    public NguoiKyGiuChucVu() {}

    public NguoiKyGiuChucVu(NguoiKy nguoiKy, ChucVu chucVu, LocalDate ngayBatDau) {
        this.nguoiKy = nguoiKy;
        this.chucVu = chucVu;
        this.ngayBatDau = ngayBatDau;
    }

    public NguoiKy getNguoiKy() {
        return nguoiKy;
    }

    public void setNguoiKy(NguoiKy nguoiKy) {
        this.nguoiKy = nguoiKy;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public static class NguoiKyGiuChucVuId implements Serializable {
        private String nguoiKy;
        private String chucVu;

        public NguoiKyGiuChucVuId() {}

        public NguoiKyGiuChucVuId(String nguoiKy, String chucVu) {
            this.nguoiKy = nguoiKy;
            this.chucVu = chucVu;
        }

        public String getNguoiKy() {
            return nguoiKy;
        }

        public void setNguoiKy(String nguoiKy) {
            this.nguoiKy = nguoiKy;
        }

        public String getChucVu() {
            return chucVu;
        }

        public void setChucVu(String chucVu) {
            this.chucVu = chucVu;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof NguoiKyGiuChucVuId)) return false;
            NguoiKyGiuChucVuId that = (NguoiKyGiuChucVuId) o;
            return nguoiKy != null && nguoiKy.equals(that.nguoiKy)
                    && chucVu != null && chucVu.equals(that.chucVu);
        }

        @Override
        public int hashCode() {
            int result = nguoiKy != null ? nguoiKy.hashCode() : 0;
            result = 31 * result + (chucVu != null ? chucVu.hashCode() : 0);
            return result;
        }
    }
}