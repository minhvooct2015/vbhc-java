package doc.management.v2.DTO;

public class NguoiKyDTO {
    private String nguoiKyId;
    private String hoTenNguoiKy;
    private CoQuanDonViDTO coQuanDonVi;

    public NguoiKyDTO() {}

    public NguoiKyDTO(String nguoiKyId, String hoTenNguoiKy, CoQuanDonViDTO coQuanDonVi) {
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

    public CoQuanDonViDTO getCoQuanDonVi() {
        return coQuanDonVi;
    }

    public void setCoQuanDonVi(CoQuanDonViDTO coQuanDonVi) {
        this.coQuanDonVi = coQuanDonVi;
    }
}