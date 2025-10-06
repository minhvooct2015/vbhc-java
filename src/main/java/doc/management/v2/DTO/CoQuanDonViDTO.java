package doc.management.v2.DTO;

public class CoQuanDonViDTO {
    private String coQuanDonViId;
    private String tenCoQuanDonVi;
    private TinhThanhPhoDTO tinhThanhPho;

    public CoQuanDonViDTO() {}

    public CoQuanDonViDTO(String coQuanDonViId, String tenCoQuanDonVi, TinhThanhPhoDTO tinhThanhPho) {
        this.coQuanDonViId = coQuanDonViId;
        this.tenCoQuanDonVi = tenCoQuanDonVi;
        this.tinhThanhPho = tinhThanhPho;
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

    public TinhThanhPhoDTO getTinhThanhPho() {
        return tinhThanhPho;
    }

    public void setTinhThanhPho(TinhThanhPhoDTO tinhThanhPho) {
        this.tinhThanhPho = tinhThanhPho;
    }
}