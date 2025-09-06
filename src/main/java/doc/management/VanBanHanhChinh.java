package doc.management;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "VanBanHanhChinh")
public class VanBanHanhChinh {
    @Id
    private String id;

    private String trichYeu;
    private String soHieu;
    private String loaiVanBan;
    private String coQuanBanHanh;
    private String nguoiKy;
    private String chucVuNguoiKy;
    private String donViPhoBien;
    private LocalDate ngayDen;
    private LocalDate ngayBanHanh;
    private String nguoiPhoBien;
    private String tepDinhKem;

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

    public String getLoaiVanBan() {
        return loaiVanBan;
    }

    public void setLoaiVanBan(String loaiVanBan) {
        this.loaiVanBan = loaiVanBan;
    }

    public String getCoQuanBanHanh() {
        return coQuanBanHanh;
    }

    public void setCoQuanBanHanh(String coQuanBanHanh) {
        this.coQuanBanHanh = coQuanBanHanh;
    }

    public String getNguoiKy() {
        return nguoiKy;
    }

    public void setNguoiKy(String nguoiKy) {
        this.nguoiKy = nguoiKy;
    }

    public String getChucVuNguoiKy() {
        return chucVuNguoiKy;
    }

    public void setChucVuNguoiKy(String chucVuNguoiKy) {
        this.chucVuNguoiKy = chucVuNguoiKy;
    }

    public String getDonViPhoBien() {
        return donViPhoBien;
    }

    public void setDonViPhoBien(String donViPhoBien) {
        this.donViPhoBien = donViPhoBien;
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

    public String getNguoiPhoBien() {
        return nguoiPhoBien;
    }

    public void setNguoiPhoBien(String nguoiPhoBien) {
        this.nguoiPhoBien = nguoiPhoBien;
    }

    public String getTepDinhKem() {
        return tepDinhKem;
    }

    public void setTepDinhKem(String tepDinhKem) {
        this.tepDinhKem = tepDinhKem;
    }
}