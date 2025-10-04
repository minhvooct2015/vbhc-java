package doc.management.v2.DTO;

public class ChucVuDTO {
    private String chucVuId;
    private String tenChucVu;

    public ChucVuDTO() {}

    public ChucVuDTO(String chucVuId, String tenChucVu) {
        this.chucVuId = chucVuId;
        this.tenChucVu = tenChucVu;
    }

    public String getChucVuId() {
        return chucVuId;
    }

    public void setChucVuId(String chucVuId) {
        this.chucVuId = chucVuId;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }
}
