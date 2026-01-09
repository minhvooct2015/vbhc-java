package doc.management.v2.DTO;

public class DonViPhoBienDTO {
    private String cqdvId;
    private String vbhcId;

    public DonViPhoBienDTO() {}

    public DonViPhoBienDTO(String cqdvId, String vbhcId) {
        this.cqdvId = cqdvId;
        this.vbhcId = vbhcId;
    }

    public String getCqdvId() {
        return cqdvId;
    }

    public void setCqdvId(String cqdvId) {
        this.cqdvId = cqdvId;
    }

    public String getVbhcId() {
        return vbhcId;
    }

    public void setVbhcId(String vbhcId) {
        this.vbhcId = vbhcId;
    }
}