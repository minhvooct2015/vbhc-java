package doc.management.v2;

import doc.management.MultipartBodyImageUpload;
import doc.management.VanBanHanhChinhDTOOrg;
import doc.management.v2.DTO.VanBanHanhChinhDTOV2;
import doc.management.v2.control.TinhThanhPhoService;
import doc.management.v2.control.VBHCService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.MultipartForm;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static doc.management.DocResource.IMAGE_DIRECTORY;

@Path("/doc/v2")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VanBanHanhChinhResource {

    @Inject
    VanBanHanhChinhRepo vanBanHanhChinhRepo;

    @Inject
    TinhThanhPhoService tinhThanhPhoService;

    @Inject
    VBHCService vbhcService;


    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("themVB")
    public Response themVB(@MultipartForm MultipartBodyImageUpload multipartBodyImageUpload) {

        vbhcService.addDOc(multipartBodyImageUpload);
        return Response.status(Response.Status.CREATED).build();
    }


    @POST
    @Path("chuc-vu")
    public Response themChucVu(@QueryParam("ten") String ten) {
      vanBanHanhChinhRepo.themChucVu(ten);
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("co-quan")
    public Response themCQ(@QueryParam("ten") String ten) {
        themCQ(ten);
        return Response.status(Response.Status.CREATED).build();
    }



    @POST
    @Path("nguoi-ky")
    public Response themNguoiKy(@QueryParam("ten") String ten, @QueryParam("CoquanId") String coQuan) {
        vanBanHanhChinhRepo.themNguoiKy(ten, coQuan);
        return Response.status(Response.Status.CREATED).build();
    }



    @POST
    @Path("loaivb")
    public Response themLoaiVB(@QueryParam("ten") String ten) {
        vanBanHanhChinhRepo.themLoaiVB(ten);
        return Response.status(Response.Status.CREATED).build();
    }


    @GET
    @Path("loaivb")
    public Response timLoaiVB(@QueryParam("ten") String ten) {
        return Response.ok(vanBanHanhChinhRepo.findLoaiVanBanByTen(ten)).build();
    }

    @GET
    @Path("loaivb/all")
    public Response timAllLoaiVB() {
        return Response.ok(vanBanHanhChinhRepo.listLoaiVanBan()).build();
    }

    @GET
    @Path("tinhTP/all")
    public Response timAllTinhTP() {
        return Response.ok(vanBanHanhChinhRepo.listTinhThanhPho()).build();
    }

    @GET
    @Path("doc/{id}")
    public Response getDoc(@jakarta.ws.rs.PathParam("id") String id) {
        VanBanHanhChinhDTOV2 vbhcById = vbhcService.getVBHCById(id);
        return Response.ok(vbhcById).build();
    }

    @DELETE
    @Path("doc/{id}")
    public Response removeDoc(@jakarta.ws.rs.PathParam("id") String id) {
        vbhcService.removeDoc(id);
        return Response.ok().build();
    }

    @POST
    @Path("/seach")
    public List<VanBanHanhChinhDTOV2> getSanPhamById(VanBanHanhChinhDTOV2 vanBanHanhChinhDTOV2) {
        List<VanBanHanhChinhDTOV2> search = vbhcService.search(vanBanHanhChinhDTOV2);
        return search;
    }


    @PUT
    @Path("update")
    @Transactional
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    //    @RolesAllowed("ADMIN")
    public Response updateSanPham(
            @MultipartForm MultipartBodyImageUpload multipartBodyImageUpload
    ) {

        // Call the service layer to update the product
//        docService.update(multipartBodyImageUpload);
        VanBanHanhChinhDTOV2 update = vbhcService.update(multipartBodyImageUpload);
        return Response.ok(update).build();
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
