package doc.management.v2.DTO;

public class CoQuanDonViDTO {
    private String coQuanDonViId;
    private String tenCoQuanDonVi;

    public CoQuanDonViDTO() {}

    public CoQuanDonViDTO(String coQuanDonViId, String tenCoQuanDonVi) {
        this.coQuanDonViId = coQuanDonViId;
        this.tenCoQuanDonVi = tenCoQuanDonVi;
    }

    public String getCoQuanDonViId() {
        return coQuanDonViId;
    }

    public void setCoQuanDonViId(String coQuanDonViId) {
        this.coQuanDonViId = coQuanDonViId;
    }

    public String getTenCoQuanDonVi() {
        return tenCoQuanDonVi;
    }

    public void setTenCoQuanDonVi(String tenCoQuanDonVi) {
        this.tenCoQuanDonVi = tenCoQuanDonVi;
    }
}