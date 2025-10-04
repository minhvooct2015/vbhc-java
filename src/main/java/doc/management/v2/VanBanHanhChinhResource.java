package doc.management.v2;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/van-ban-hanh-chinh")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VanBanHanhChinhResource {

    @Inject
    VanBanHanhChinhRepo vanBanHanhChinhRepo;
    @POST
    public Response themLoaiVB(@QueryParam("ten") String ten) {
        LoaiVanBan lvb = new LoaiVanBan(UUID.randomUUID().toString(), ten);
        vanBanHanhChinhRepo.createLoaiVanBan(lvb);
        return Response.status(Response.Status.CREATED).build();
    }
}
