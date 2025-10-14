package doc.management.v2;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class VanBanHanhChinh {
    @Id
    @Column(length = 36)
    private String id;

    private String trichYeu;

    private String soHieu;

    private LocalDate ngayDen;

    private LocalDate ngayBanHanh;

    private String tepDinhKem;

    @ManyToOne
    @JoinColumn(name = "loaiVanBanId", nullable = false)
    private LoaiVanBan loaiVanBan;

    @ManyToOne
    @JoinColumn(name = "coQuanBanHanhId", nullable = false)
    private CoQuanDonVi coQuanBanHanh;

    @ManyToOne
    @JoinColumn(name = "donViPhoBienId", nullable = false)
    private CoQuanDonVi donViPhoBien;

    // Add mapping to NguoiKyGiuChucVu (composite key)
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "nguoiKyId", referencedColumnName = "nguoiKyId"),
            @JoinColumn(name = "chucVuId", referencedColumnName = "chucVuId")
    })
    private NguoiKyGiuChucVu nguoiKyGiuChucVu;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private NguoiDung nguoiNhap;

    public VanBanHanhChinh() {}

    public VanBanHanhChinh(String id, String trichYeu, String soHieu, LocalDate ngayDen, LocalDate ngayBanHanh,
                           String tepDinhKem, LoaiVanBan loaiVanBan, CoQuanDonVi coQuanBanHanh, CoQuanDonVi donViPhoBien,
                           NguoiKyGiuChucVu nguoiKyGiuChucVu, NguoiDung nguoiNhap) {
        this.id = id;
        this.trichYeu = trichYeu;
        this.soHieu = soHieu;
        this.ngayDen = ngayDen;
        this.ngayBanHanh = ngayBanHanh;
        this.tepDinhKem = tepDinhKem;
        this.loaiVanBan = loaiVanBan;
        this.coQuanBanHanh = coQuanBanHanh;
        this.donViPhoBien = donViPhoBien;
        this.nguoiKyGiuChucVu = nguoiKyGiuChucVu;
        this.nguoiNhap = nguoiNhap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrichYeu() {
        return trichYeu;
    }

    public void setTrichYeu(String trichYeu) {
        this.trichYeu = trichYeu;
    }

    public String getSoHieu() {
        return soHieu;
    }

    public void setSoHieu(String soHieu) {
        this.soHieu = soHieu;
    }

    public LocalDate getNgayDen() {
        return ngayDen;
    }

    public void setNgayDen(LocalDate ngayDen) {
        this.ngayDen = ngayDen;
    }

    public LocalDate getNgayBanHanh() {
        return ngayBanHanh;
    }

    public void setNgayBanHanh(LocalDate ngayBanHanh) {
        this.ngayBanHanh = ngayBanHanh;
    }

    public String getTepDinhKem() {
        return tepDinhKem;
    }

    public void setTepDinhKem(String tepDinhKem) {
        this.tepDinhKem = tepDinhKem;
    }

    public LoaiVanBan getLoaiVanBan() {
        return loaiVanBan;
    }

    public void setLoaiVanBan(LoaiVanBan loaiVanBan) {
        this.loaiVanBan = loaiVanBan;
    }

    public CoQuanDonVi getCoQuanBanHanh() {
        return coQuanBanHanh;
    }

    public void setCoQuanBanHanh(CoQuanDonVi coQuanBanHanh) {
        this.coQuanBanHanh = coQuanBanHanh;
    }

    public CoQuanDonVi getDonViPhoBien() {
        return donViPhoBien;
    }

    public void setDonViPhoBien(CoQuanDonVi donViPhoBien) {
        this.donViPhoBien = donViPhoBien;
    }

    public NguoiKyGiuChucVu getNguoiKyGiuChucVu() {
        return nguoiKyGiuChucVu;
    }

    public void setNguoiKyGiuChucVu(NguoiKyGiuChucVu nguoiKy) {
        this.nguoiKyGiuChucVu = nguoiKy;
    }

    public NguoiDung getNguoiNhap() {
        return nguoiNhap;
    }

    public void setNguoiNhap(NguoiDung nguoiNhap) {
        this.nguoiNhap = nguoiNhap;
    }
}