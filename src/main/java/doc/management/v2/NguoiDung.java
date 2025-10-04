package doc.management.v2;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NguoiDung  {
    @Id
    private String userId;

    @Column(nullable = false)
    private String hoTen;

    private String email;

    private String soDienThoai;

    @Column(nullable = false, unique = true)
    private String tenDangNhap;

    @Column(nullable = false)
    private String matKhau;
}