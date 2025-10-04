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

    // ---- CoQuanDonVi ----
    public static CoQuanDonViDTO toCoQuanDonViDTO(CoQuanDonVi entity) {
        if (entity == null) return null;
        return new CoQuanDonViDTO(entity.getCoQuanDonViId(), entity.getTenCoQuanDonVi());
    }

    public static CoQuanDonVi toCoQuanDonViEntity(CoQuanDonViDTO dto) {
        if (dto == null) return null;
        return new CoQuanDonVi(dto.getCoQuanDonViId(), dto.getTenCoQuanDonVi());
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
        return new VanBanHanhChinhDTO(
                entity.getId(),
                entity.getTrichYeu(),
                entity.getSoHieu(),
                entity.getNgayDen(),
                entity.getNgayBanHanh(),
                entity.getTepDinhKem(),
                toLoaiVanBanDTO(entity.getLoaiVanBan()),
                toCoQuanDonViDTO(entity.getCoQuanBanHanh()),
                toCoQuanDonViDTO(entity.getDonViPhoBien()),
                toNguoiKyDTO(entity.getNguoiKy()),
                toNguoiDungDTO(entity.getNguoiNhap())
        );
    }

    public static VanBanHanhChinh toVanBanHanhChinhEntity(VanBanHanhChinhDTO dto) {
        if (dto == null) return null;
        return new VanBanHanhChinh(
                dto.getId(),
                dto.getTrichYeu(),
                dto.getSoHieu(),
                dto.getNgayDen(),
                dto.getNgayBanHanh(),
                dto.getTepDinhKem(),
                toLoaiVanBanEntity(dto.getLoaiVanBan()),
                toCoQuanDonViEntity(dto.getCoQuanBanHanh()),
                toCoQuanDonViEntity(dto.getDonViPhoBien()),
                toNguoiKyEntity(dto.getNguoiKy()),
                toNguoiDungEntity(dto.getNguoiNhap())
        );
    }

    // ---- Utility (Optional) ----
    public static List<VanBanHanhChinhDTO> toVanBanHanhChinhDTOList(List<VanBanHanhChinh> list) {
        if (list == null) return null;
        return list.stream().map(EntityDtoMapper::toVanBanHanhChinhDTO).collect(Collectors.toList());
    }

    public static List<VanBanHanhChinh> toVanBanHanhChinhEntityList(List<VanBanHanhChinhDTO> list) {
        if (list == null) return null;
        return list.stream().map(EntityDtoMapper::toVanBanHanhChinhEntity).collect(Collectors.toList());
    }
}
