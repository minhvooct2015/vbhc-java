package doc.management;

import doc.management.v2.VanBanHanhChinhRepo;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.MultipartForm;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Path("/doc")
public class DocResource {

    public static final String IMAGE_DIRECTORY = "src/main/resources/images";

    @Inject
    DocService docService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response addSanPham(@MultipartForm MultipartBodyImageUpload multipartBodyImageUpload) {

        docService.addDOc(multipartBodyImageUpload);
        return Response.status(Response.Status.CREATED).build();
    }


    @PUT
    @Path("update/{id}")
    @Transactional
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    //    @RolesAllowed("ADMIN")
    public Response updateSanPham(
            @MultipartForm MultipartBodyImageUpload multipartBodyImageUpload
    ) {

        // Call the service layer to update the product
        docService.update(multipartBodyImageUpload);
        return Response.ok().build();
    }

    @DELETE
    @Path("delete/{id}")
    @Transactional
    public Response deleteSanPham(@PathParam("id") String id) {
        docService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("get/all")
    public List<VanBanHanhChinhDTO> getAllSanPham() {
        return docService.getAll();
    }

    @GET
    @Path("get/{id}")
    public VanBanHanhChinhDTO getSanPhamById(@PathParam("id") String id) {
        return docService.getById(id);
    }

    @POST
    @Path("/seach")
    public List<VanBanHanhChinhDTO> getSanPhamById(VanBanHanhChinhDTO vanBanHanhChinhDto) {
        return docService.search(vanBanHanhChinhDto);
    }


    @GET
    @Path("file/{fileName}")
//    @Produces({"image/jpeg", "image/png", "application/pdf"})
    public Response getImage(@jakarta.ws.rs.PathParam("fileName") String fileName) {
        try {
            java.nio.file.Path imagePath = Paths.get(IMAGE_DIRECTORY, fileName);
            if (!Files.exists(imagePath)) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }


            String mimeType = Files.probeContentType(imagePath);
            if ("application/pdf".equals(mimeType)) {
                return Response.ok(Files.newInputStream(imagePath)).type("application/pdf").build();
            } else if ("image/jpeg".equals(mimeType)) {
                return Response.ok(Files.newInputStream(imagePath)).type("image/jpeg").build();
            } else if ("image/png".equals(mimeType)) {
                return Response.ok(Files.newInputStream(imagePath)).type("image/png").build();
            } else {
                return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE)
                        .entity("Unsupported file type: " + mimeType)
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("src/main/resources/images/{fileName}")
    @Produces({"image/jpeg", "image/png", "application/pdf"})
    public Response getImagePath(@jakarta.ws.rs.PathParam("fileName") String fileName) {
        try {
            java.nio.file.Path imagePath = Paths.get(IMAGE_DIRECTORY, fileName);

            if (!Files.exists(imagePath)) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }


            return Response.ok(Files.newInputStream(imagePath)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


}
