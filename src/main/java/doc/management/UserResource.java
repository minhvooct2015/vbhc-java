package doc.management;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
public class UserResource {

    @Inject
    UserService userService;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(NguoiDungDTO request) {
        try {
            // Register the user
             userService.register(request);
            
            // Return the registered user in the response body with status CREATED (201)
            return Response.status(Response.Status.CREATED)
                    .build();
        } catch (Exception e) {
            // Handle any exceptions, log the error, and return an appropriate response
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error registering user: " + e.getMessage())
                    .build();
        }
    }

    @POST
    public Response login(DangNhapDTO request) {
        NguoiDung nguoiDung = userService.login(request.getTenDangNhap(), request.getMatKhau());
        if (nguoiDung != null) {
            // Build DTO to return (without password)
            NguoiDungDTO dto = new NguoiDungDTO();
            dto.setFromEntity(nguoiDung);
            return Response.ok(dto).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\": \"Tên đăng nhập hoặc mật khẩu không đúng\"}")
                    .build();
        }
    }
}
