package doc.management;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class DocRepo {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void save(VanBanHanhChinh vanBanHanhChinh) {
        entityManager.persist(vanBanHanhChinh);
    }

    public VanBanHanhChinh findById(String id) {
        return  entityManager.find(VanBanHanhChinh.class, id);
    }

    public List<VanBanHanhChinh> getAll() {
        String jpql = "SELECT v FROM VanBanHanhChinh v";
        return entityManager.createQuery(jpql, VanBanHanhChinh.class).getResultList();
    }

    public void deleteById(String id) {
        VanBanHanhChinh byId = findById(id);
        entityManager.remove(byId);
    }

    @Transactional
    public void updateVanBanHanhChinh(VanBanHanhChinh updatedEntity) {
        VanBanHanhChinh entity = findById(updatedEntity.getId());
        if (entity != null) {
            entity.setTrichYeu(updatedEntity.getTrichYeu());
            entity.setSoHieu(updatedEntity.getSoHieu());
            entity.setLoaiVanBan(updatedEntity.getLoaiVanBan());
            entity.setCoQuanBanHanh(updatedEntity.getCoQuanBanHanh());
            entity.setNguoiKy(updatedEntity.getNguoiKy());
            entity.setChucVuNguoiKy(updatedEntity.getChucVuNguoiKy());
            entity.setDonViPhoBien(updatedEntity.getDonViPhoBien());
            entity.setNgayDen(updatedEntity.getNgayDen());
            entity.setNgayBanHanh(updatedEntity.getNgayBanHanh());
            entity.setNguoiPhoBien(updatedEntity.getNguoiPhoBien());
            entity.setTepDinhKem(updatedEntity.getTepDinhKem());
        }
    }

    public List<VanBanHanhChinh> searchBy(VanBanHanhChinhDTOOrg dto) {
        StringBuilder jpql = new StringBuilder("SELECT v FROM VanBanHanhChinh v WHERE 1=1");
//        if (dto.getId() != null) jpql.append(" AND v.id = :id");
        if (dto.getTrichYeu() != null) jpql.append(" AND v.trichYeu = :trichYeu");
        if (dto.getSoHieu() != null) jpql.append(" AND v.soHieu = :soHieu");
        if (dto.getLoaiVanBan() != null) jpql.append(" AND v.loaiVanBan = :loaiVanBan");
        if (dto.getCoQuanBanHanh() != null) jpql.append(" AND v.coQuanBanHanh = :coQuanBanHanh");
        if (dto.getNguoiKy() != null) jpql.append(" AND v.nguoiKy = :nguoiKy");
        if (dto.getChucVuNguoiKy() != null) jpql.append(" AND v.chucVuNguoiKy = :chucVuNguoiKy");
        if (dto.getDonViPhoBien() != null) jpql.append(" AND v.donViPhoBien = :donViPhoBien");
        if (dto.getNgayDen() != null) jpql.append(" AND v.ngayDen = :ngayDen");
        if (dto.getNgayBanHanh() != null) jpql.append(" AND v.ngayBanHanh = :ngayBanHanh");
        if (dto.getNguoiPhoBien() != null) jpql.append(" AND v.nguoiPhoBien = :nguoiPhoBien");
        if (dto.getTepDinhKem() != null) jpql.append(" AND v.tepDinhKem = :tepDinhKem");
//        if (dto.getTenTaiKhoan() != null) jpql.append(" AND v.tenTaiKhoan = :tenTaiKhoan");

        TypedQuery<VanBanHanhChinh> query = entityManager.createQuery(jpql.toString(), VanBanHanhChinh.class);
//        if (dto.getId() != null) query.setParameter("id", dto.getId());
        if (dto.getTrichYeu() != null) query.setParameter("trichYeu", dto.getTrichYeu());
        if (dto.getSoHieu() != null) query.setParameter("soHieu", dto.getSoHieu());
        if (dto.getLoaiVanBan() != null) query.setParameter("loaiVanBan", dto.getLoaiVanBan());
        if (dto.getCoQuanBanHanh() != null) query.setParameter("coQuanBanHanh", dto.getCoQuanBanHanh());
        if (dto.getNguoiKy() != null) query.setParameter("nguoiKy", dto.getNguoiKy());
        if (dto.getChucVuNguoiKy() != null) query.setParameter("chucVuNguoiKy", dto.getChucVuNguoiKy());
        if (dto.getDonViPhoBien() != null) query.setParameter("donViPhoBien", dto.getDonViPhoBien());
        if (dto.getNgayDen() != null) query.setParameter("ngayDen", dto.getNgayDen());
        if (dto.getNgayBanHanh() != null) query.setParameter("ngayBanHanh", dto.getNgayBanHanh());
        if (dto.getNguoiPhoBien() != null) query.setParameter("nguoiPhoBien", dto.getNguoiPhoBien());
        if (dto.getTepDinhKem() != null) query.setParameter("tepDinhKem", dto.getTepDinhKem());
//        if (dto.getTenTaiKhoan() != null) query.setParameter("tenTaiKhoan", dto.getTenTaiKhoan());

        return query.getResultList();
    }
}
