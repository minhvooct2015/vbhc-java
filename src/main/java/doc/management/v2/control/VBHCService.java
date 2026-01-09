package doc.management.v2.control;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.StringUtils;
import doc.management.*;
import doc.management.v2.*;
import doc.management.v2.DTO.VanBanHanhChinhDTOV2;
import doc.management.v2.VanBanHanhChinh;
import doc.management.v2.mapper.EntityDtoMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import static doc.management.DocResource.IMAGE_DIRECTORY;
import static doc.management.DocService.FILE_NAME_CONCAT;


@ApplicationScoped
public class VBHCService {

    private static final Logger logger = LoggerFactory.getLogger(DocService.class);
    private static final String IMAGE_DIRECTORY = "src/main/resources/images";  // Change this to your desired directory

    @Inject
    VanBanHanhChinhRepo vanBanHanhChinhRepo;

    @Inject
    UserRepository userRepository;


    @Transactional
    public Objects test() {
        List<DataJson> allDocJsons = vanBanHanhChinhRepo.findAllDocJsons();

        for(DataJson json: allDocJsons) {
            String orgDocJson = json.getOrgDocJson();
            String latestJson = json.getOrgLatestDoc();

            compareAndPrintDiff(orgDocJson, latestJson);
        }
        return null;
    }

    @Inject
    ManagedExecutor managedExecutor;
    @Transactional
    public Map<String, List<JsonComparator.JsonDifference>> getJsonDiff(int ps, int pn) throws Exception {
        List<DataJson> allDocJsons = vanBanHanhChinhRepo.findAllDocJsons(ps, pn);
        Map<String, List<JsonComparator.JsonDifference>> results = new HashMap<>();

        // Collect all async tasks as futures
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (DataJson data: allDocJsons) {
            CompletableFuture<Void> future = managedExecutor.runAsync(() -> {
                try {
                    getOneJsonDiff(results, data);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            futures.add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        return results;
    }

    private void getOneJsonDiff(Map<String, List<JsonComparator.JsonDifference>> results, DataJson data) throws Exception {
        String orgDocJson = data.getOrgDocJson();
        String orgLatestDoc = data.getOrgLatestDoc();
        Set<String> excludes = Set.of("tepDinhKem", "ghiChu", "donViPhoBien", "nguoiPhoBien");
        List<JsonComparator.JsonDifference> diff = JsonComparator.diff(orgDocJson, orgLatestDoc, excludes);
        if(diff == null || diff.isEmpty()) {
            return;
        }
        VanBanHanhChinhDTOV2 vbhc = VanBanHanhChinhMapper.
                fromString(orgDocJson, VanBanHanhChinhDTOV2.class);
        String name =  vbhc.getTepDinhKem() + data.getId().toString();
        results.put(name, diff);
    }

    //test 2 case, 1 Truong hop la them moi nhung da ton tai,
//    1 cai la tao moi tu dau den cuoi
        @Transactional
        public VanBanHanhChinh addDOc(MultipartBodyImageUpload multipartBodyImageUpload, boolean isUpdated) {
            // Image upload logic (if applicable)
            String docInfo = multipartBodyImageUpload.getDocInfo();
            String orgDoc = multipartBodyImageUpload.getOrgDoc();

            DataJson dataJson = new DataJson();
            dataJson.setId(UUID.randomUUID().toString());
            dataJson.setOrgDocJson(orgDoc);
            dataJson.setOrgLatestDoc(docInfo);

            vanBanHanhChinhRepo.themDocJson(dataJson);

            VanBanHanhChinhDTOV2 vbhc = VanBanHanhChinhMapper.
                    fromString(docInfo, VanBanHanhChinhDTOV2.class);

            VanBanHanhChinh vbhcE = EntityDtoMapper.toVanBanHanhChinhEntityShort(vbhc);
            vbhcE.setId(UUID.randomUUID().toString());

            String tenTaiKhoan = vbhc.getNguoiPhoBien();
            NguoiDung byTenDangNhap = userRepository.findByTenDangNhap(tenTaiKhoan);
            vbhcE.setNguoiNhap(byTenDangNhap);

            String loaiVanBan = vbhc.getLoaiVanBan();
            LoaiVanBan loaiVanBanById = vanBanHanhChinhRepo.findOrAddLoaiVanBanByIdorTen(loaiVanBan);
            vbhcE.setLoaiVanBan(loaiVanBanById);

            vbhcE.setGhiChu(vbhc.getGhiChu());

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

            if(!isUpdated) {
                InputStream file = multipartBodyImageUpload.getFile();
                String imagePath = handleImageUpload(file, vbhcE.getId(), vbhc.getTepDinhKem());  // Assuming handleImageUpload is a method that handles the file upload
                vbhcE.setTepDinhKem(imagePath);
            }

            if(isUpdated && multipartBodyImageUpload.getFile()!= null) {
                InputStream file = multipartBodyImageUpload.getFile();
                String imagePath = handleImageUpload(file, vbhcE.getId(), vbhc.getTepDinhKem());  // Assuming handleImageUpload is a method that handles the file upload
                vbhcE.setTepDinhKem(imagePath);
            }


            vanBanHanhChinhRepo.createVanBanHanhChinh(vbhcE);

            List<String> donViPhoBien = vbhc.getDonViPhoBien();
            if(donViPhoBien != null && !donViPhoBien.isEmpty()) {
                List<CoQuanDonVi> donviphobienE = new ArrayList<>();
                donViPhoBien.forEach( dvpb -> {
                            CoQuanDonVi coQuanDonViById1 = vanBanHanhChinhRepo.findOrAddCoQuanDonVi(dvpb);
                            donviphobienE.add(coQuanDonViById1);

                            vanBanHanhChinhRepo.addDonViPb(coQuanDonViById1, vbhcE);
                        }
                );
            }

            return vbhcE;
        }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void compareAndPrintDiff(String json1, String json2) {
        try {
            // Parse thành Map (giữ thứ tự key nếu muốn)
            Map<String, Object> map1 = objectMapper.readValue(
                    json1, new TypeReference<LinkedHashMap<String, Object>>() {});
            Map<String, Object> map2 = objectMapper.readValue(
                    json2, new TypeReference<LinkedHashMap<String, Object>>() {});

            // Tổng hợp tập key
            Set<String> allKeys = new TreeSet<>();
            allKeys.addAll(map1.keySet());
            allKeys.addAll(map2.keySet());

            System.out.println("=== JSON DIFF ===");
            for (String key : allKeys) {
                boolean in1 = map1.containsKey(key);
                boolean in2 = map2.containsKey(key);

                if (!in1) {
                    System.out.printf("Key '%s' chỉ có trong JSON2, value2 = %s%n",
                            key, map2.get(key));
                    continue;
                }

                if (!in2) {
                    System.out.printf("Key '%s' chỉ có trong JSON1, value1 = %s%n",
                            key, map1.get(key));
                    continue;
                }

                Object v1 = map1.get(key);
                Object v2 = map2.get(key);

                if ((v1 == null && v2 != null) ||
                        (v1 != null && !v1.equals(v2))) {
                    System.out.printf(
                            "Khác nhau ở key '%s': value1 = %s, value2 = %s%n",
                            key, v1, v2
                    );
                }
            }
            System.out.println("=== END DIFF ===");

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi so sánh JSON", e);
        }
    }


    public VanBanHanhChinhDTOV2 update(MultipartBodyImageUpload multipartBodyImageUpload) {
        VanBanHanhChinhDTOV2 vbhcDto = VanBanHanhChinhMapper.
                fromString(multipartBodyImageUpload.getDocInfo(), VanBanHanhChinhDTOV2.class);
        String tepDinhKem = vanBanHanhChinhRepo.findVanBanHanhChinhById(vbhcDto.getId()).getTepDinhKem();
        removeDoc(vbhcDto.getId());

        String fileName = extractFileName(tepDinhKem);

        try {
            java.nio.file.Path imagePath = Paths.get(IMAGE_DIRECTORY, fileName);
            InputStream inputStream = Files.newInputStream(imagePath);
            if(multipartBodyImageUpload.getFile() == null) multipartBodyImageUpload.setFile(inputStream);
            addDOc(multipartBodyImageUpload, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return vbhcDto;
    }

    public static String buidNewImage(String input, String newId) {
        if (input == null) return null;
        // Get the part before -----
        String newName = input.split(FILE_NAME_CONCAT)[0];

        return newName + FILE_NAME_CONCAT + newId +".pdf"; // in case there's no slash
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

    public String mimeFromPath(String pathStr) throws IOException {
        Path p = Paths.get(pathStr);
        return Files.probeContentType(p); // may return null
    }

    public String mimeFromNameUsingURLConnection(String filename) {
        return URLConnection.guessContentTypeFromName(filename); // may return null
    }

    public static String extractFileName(String input) {
        if (input == null) return null;
        // Get the part before -----
        String[] split = input.split("/");


        return split[4];
    }

    public Path renameImage(String fileName, String newFileName) throws IOException {
        Path imagePath = Paths.get(IMAGE_DIRECTORY, fileName);
        Path newPath = Paths.get(IMAGE_DIRECTORY, newFileName);

        // ensure source exists
        if (Files.notExists(imagePath)) {
            throw new NoSuchFileException(imagePath.toString());
        }

        // perform the move (rename). REPLACE_EXISTING will overwrite if target exists.
        try {
            return Files.move(imagePath, newPath, StandardCopyOption.ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException ex) {
            // fallback to non-atomic move if atomic not supported on this filesystem
            return Files.move(imagePath, newPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
    @Transactional
    public VanBanHanhChinhDTOV2 updatebk(MultipartBodyImageUpload multipartBodyImageUpload) {
        // Image upload logic (if applicable)
        VanBanHanhChinhDTOV2 vbhcDto = VanBanHanhChinhMapper.
                fromString(multipartBodyImageUpload.getDocInfo(), VanBanHanhChinhDTOV2.class);

        VanBanHanhChinh vbhcE = vanBanHanhChinhRepo.findVanBanHanhChinhById(vbhcDto.getId());

        String nguoiPhoBien = vbhcDto.getNguoiPhoBien();
        if(nguoiPhoBien != null && nguoiPhoBien !="") {
            NguoiDung byTenDangNhap = userRepository.findByTenDangNhap(nguoiPhoBien);
            vbhcE.setNguoiNhap(byTenDangNhap);
        }

        vbhcE.setGhiChu(vbhcDto.getGhiChu());
//
//        String donViPhoBien = vbhcDto.getDonViPhoBien();
//        CoQuanDonVi coQuanDonViById1 = vanBanHanhChinhRepo.findOrAddCoQuanDonVi(donViPhoBien);
//        vbhcE.setDonViPhoBien(coQuanDonViById1);

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


        List<String> donViPhoBien = vbhcDto.getDonViPhoBien();
        if(donViPhoBien != null && !donViPhoBien.isEmpty()) {
            List<CoQuanDonVi> donviphobienE = new ArrayList<>();
            donViPhoBien.forEach( dvpb -> {
                        CoQuanDonVi coQuanDonViById1 = vanBanHanhChinhRepo.findOrAddCoQuanDonVi(dvpb);
                        donviphobienE.add(coQuanDonViById1);

                        vanBanHanhChinhRepo.addDonViPb(coQuanDonViById1, vbhcE);
                    }
            );
        }
        vanBanHanhChinhRepo.updateVanBanHanhChinh(vbhcE);

        return EntityDtoMapper.toVanBanHanhChinhDTOV2(vbhcE);
//        docRepo.updateVanBanHanhChinh(vanBanHanhChinh);
    }


    public void removeDoc(String id) {

            vanBanHanhChinhRepo.removeVanBanHanhChinh(id);
    }

    @Transactional
    public List<VanBanHanhChinhDTOV2> search(VanBanHanhChinhDTOV2 vanBanHanhChinhDTOV2) {
        List<VanBanHanhChinh> vbhc = vanBanHanhChinhRepo.searchBy(vanBanHanhChinhDTOV2);


//        if(vbhc == null) {
//            if (vanBanHanhChinhDTOV2.getDonViPhoBien() != null) {
//                List<String> donViPhoBiens = vanBanHanhChinhDTOV2.getDonViPhoBien();
//                List<VanBanHanhChinhDTOV2> flatList = donViPhoBiens.stream()
//                        .map(dv -> vanBanHanhChinhRepo.findCoQuanDonViByIdOrTen(dv))   // CoQuanDonVi or null
//                        .filter(Objects::nonNull)
//                        .flatMap(cqdv -> {
//                            List<VanBanHanhChinh> list = cqdv.getVanBanPhoBien();
//                            return (list == null) ? Stream.empty() : list.stream();
//                        })
//                        .map(this::mapEntityToDtoWithDonViPhoBien).toList();
//                return flatList;
//            }
//        }
//
//        else {
//            List<VanBanHanhChinh> vanBanHanhChinhs = vanBanHanhChinhRepo.searchByDonViPhoBien(vanBanHanhChinhDTOV2);
//            vbhc.addAll(vanBanHanhChinhs);
//        }
        List<VanBanHanhChinhDTOV2> objects = vbhc.stream().distinct().map(this::mapEntityToDtoWithDonViPhoBien).toList();

        return objects;
    }



    public VanBanHanhChinhDTOV2 getVBHCById(String id){
        VanBanHanhChinh vanBanHanhChinhById = vanBanHanhChinhRepo.findVanBanHanhChinhById(id);
        VanBanHanhChinhDTOV2 vanBanHanhChinhDTO = mapEntityToDtoWithDonViPhoBien(vanBanHanhChinhById);
        return vanBanHanhChinhDTO;
    }

    private VanBanHanhChinhDTOV2 mapEntityToDtoWithDonViPhoBien(VanBanHanhChinh vanBanHanhChinhById) {
        VanBanHanhChinhDTOV2 vanBanHanhChinhDTO = EntityDtoMapper.toVanBanHanhChinhDTOV2(vanBanHanhChinhById);
        List<String> tenDonViPhoBien = vanBanHanhChinhRepo.findCoQuanBanHanhByVbhcId(vanBanHanhChinhDTO.getId()).stream().map(CoQuanDonVi::getTenCoQuanDonVi).toList();
        vanBanHanhChinhDTO.setDonViPhoBien(tenDonViPhoBien);
        return vanBanHanhChinhDTO;
    }

    public List<VanBanHanhChinhDTOV2> getVBHCAll(){
        List<VanBanHanhChinh> vbchs = vanBanHanhChinhRepo.listVanBanHanhChinh();


        List<VanBanHanhChinhDTOV2> vanBanHanhChinhDTOV2s = vbchs.stream().map(EntityDtoMapper::toVanBanHanhChinhDTOV2).toList();
        vanBanHanhChinhDTOV2s.forEach(v -> {
            List<String> tenDonViPhoBien = vanBanHanhChinhRepo.findCoQuanBanHanhByVbhcId(v.getId()).stream().map(CoQuanDonVi::getTenCoQuanDonVi).toList();
            v.setDonViPhoBien(tenDonViPhoBien);
        });
        return vanBanHanhChinhDTOV2s;
    }


    private String handleImageUpload(InputStream file, String id, String fileName) {
        try {

            String mineType = getExtensionWithDot(fileName);
            Path directoryPath = Paths.get(IMAGE_DIRECTORY);
            String uniqueImageName = fileName + FILE_NAME_CONCAT + id + mineType;
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

    public String getExtensionWithDot(String filename) {
        if (filename == null || filename.isEmpty()) {
            return "";
        }

        // Strip directory path, keep only the last segment
        int sepPos = Math.max(filename.lastIndexOf('/'), filename.lastIndexOf('\\'));
        String name = (sepPos >= 0) ? filename.substring(sepPos + 1) : filename;

        int dotPos = name.lastIndexOf('.');
        // No dot, or dot is the first char (hidden file like .bashrc), or dot is last char ("file.")
        if (dotPos <= 0 || dotPos == name.length() - 1) {
            return "";
        }

        return name.substring(dotPos).toLowerCase();
    }
}
