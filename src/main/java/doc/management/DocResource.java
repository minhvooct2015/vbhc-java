package doc.management;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.MultipartForm;

@Path("/doc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DocResource {

    public static final String IMAGE_DIRECTORY = "src/main/resources/images";

    @Inject
    DocService docService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response addSanPham(@MultipartForm MultipartBodyImageUpload multipartBodyImageUpload) {


        docService.addSanPham(multipartBodyImageUpload);
        return Response.status(Response.Status.CREATED).build();
    }

}
