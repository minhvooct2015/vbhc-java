package doc.management.v2;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/doc/v2")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VanBanHanhChinhResource {

    @Inject
    VanBanHanhChinhRepo vanBanHanhChinhRepo;
    @POST
    @Path("loaivb")
    public Response themLoaiVB(@QueryParam("ten") String ten) {
        LoaiVanBan lvb = new LoaiVanBan(UUID.randomUUID().toString(), ten);
        vanBanHanhChinhRepo.createLoaiVanBan(lvb);
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
}
