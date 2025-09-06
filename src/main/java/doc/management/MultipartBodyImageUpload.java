package doc.management;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.PartType;

import java.io.InputStream;

public class MultipartBodyImageUpload {
    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private InputStream file;

    @FormParam("docInfo")
    @PartType(MediaType.TEXT_PLAIN)
    private String docInfo;

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }

    public String getDocInfo() {
        return docInfo;
    }

    public void setDocInfo(String docInfo) {
        this.docInfo = docInfo;
    }
}
