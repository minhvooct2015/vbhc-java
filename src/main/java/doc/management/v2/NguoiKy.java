package doc.management.v2;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class NguoiKy {
    @Id
    @Column(length = 36)
    private String nguoiKyId;

    @Column(nullable = false)
    private String hoTenNguoiKy;

    @ManyToOne
    @JoinColumn(name = "coQuanDonViId", nullable = false)
    private CoQuanDonVi coQuanDonVi;

    @OneToMany(mappedBy = "nguoiKy")
    private List<NguoiKyGiuChucVu> nguoiKyGiuChucVu;

    public NguoiKy() {}


    public List<NguoiKyGiuChucVu> getNguoiKyGiuChucVu() {
        return nguoiKyGiuChucVu;
    }

    public void setNguoiKyGiuChucVu(List<NguoiKyGiuChucVu> nguoiKyGiuChucVu) {
        this.nguoiKyGiuChucVu = nguoiKyGiuChucVu;
    }

    public NguoiKy(String nguoiKyId, String hoTenNguoiKy, CoQuanDonVi coQuanDonVi) {
        this.nguoiKyId = nguoiKyId;
        this.hoTenNguoiKy = hoTenNguoiKy;
        this.coQuanDonVi = coQuanDonVi;
    }

    public String getNguoiKyId() {
        return nguoiKyId;
    }

    public void setNguoiKyId(String nguoiKyId) {
        this.nguoiKyId = nguoiKyId;
    }

    public String getHoTenNguoiKy() {
        return hoTenNguoiKy;
    }

    public void setHoTenNguoiKy(String hoTenNguoiKy) {
        this.hoTenNguoiKy = hoTenNguoiKy;
    }

    public CoQuanDonVi getCoQuanDonVi() {
        return coQuanDonVi;
    }

    public void setCoQuanDonVi(CoQuanDonVi coQuanDonVi) {
        this.coQuanDonVi = coQuanDonVi;
    }
}
