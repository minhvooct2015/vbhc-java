package doc.management;

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
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class DocService {

    public static final String FILE_NAME_CONCAT = "-----";
    @Inject
    DocRepo docRepo;
    private static final Logger logger = LoggerFactory.getLogger(DocService.class);
    private static final String IMAGE_DIRECTORY = "src/main/resources/images";  // Change this to your desired directory

    @Transactional
    public void addDOc(MultipartBodyImageUpload multipartBodyImageUpload) {
        // Image upload logic (if applicable)
        VanBanHanhChinhDTOOrg vbhc = VanBanHanhChinhMapper.fromString(multipartBodyImageUpload.getDocInfo(), VanBanHanhChinhDTOOrg.class);
        VanBanHanhChinh vbhcE = VanBanHanhChinhMapper.mapDtoToEntity(vbhc);
            vbhcE.setId("VB" + String.format("%03d", (int) (Math.random() * 1000)));
            vbhcE.setNgayDen(LocalDate.now());
            String imagePath = handleImageUpload(multipartBodyImageUpload.getFile(), vbhcE.getId(), vbhc.getTepDinhKem());  // Assuming handleImageUpload is a method that handles the file upload
            vbhcE.setTepDinhKem(imagePath);
            docRepo.save(vbhcE);
            logger.info("new vbhc added successfully");
    }

    public void update(MultipartBodyImageUpload multipartBodyImageUpload) {
        VanBanHanhChinhDTOOrg vanBanHanhChinhDTOOrg = VanBanHanhChinhMapper.fromString(multipartBodyImageUpload.getDocInfo());
        VanBanHanhChinh vanBanHanhChinh = VanBanHanhChinhMapper.mapDtoToEntity(vanBanHanhChinhDTOOrg);

        if (multipartBodyImageUpload.getFile() != null) {
            String imagePath = handleImageUpload(multipartBodyImageUpload.getFile(), vanBanHanhChinh.getId(), vanBanHanhChinh.getTepDinhKem());
            vanBanHanhChinh.setTepDinhKem(imagePath);
        }

        docRepo.updateVanBanHanhChinh(vanBanHanhChinh);
    }

    public static String extractFileNameBeforeDelimiter(String input) {
        if (input == null) return null;
        // Get the part before -----
        String beforeDelimiter = input.split(FILE_NAME_CONCAT)[0];
        // Get the file name (after last /)
        int lastSlash = beforeDelimiter.lastIndexOf('/');
        if (lastSlash >= 0 && lastSlash < beforeDelimiter.length() - 1) {
            return beforeDelimiter.substring(lastSlash + 1);
        }
        return beforeDelimiter; // in case there's no slash
    }
    public List<VanBanHanhChinhDTOOrg> getAll() {
        // Image upload logic (if applicable)
        List<VanBanHanhChinh> all = docRepo.getAll();
        List<VanBanHanhChinhDTOOrg> vanBanHanhChinhDTOOrgs = all.stream().map(VanBanHanhChinhMapper::mapEntityToDto).toList();
        return vanBanHanhChinhDTOOrgs;
    }

    public VanBanHanhChinhDTOOrg getById(String id){
        return VanBanHanhChinhMapper.mapEntityToDto(docRepo.findById(id));
    }

    public  List<VanBanHanhChinhDTOOrg> search(VanBanHanhChinhDTOOrg vanBanHanhChinhDTOOrg){
        return docRepo.searchBy(vanBanHanhChinhDTOOrg).stream().map(VanBanHanhChinhMapper::mapEntityToDto).toList();
    }

    public void delete(String id) {
        docRepo.deleteById(id);
    }



    // Handle image upload and return the file path
    private String handleImageUpload(InputStream file, String id, String fileName) {
        try {
            Path directoryPath = Paths.get(IMAGE_DIRECTORY);
            String uniqueImageName = fileName + FILE_NAME_CONCAT + id +".pdf";
            Path filePath = directoryPath.resolve(uniqueImageName);
            Files.copy(file, filePath);

            return filePath.toString();  // Return the path where the image is saved
        } catch (IOException e) {
            throw new RuntimeException("Error uploading image", e);
        }
    }
}
