package doc.management;
import doc.management.v2.DTO.NguoiDungDTO;
import doc.management.v2.NguoiDung;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.UUID;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    public NguoiDung register(NguoiDungDTO request) {
        // Check if the user already exists
        if (userRepository.findByTenDangNhap(request.getTenDangNhap()) != null) {
            throw new BadRequestException("User already exists");
        }

        // Create a new User entity
        NguoiDung newUser = new NguoiDung();
        newUser.setUserId(UUID.randomUUID().toString());
        newUser.setHoTen(request.getHoTen());
        newUser.setEmail(request.getEmail());
        newUser.setSoDienThoai(request.getSoDienThoai());
        newUser.setMatKhau(BCrypt.hashpw(request.getMatKhau(), BCrypt.gensalt()));
        newUser.setTenDangNhap(request.getTenDangNhap());

        // Persist the new user to the database
        userRepository.save(newUser);
        return newUser;
    }

    public boolean validatePassword(String rawPassword, String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }

    // Login logic: returns user if login is successful, else null
    public NguoiDung login(String tenDangNhap, String matKhau) {
        NguoiDung user = userRepository.findByTenDangNhap(tenDangNhap);
        if (user != null && validatePassword(matKhau, user.getMatKhau())) {
            return user;
        }
        return null;
    }
}
