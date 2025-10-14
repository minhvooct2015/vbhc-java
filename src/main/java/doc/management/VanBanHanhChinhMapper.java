package doc.management;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class VanBanHanhChinhMapper {
    public static VanBanHanhChinh mapDtoToEntity(VanBanHanhChinhDTOOrg dto) {
        if (dto == null) return null;

        VanBanHanhChinh entity = new VanBanHanhChinh();
        entity.setTrichYeu(dto.getTrichYeu());
        entity.setSoHieu(dto.getSoHieu());
        entity.setLoaiVanBan(dto.getLoaiVanBan());
        entity.setCoQuanBanHanh(dto.getCoQuanBanHanh());
        entity.setNguoiKy(dto.getNguoiKy());
        entity.setChucVuNguoiKy(dto.getChucVuNguoiKy());
        entity.setDonViPhoBien(dto.getDonViPhoBien());
        entity.setNgayDen(dto.getNgayDen());
        entity.setNgayBanHanh(dto.getNgayBanHanh());
        entity.setNguoiPhoBien(dto.getNguoiPhoBien());
        entity.setTepDinhKem(dto.getTepDinhKem());
        return entity;
    }

    private static final ObjectMapper objectMapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Support Java 8+ java.time types
        mapper.registerModule(new JavaTimeModule());
        // Ignore unknown properties gracefully
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    /**
     * Map a JSON string to VanBanHanhChinhDTO.
     *
     * @param json The JSON string representing VanBanHanhChinhDTO
     * @return VanBanHanhChinhDTO object
     * @throws Exception if parsing fails
     */
    public static <T> T fromString(String json, Class<T> classType){
        try {
            return objectMapper.readValue(json, classType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Map a JSON string to VanBanHanhChinhDTO.
     *
     * @param json The JSON string representing VanBanHanhChinhDTO
     * @return VanBanHanhChinhDTO object
     * @throws Exception if parsing fails
     */
    public static VanBanHanhChinhDTOOrg fromString(String json){
        try {
            return objectMapper.readValue(json, VanBanHanhChinhDTOOrg.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static VanBanHanhChinhDTOOrg mapEntityToDto(VanBanHanhChinh entity) {
        if (entity == null) return null;

        VanBanHanhChinhDTOOrg dto = new VanBanHanhChinhDTOOrg();
        dto.setId(entity.getId());
        dto.setTrichYeu(entity.getTrichYeu());
        dto.setSoHieu(entity.getSoHieu());
        dto.setLoaiVanBan(entity.getLoaiVanBan());
        dto.setCoQuanBanHanh(entity.getCoQuanBanHanh());
        dto.setNguoiKy(entity.getNguoiKy());
        dto.setChucVuNguoiKy(entity.getChucVuNguoiKy());
        dto.setDonViPhoBien(entity.getDonViPhoBien());
        dto.setNgayDen(entity.getNgayDen());
        dto.setNgayBanHanh(entity.getNgayBanHanh());
        dto.setNguoiPhoBien(entity.getNguoiPhoBien());
        dto.setTepDinhKem(entity.getTepDinhKem());
        return dto;
    }
}
