package doc.management.v2.DTO;

public class TinhThanhPhoDTO {
    private String tinhThanhPhoId;
    private String tenTinhThanhPho;

    public TinhThanhPhoDTO() {}

    public TinhThanhPhoDTO(String tinhThanhPhoId, String tenTinhThanhPho) {
        this.tinhThanhPhoId = tinhThanhPhoId;
        this.tenTinhThanhPho = tenTinhThanhPho;
    }

    public String getTinhThanhPhoId() {
        return tinhThanhPhoId;
    }

    public void setTinhThanhPhoId(String tinhThanhPhoId) {
        this.tinhThanhPhoId = tinhThanhPhoId;
    }

    public String getTenTinhThanhPho() {
        return tenTinhThanhPho;
    }

    public void setTenTinhThanhPho(String tenTinhThanhPho) {
        this.tenTinhThanhPho = tenTinhThanhPho;
    }
}