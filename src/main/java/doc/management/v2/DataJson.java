package doc.management.v2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "DataJson")
public class DataJson {

    @Id
    @Column(name = "Id", length = 100, nullable = false)
    private String id;

    @Lob
    @Column(name = "OrgDocJson", columnDefinition = "LONGTEXT")
    private String orgDocJson;

    @Lob
    @Column(name = "OrgLatestDoc", columnDefinition = "LONGTEXT")
    private String orgLatestDoc;

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
}