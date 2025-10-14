package doc.management.v2.mapper;

import doc.management.v2.DTO.*;
import doc.management.v2.*;

import java.util.List;
import java.util.stream.Collectors;

public class EntityDtoMapper {

    // ---- LoaiVanBan ----
    public static LoaiVanBanDTO toLoaiVanBanDTO(LoaiVanBan entity) {
        if (entity == null) return null;
        return new LoaiVanBanDTO(entity.getLoaiVanBanId(), entity.getTenLoai());
    }

    public static LoaiVanBan toLoaiVanBanEntity(LoaiVanBanDTO dto) {
        if (dto == null) return null;
        return new LoaiVanBan(dto.getLoaiVanBanId(), dto.getTenLoai());
    }

    // --- TinhThanhPho ---
    public static TinhThanhPhoDTO toTinhThanhPhoDTO(TinhThanhPho entity) {
        if (entity == null) return null;
        return new TinhThanhPhoDTO(entity.getTinhThanhPhoId(), entity.getTenTinhThanhPho());
    }

    public static TinhThanhPho toTinhThanhPhoEntity(TinhThanhPhoDTO dto) {
        if (dto == null) return null;
        return new TinhThanhPho(dto.getTinhThanhPhoId(), dto.getTenTinhThanhPho());
    }

    // --- Update CoQuanDonVi mapping to include TinhThanhPho ---
    public static CoQuanDonViDTO toCoQuanDonViDTO(CoQuanDonVi entity) {
        if (entity == null) return null;
        return new CoQuanDonViDTO(
                entity.getCoQuanDonViId(),
                entity.getTenCoQuanDonVi(),
                toTinhThanhPhoDTO(entity.getTinhThanhPho())
        );
    }

    public static CoQuanDonVi toCoQuanDonViEntity(CoQuanDonViDTO dto) {
        if (dto == null) return null;
        return new CoQuanDonVi(
                dto.getCoQuanDonViId(),
                dto.getTenCoQuanDonVi(),
                toTinhThanhPhoEntity(dto.getTinhThanhPho())
        );
    }


    // ---- ChucVu ----
    public static ChucVuDTO toChucVuDTO(ChucVu entity) {
        if (entity == null) return null;
        return new ChucVuDTO(entity.getChucVuId(), entity.getTenChucVu());
    }

    public static ChucVu toChucVuEntity(ChucVuDTO dto) {
        if (dto == null) return null;
        return new ChucVu(dto.getChucVuId(), dto.getTenChucVu());
    }

    // ---- NguoiKy ----
    public static NguoiKyDTO toNguoiKyDTO(NguoiKy entity) {
        if (entity == null) return null;
        return new NguoiKyDTO(
                entity.getNguoiKyId(),
                entity.getHoTenNguoiKy(),
                toCoQuanDonViDTO(entity.getCoQuanDonVi())
        );
    }

    public static NguoiKy toNguoiKyEntity(NguoiKyDTO dto) {
        if (dto == null) return null;
        return new NguoiKy(
                dto.getNguoiKyId(),
                dto.getHoTenNguoiKy(),
                toCoQuanDonViEntity(dto.getCoQuanDonVi())
        );
    }

    // ---- NguoiDung ----
    public static NguoiDungDTO toNguoiDungDTO(NguoiDung entity) {
        if (entity == null) return null;
        return new NguoiDungDTO(
                entity.getUserId(),
                entity.getHoTen(),
                entity.getEmail(),
                entity.getSoDienThoai(),
                entity.getTenDangNhap(),
                entity.getMatKhau()
        );
    }

    public static NguoiDungDTO toNguoiDungDTOShort(NguoiDung entity) {
        if (entity == null) return null;
        return new NguoiDungDTO(
                entity.getUserId(),
                entity.getHoTen(),
                entity.getEmail(),
                entity.getSoDienThoai(),
                entity.getTenDangNhap()
        );
    }

    public static NguoiDung toNguoiDungEntity(NguoiDungDTO dto) {
        if (dto == null) return null;
        return new NguoiDung(
                dto.getUserId(),
                dto.getHoTen(),
                dto.getEmail(),
                dto.getSoDienThoai(),
                dto.getTenDangNhap(),
                dto.getMatKhau()
        );
    }

    // ---- NguoiKyGiuChucVu ----
    public static NguoiKyGiuChucVuDTO toNguoiKyGiuChucVuDTO(NguoiKyGiuChucVu entity) {
        if (entity == null) return null;
        return new NguoiKyGiuChucVuDTO(
                toNguoiKyDTO(entity.getNguoiKy()),
                toChucVuDTO(entity.getChucVu()),
                entity.getNgayBatDau()
        );
    }

    public static NguoiKyGiuChucVu toNguoiKyGiuChucVuEntity(NguoiKyGiuChucVuDTO dto) {
        if (dto == null) return null;
        return new NguoiKyGiuChucVu(
                toNguoiKyEntity(dto.getNguoiKy()),
                toChucVuEntity(dto.getChucVu()),
                dto.getNgayBatDau()
        );
    }

    // ---- VanBanHanhChinh ----
    public static VanBanHanhChinhDTO toVanBanHanhChinhDTO(VanBanHanhChinh entity) {
        if (entity == null) return null;
       return null;
//        return new VanBanHanhChinhDTO(
//                entity.getId(),
//                entity.getTrichYeu(),
//                entity.getSoHieu(),
//                entity.getNgayDen(),
//                entity.getNgayBanHanh(),
//                entity.getTepDinhKem(),
//                toLoaiVanBanDTO(entity.getLoaiVanBan()),
//                toCoQuanDonViDTO(entity.getCoQuanBanHanh()),
//                toCoQuanDonViDTO(entity.getDonViPhoBien()),
//                toNguoiKyDTO(entity.getNguoiKyGiuChucVu()),
//                toNguoiDungDTO(entity.getNguoiNhap())
//        );
    }

    public static VanBanHanhChinhDTOV2 toVanBanHanhChinhDTOV2(VanBanHanhChinh entity) {
        if (entity == null) return null;
        VanBanHanhChinhDTOV2 dto = new VanBanHanhChinhDTOV2();


        NguoiKyGiuChucVu nguoiKyGiuChucVu = entity.getNguoiKyGiuChucVu();
        String tenChucVu = nguoiKyGiuChucVu.getChucVu().getTenChucVu();
        String hoTenNguoiKy = nguoiKyGiuChucVu.getNguoiKy().getHoTenNguoiKy();
        CoQuanDonVi coQuanBanHanh = entity.getCoQuanBanHanh();

        dto.setId(entity.getId());
        dto.setTrichYeu(entity.getTrichYeu());
        dto.setSoHieu(entity.getSoHieu());
        dto.setLoaiVanBan(entity.getLoaiVanBan().getTenLoai());
        dto.setChucVuNguoiKy(tenChucVu);
        dto.setCoQuanBanHanh(coQuanBanHanh.getTenCoQuanDonVi());
        dto.setNguoiKy(hoTenNguoiKy);
        dto.setDonViPhoBien(entity.getDonViPhoBien().getTenCoQuanDonVi());
        dto.setNgayDen(entity.getNgayDen());
        dto.setNgayBanHanh(entity.getNgayBanHanh());
        NguoiDung nguoiNhap = entity.getNguoiNhap();
        dto.setNguoiPhoBien(nguoiNhap.getHoTen());
        dto.setTenTaiKhoan(nguoiNhap.getTenDangNhap());
        dto.setTepDinhKem(entity.getTepDinhKem());

        return dto;
    }

//    public static VanBanHanhChinh toVanBanHanhChinhEntity(VanBanHanhChinhDTO dto) {
//        if (dto == null) return null;
//        return new VanBanHanhChinh(
//                dto.getId(),
//                dto.getTrichYeu(),
//                dto.getSoHieu(),
//                dto.getNgayDen(),
//                dto.getNgayBanHanh(),
//                dto.getTepDinhKem(),
//                toLoaiVanBanEntity(dto.getLoaiVanBan()),
//                toCoQuanDonViEntity(dto.getCoQuanBanHanh()),
//                toCoQuanDonViEntity(dto.getDonViPhoBien()),
//                toNguoiKyEntity(dto.getNguoiKy()),
//                toNguoiDungEntity(dto.getNguoiNhap())
//        );
//    }

    public static VanBanHanhChinh toVanBanHanhChinhEntityShort(VanBanHanhChinhDTOV2 dto) {
        VanBanHanhChinh vanBanHanhChinh = new VanBanHanhChinh();

        vanBanHanhChinh.setId(dto.getId());
        vanBanHanhChinh.setTrichYeu(dto.getTrichYeu());
        vanBanHanhChinh.setSoHieu(dto.getSoHieu());
        vanBanHanhChinh.setNgayDen(dto.getNgayDen());
        vanBanHanhChinh.setNgayBanHanh(dto.getNgayBanHanh());
        vanBanHanhChinh.setTepDinhKem(dto.getTepDinhKem());
        return vanBanHanhChinh;
    }

//    toLoaiVanBanEntity(dto.getLoaiVanBan()),
//    toCoQuanDonViEntity(dto.getCoQuanBanHanh()),
//    toCoQuanDonViEntity(dto.getDonViPhoBien()),
//    toNguoiKyEntity(dto.getNguoiKy()),
//    toNguoiDungEntity(dto.getNguoiNhap())
    // ---- Utility (Optional) ----
    public static List<VanBanHanhChinhDTO> toVanBanHanhChinhDTOList(List<VanBanHanhChinh> list) {
        if (list == null) return null;
        return list.stream().map(EntityDtoMapper::toVanBanHanhChinhDTO).collect(Collectors.toList());
    }

    public static List<VanBanHanhChinh> toVanBanHanhChinhEntityList(List<VanBanHanhChinhDTO> list) {
        if (list == null) return null;
//        return list.stream().map(EntityDtoMapper::toVanBanHanhChinhEntity).collect(Collectors.toList());
        return null;
    }
}
