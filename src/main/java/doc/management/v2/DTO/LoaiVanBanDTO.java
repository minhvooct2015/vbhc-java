package doc.management.v2.DTO;

public class LoaiVanBanDTO {
    private String loaiVanBanId;
    private String tenLoai;

    public LoaiVanBanDTO() {}

    public LoaiVanBanDTO(String loaiVanBanId, String tenLoai) {
        this.loaiVanBanId = loaiVanBanId;
        this.tenLoai = tenLoai;
    }

    public String getLoaiVanBanId() {
        return loaiVanBanId;
    }

    public void setLoaiVanBanId(String loaiVanBanId) {
        this.loaiVanBanId = loaiVanBanId;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
