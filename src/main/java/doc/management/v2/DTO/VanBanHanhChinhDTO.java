package doc.management.v2.DTO;

import java.time.LocalDate;

public class VanBanHanhChinhDTO {
    private String id;
    private String trichYeu;
    private String soHieu;
    private LocalDate ngayDen;
    private LocalDate ngayBanHanh;
    private String tepDinhKem;
    private LoaiVanBanDTO loaiVanBan;
    private CoQuanDonViDTO coQuanBanHanh;
    private CoQuanDonViDTO donViPhoBien;
    private NguoiKyDTO nguoiKy;
    private NguoiDungDTO nguoiNhap;

    public VanBanHanhChinhDTO() {}

    public VanBanHanhChinhDTO(String id, String trichYeu, String soHieu, LocalDate ngayDen, LocalDate ngayBanHanh,
                              String tepDinhKem, LoaiVanBanDTO loaiVanBan, CoQuanDonViDTO coQuanBanHanh,
                              CoQuanDonViDTO donViPhoBien, NguoiKyDTO nguoiKy, NguoiDungDTO nguoiNhap) {
        this.id = id;
        this.trichYeu = trichYeu;
        this.soHieu = soHieu;
        this.ngayDen = ngayDen;
        this.ngayBanHanh = ngayBanHanh;
        this.tepDinhKem = tepDinhKem;
        this.loaiVanBan = loaiVanBan;
        this.coQuanBanHanh = coQuanBanHanh;
        this.donViPhoBien = donViPhoBien;
        this.nguoiKy = nguoiKy;
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

    public LoaiVanBanDTO getLoaiVanBan() {
        return loaiVanBan;
    }

    public void setLoaiVanBan(LoaiVanBanDTO loaiVanBan) {
        this.loaiVanBan = loaiVanBan;
    }

    public CoQuanDonViDTO getCoQuanBanHanh() {
        return coQuanBanHanh;
    }

    public void setCoQuanBanHanh(CoQuanDonViDTO coQuanBanHanh) {
        this.coQuanBanHanh = coQuanBanHanh;
    }

    public CoQuanDonViDTO getDonViPhoBien() {
        return donViPhoBien;
    }

    public void setDonViPhoBien(CoQuanDonViDTO donViPhoBien) {
        this.donViPhoBien = donViPhoBien;
    }

    public NguoiKyDTO getNguoiKy() {
        return nguoiKy;
    }

    public void setNguoiKy(NguoiKyDTO nguoiKy) {
        this.nguoiKy = nguoiKy;
    }

    public NguoiDungDTO getNguoiNhap() {
        return nguoiNhap;
    }

    public void setNguoiNhap(NguoiDungDTO nguoiNhap) {
        this.nguoiNhap = nguoiNhap;
    }
}