package doc.management;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@ApplicationScoped
public class DocService {

    public static final String FILE_NAME_CONCAT = "-----";
    @Inject
    DocRepo docRepo;
    private static final Logger logger = LoggerFactory.getLogger(DocService.class);
    private static final String IMAGE_DIRECTORY = "src/main/resources/images";  // Change this to your desired directory

    @Transactional
    public void addSanPham(MultipartBodyImageUpload multipartBodyImageUpload) {
        // Image upload logic (if applicable)
        VanBanHanhChinhDTO vbhc = VanBanHanhChinhMapper.fromString(multipartBodyImageUpload.getDocInfo());
        VanBanHanhChinh vbhcE = VanBanHanhChinhMapper.mapDtoToEntity(vbhc);
            vbhcE.setId("VB" + String.format("%03d", (int) (Math.random() * 1000)));
            vbhcE.setNgayDen(LocalDate.now());
            String imagePath = handleImageUpload(multipartBodyImageUpload.getFile(), vbhcE.getId(), vbhc.getTepDinhKem());  // Assuming handleImageUpload is a method that handles the file upload
            vbhcE.setTepDinhKem(imagePath);
            docRepo.save(vbhcE);
            logger.info("new sp added successfully");
    }

    // Handle image upload and return the file path
    private String handleImageUpload(InputStream file, String maSp, String fileName) {
        try {
            Path directoryPath = Paths.get(IMAGE_DIRECTORY);
            String uniqueImageName = fileName + FILE_NAME_CONCAT + maSp +".pdf";
            Path filePath = directoryPath.resolve(uniqueImageName);
            Files.copy(file, filePath);

            return filePath.toString();  // Return the path where the image is saved
        } catch (IOException e) {
            throw new RuntimeException("Error uploading image", e);
        }
    }
}
