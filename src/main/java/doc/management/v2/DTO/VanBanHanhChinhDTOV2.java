package doc.management.v2.DTO;

import java.time.LocalDate;
import java.util.List;

public class VanBanHanhChinhDTOV2 {
    private String id;
    private String trichYeu;
    private String soHieu;
    private String loaiVanBan;
    private String coQuanBanHanh;
    private String nguoiKy;
    private String chucVuNguoiKy;
    private List<String> donViPhoBien;
    private LocalDate ngayDen;
    private LocalDate ngayBanHanh;
    private String nguoiPhoBien;
    private String tepDinhKem;

    private String tenTaiKhoan;

    private String ghiChu;

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

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
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

    public List<String> getDonViPhoBien() {
        return donViPhoBien;
    }

    public void setDonViPhoBien(List<String> donViPhoBien) {
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

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }
}