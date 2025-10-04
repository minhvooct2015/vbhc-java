package doc.management.v2.DTO;

import java.time.LocalDate;

public class NguoiKyGiuChucVuDTO {
    private NguoiKyDTO nguoiKy;
    private ChucVuDTO chucVu;
    private LocalDate ngayBatDau;

    public NguoiKyGiuChucVuDTO() {}

    public NguoiKyGiuChucVuDTO(NguoiKyDTO nguoiKy, ChucVuDTO chucVu, LocalDate ngayBatDau) {
        this.nguoiKy = nguoiKy;
        this.chucVu = chucVu;
        this.ngayBatDau = ngayBatDau;
    }

    public NguoiKyDTO getNguoiKy() {
        return nguoiKy;
    }

    public void setNguoiKy(NguoiKyDTO nguoiKy) {
        this.nguoiKy = nguoiKy;
    }

    public ChucVuDTO getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVuDTO chucVu) {
        this.chucVu = chucVu;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }
}