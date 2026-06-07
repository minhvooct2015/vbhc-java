package doc.management.v2;

import jakarta.persistence.*;

@Entity
@Table(name = "DataJson")
public class DataJson {

    @Id
    @Column(name = "Id", length = 100, nullable = false)
    private String id;

    @Column(name = "OrgDocJson")
    private String orgDocJson;

    @Column(name = "OrgLatestDoc")
    private String orgLatestDoc;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vanBanHanhChinhId", nullable = false)
    private VanBanHanhChinh vanBanHanhChinh;

    // Constructors
    public DataJson() {
    }

    public DataJson(String id, String orgDocJson, String orgLatestDoc) {
        this.id = id;
        this.orgDocJson = orgDocJson;
        this.orgLatestDoc = orgLatestDoc;
    }

    // Getters & Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgDocJson() {
        return orgDocJson;
    }

    public void setOrgDocJson(String orgDocJson) {
        this.orgDocJson = orgDocJson;
    }

    public String getOrgLatestDoc() {
        return orgLatestDoc;
    }

    public void setOrgLatestDoc(String orgLatestDoc) {
        this.orgLatestDoc = orgLatestDoc;
    }

    public VanBanHanhChinh getVanBanHanhChinh() {
        return vanBanHanhChinh;
    }

    public void setVanBanHanhChinh(VanBanHanhChinh vanBanHanhChinh) {
        this.vanBanHanhChinh = vanBanHanhChinh;
    }
}