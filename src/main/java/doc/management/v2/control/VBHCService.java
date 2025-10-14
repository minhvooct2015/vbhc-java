package doc.management.v2.control;

import doc.management.*;
import doc.management.v2.*;
import doc.management.v2.DTO.VanBanHanhChinhDTOV2;
import doc.management.v2.VanBanHanhChinh;
import doc.management.v2.mapper.EntityDtoMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static doc.management.DocService.FILE_NAME_CONCAT;


@ApplicationScoped
public class VBHCService {

    private static final Logger logger = LoggerFactory.getLogger(DocService.class);
    private static final String IMAGE_DIRECTORY = "src/main/resources/images";  // Change this to your desired directory

    @Inject
    VanBanHanhChinhRepo vanBanHanhChinhRepo;

    @Inject
    UserRepository userRepository;

    //test 2 case, 1 Truong hop la them moi nhung da ton tai,
//    1 cai la tao moi tu dau den cuoi
        @Transactional
        public void addDOc(MultipartBodyImageUpload multipartBodyImageUpload) {
            // Image upload logic (if applicable)
            VanBanHanhChinhDTOV2 vbhc = VanBanHanhChinhMapper.
                    fromString(multipartBodyImageUpload.getDocInfo(), VanBanHanhChinhDTOV2.class);

            VanBanHanhChinh vbhcE = EntityDtoMapper.toVanBanHanhChinhEntityShort(vbhc);
            vbhcE.setId(UUID.randomUUID().toString());

            String tenTaiKhoan = vbhc.getTenTaiKhoan();
            NguoiDung byTenDangNhap = userRepository.findByTenDangNhap(tenTaiKhoan);
            vbhcE.setNguoiNhap(byTenDangNhap);

            String donViPhoBien = vbhc.getDonViPhoBien();
            CoQuanDonVi coQuanDonViById1 = vanBanHanhChinhRepo.findOrAddCoQuanDonVi(donViPhoBien);
            vbhcE.setDonViPhoBien(coQuanDonViById1);

            String loaiVanBan = vbhc.getLoaiVanBan();
            LoaiVanBan loaiVanBanById = vanBanHanhChinhRepo.findOrAddLoaiVanBanByIdorTen(loaiVanBan);
            vbhcE.setLoaiVanBan(loaiVanBanById);

            String coQuanBanHanh = vbhc.getCoQuanBanHanh();
            CoQuanDonVi coQuanBanHanhById = vanBanHanhChinhRepo.findOrAddCoQuanDonVi(coQuanBanHanh);
            String tenCQBH = coQuanBanHanhById.getTenCoQuanDonVi();
            vbhcE.setCoQuanBanHanh(coQuanBanHanhById);

            String chucVuNguoiKy = vbhc.getChucVuNguoiKy();
            ChucVu chucVuById = vanBanHanhChinhRepo.findOrAddChucVu(chucVuNguoiKy);


            String nguoiKy = vbhc.getNguoiKy();
            NguoiKy nguoiKyById = vanBanHanhChinhRepo.findOrAddNguoiKy(nguoiKy, tenCQBH);

            NguoiKyGiuChucVu nguoiKyGiuChucVu = vanBanHanhChinhRepo.addNguoiKyGiuChucVu(nguoiKyById, chucVuById);

            vbhcE.setNguoiKyGiuChucVu(nguoiKyGiuChucVu);

            String imagePath = handleImageUpload(multipartBodyImageUpload.getFile(), vbhcE.getId(), vbhc.getTepDinhKem());  // Assuming handleImageUpload is a method that handles the file upload
            vbhcE.setTepDinhKem(imagePath);

            vanBanHanhChinhRepo.createVanBanHanhChinh(vbhcE);
        }

    @Transactional
    public VanBanHanhChinhDTOV2 update(MultipartBodyImageUpload multipartBodyImageUpload) {
        // Image upload logic (if applicable)
        VanBanHanhChinhDTOV2 vbhcDto = VanBanHanhChinhMapper.
                fromString(multipartBodyImageUpload.getDocInfo(), VanBanHanhChinhDTOV2.class);

        VanBanHanhChinh vbhcE = vanBanHanhChinhRepo.findVanBanHanhChinhById(vbhcDto.getId());
//        VanBanHanhChinh vbhcE = EntityDtoMapper.toVanBanHanhChinhEntityShort(vbhcDto);
//        vbhcE.setId(UUID.randomUUID().toString());

        String tenTaiKhoan = vbhcDto.getTenTaiKhoan();
        NguoiDung byTenDangNhap = userRepository.findByTenDangNhap(tenTaiKhoan);
        vbhcE.setNguoiNhap(byTenDangNhap);

        String donViPhoBien = vbhcDto.getDonViPhoBien();
        CoQuanDonVi coQuanDonViById1 = vanBanHanhChinhRepo.findOrAddCoQuanDonVi(donViPhoBien);
        vbhcE.setDonViPhoBien(coQuanDonViById1);

        String loaiVanBan = vbhcDto.getLoaiVanBan();
        LoaiVanBan loaiVanBanById = vanBanHanhChinhRepo.findOrAddLoaiVanBanByIdorTen(loaiVanBan);
        vbhcE.setLoaiVanBan(loaiVanBanById);

        String coQuanBanHanh = vbhcDto.getCoQuanBanHanh();
        CoQuanDonVi coQuanBanHanhById = vanBanHanhChinhRepo.findOrAddCoQuanDonVi(coQuanBanHanh);
        String tenCQBH = coQuanBanHanhById.getTenCoQuanDonVi();
        vbhcE.setCoQuanBanHanh(coQuanBanHanhById);

        String chucVuNguoiKy = vbhcDto.getChucVuNguoiKy();
        ChucVu chucVuById = vanBanHanhChinhRepo.findOrAddChucVu(chucVuNguoiKy);


        String nguoiKy = vbhcDto.getNguoiKy();
        NguoiKy nguoiKyById = vanBanHanhChinhRepo.findOrAddNguoiKy(nguoiKy, tenCQBH);

        NguoiKyGiuChucVu nguoiKyGiuChucVu = vanBanHanhChinhRepo.addNguoiKyGiuChucVu(nguoiKyById, chucVuById);

        vbhcE.setNguoiKyGiuChucVu(nguoiKyGiuChucVu);


//        vanBanHanhChinhRepo.createVanBanHanhChinh(vbhcE);
        if (multipartBodyImageUpload.getFile() != null) {
            String imagePath = handleImageUpload(multipartBodyImageUpload.getFile(), vbhcE.getId(), vbhcDto.getTepDinhKem());
            vbhcE.setTepDinhKem(imagePath);
        }
        return EntityDtoMapper.toVanBanHanhChinhDTOV2(vbhcE);
//        docRepo.updateVanBanHanhChinh(vanBanHanhChinh);
    }

    public void removeDoc(String id) {
            vanBanHanhChinhRepo.removeVanBanHanhChinh(id);
    }

    public List<VanBanHanhChinhDTOV2> search(VanBanHanhChinhDTOV2 vanBanHanhChinhDTOV2) {
        List<VanBanHanhChinh> vbhc = vanBanHanhChinhRepo.searchBy(vanBanHanhChinhDTOV2);
        List<VanBanHanhChinhDTOV2> objects = vbhc.stream().map(EntityDtoMapper::toVanBanHanhChinhDTOV2).toList();

        return objects;
    }



    public VanBanHanhChinhDTOV2 getVBHCById(String id){
        VanBanHanhChinh vanBanHanhChinhById = vanBanHanhChinhRepo.findVanBanHanhChinhById(id);
        VanBanHanhChinhDTOV2 vanBanHanhChinhDTO = EntityDtoMapper.toVanBanHanhChinhDTOV2(vanBanHanhChinhById);
        return vanBanHanhChinhDTO;
    }

    private String handleImageUpload(InputStream file, String id, String fileName) {
        try {
            Path directoryPath = Paths.get(IMAGE_DIRECTORY);
            String uniqueImageName = fileName + FILE_NAME_CONCAT + id +".pdf";
            Path filePath = directoryPath.resolve(uniqueImageName);

            if (!Files.exists(filePath)) {
                Files.copy(file, filePath);
            }

//            Files.copy(file, filePath, StandardCopyOption.REPLACE_EXISTING);

            return filePath.toString();  // Return the path where the image is saved
        } catch (IOException e) {
            throw new RuntimeException("Error uploading image", e);
        }
    }
}
