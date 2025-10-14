package doc.management.v2;
import doc.management.v2.DTO.VanBanHanhChinhDTOV2;
import doc.management.v2.control.TinhThanhPhoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class VanBanHanhChinhRepo {

    @Inject
    EntityManager em;

    @Inject
    TinhThanhPhoService tinhThanhPhoService;

    public CoQuanDonVi themCQ(String ten) {
        CoQuanDonVi ob = new CoQuanDonVi();
        TinhThanhPho matchingProvince = tinhThanhPhoService.findMatchingProvince(ten);
        if (matchingProvince !=null ) {
            ob.setTinhThanhPho(matchingProvince);
        }
        ob.setCoQuanDonViId(UUID.randomUUID().toString());
        ob.setTenCoQuanDonVi(ten);

        createCoQuanDonVi(ob);
        return ob;
    }

    public NguoiKy themNguoiKy(String ten, String coQuan) {
        NguoiKy ob = new NguoiKy();
        ob.setNguoiKyId(UUID.randomUUID().toString());
        ob.setHoTenNguoiKy(ten);

        CoQuanDonVi coQuanDonVi = findCoQuanDonViByIdOrTen(coQuan);

        if(coQuanDonVi == null) {
            coQuanDonVi = themCQ(coQuan);
        }
        ob.setCoQuanDonVi(coQuanDonVi);


        createNguoiKy(ob);
        return ob;
    }

    public LoaiVanBan themLoaiVB(String ten) {
        LoaiVanBan lvb = new LoaiVanBan(UUID.randomUUID().toString(), ten);
        createLoaiVanBan(lvb);
        return lvb;
    }

    // ----------- VanBanHanhChinh -----------
    @Transactional
    public void createVanBanHanhChinh(VanBanHanhChinh entity) {
        em.persist(entity);
    }

    @Transactional
    public VanBanHanhChinh updateVanBanHanhChinh(VanBanHanhChinh entity) {
        return em.merge(entity);
    }

    @Transactional
    public void removeVanBanHanhChinh(String id) {
        VanBanHanhChinh found = em.find(VanBanHanhChinh.class, id);
        if (found != null) {
            em.remove(found);
        }
    }

//    public List<VanBanHanhChinh> searchBy(VanBanHanhChinhDTOV2 dto) {
//        StringBuilder jpql = new StringBuilder("SELECT v FROM VanBanHanhChinh v WHERE 1=1");
//
//        // String fields (partial match)
//        if (dto.getTrichYeu() != null && !dto.getTrichYeu().isEmpty())
//            jpql.append(" AND LOWER(v.trichYeu) LIKE :trichYeu");
//        if (dto.getSoHieu() != null && !dto.getSoHieu().isEmpty())
//            jpql.append(" AND LOWER(v.soHieu) LIKE :soHieu");
//        if (dto.getTepDinhKem() != null && !dto.getTepDinhKem().isEmpty())
//            jpql.append(" AND LOWER(v.tepDinhKem) LIKE :tepDinhKem");
//
//        // Date fields (exact match)
//        if (dto.getNgayDen() != null)
//            jpql.append(" AND v.ngayDen = :ngayDen");
//        if (dto.getNgayBanHanh() != null)
//            jpql.append(" AND v.ngayBanHanh = :ngayBanHanh");
//
//        // Relationship: LoaiVanBan
//        LoaiVanBan loaiVanBanEntity = null;
//        if (dto.getLoaiVanBan() != null) {
//            loaiVanBanEntity = findLoaiVanBanByIdOrTen(dto.getLoaiVanBan());
//            if (loaiVanBanEntity != null)
//                jpql.append(" AND v.loaiVanBan = :loaiVanBan");
//        }
//
//        // Relationship: CoQuanDonVi (Ban Hanh)
//        CoQuanDonVi coQuanBanHanhEntity = null;
//        if (dto.getCoQuanBanHanh() != null) {
//            coQuanBanHanhEntity = findCoQuanDonViByIdOrTen(dto.getCoQuanBanHanh());
//            if (coQuanBanHanhEntity != null)
//                jpql.append(" AND v.coQuanBanHanh = :coQuanBanHanh");
//        }
//
//        // Relationship: DonViPhoBien
//        CoQuanDonVi donViPhoBienEntity = null;
//        if (dto.getDonViPhoBien() != null) {
//            donViPhoBienEntity = findCoQuanDonViByIdOrTen(dto.getDonViPhoBien());
//            if (donViPhoBienEntity != null)
//                jpql.append(" AND v.donViPhoBien = :donViPhoBien");
//        }
//
//        // Relationship: NguoiKyGiuChucVu (composite key)
//        NguoiKyGiuChucVu nguoiKyGiuChucVuEntity = null;
//        if (dto.getNguoiKy() != null && dto.getChucVuNguoiKy() != null) {
//            NguoiKyGiuChucVu.NguoiKyGiuChucVuId pk = new NguoiKyGiuChucVu.NguoiKyGiuChucVuId(dto.getNguoiKy(), dto.getChucVuNguoiKy());
//            nguoiKyGiuChucVuEntity = em.find(NguoiKyGiuChucVu.class, pk);
//            if (nguoiKyGiuChucVuEntity != null)
//                jpql.append(" AND v.nguoiKyGiuChucVu = :nguoiKyGiuChucVu");
//        }
//
//        List<NguoiKyGiuChucVu> nguoiKyGiuChucVuByNguoiKy = null;
//        if (dto.getNguoiKy() != null && dto.getChucVuNguoiKy() == null) {
//            NguoiKy nguoiKyByIdOrTen = findNguoiKyByIdOrTen(dto.getNguoiKy());
//            nguoiKyGiuChucVuByNguoiKy = findNguoiKyGiuChucVuByNguoiKy(nguoiKyByIdOrTen);
//            if (nguoiKyGiuChucVuByNguoiKy != null)
//                jpql.append(" AND v.nguoiKyGiuChucVu IN :nguoiKyGiuChucVuList");
//        }
//
//        List<NguoiKyGiuChucVu> nguoiKyGiuChucVuByChucVu = null;
//        if (dto.getChucVuNguoiKy() != null && dto.getNguoiKy() == null) {
//            ChucVu chucVu = findChucVuByIdOrTen(dto.getChucVuNguoiKy());
//            nguoiKyGiuChucVuByChucVu = findNguoiKyGiuChucVuByChucVu(chucVu);
//            if (nguoiKyGiuChucVuByChucVu != null)
//                jpql.append(" AND v.nguoiKyGiuChucVu IN :nguoiKyGiuChucVuList");
//        }
//
//        TypedQuery<VanBanHanhChinh> query = em.createQuery(jpql.toString(), VanBanHanhChinh.class);
//
//        // Set parameters
//        if (dto.getTrichYeu() != null && !dto.getTrichYeu().isEmpty())
//            query.setParameter("trichYeu", "%" + dto.getTrichYeu().toLowerCase() + "%");
//        if (dto.getSoHieu() != null && !dto.getSoHieu().isEmpty())
//            query.setParameter("soHieu", "%" + dto.getSoHieu().toLowerCase() + "%");
//        if (dto.getTepDinhKem() != null && !dto.getTepDinhKem().isEmpty())
//            query.setParameter("tepDinhKem", "%" + dto.getTepDinhKem().toLowerCase() + "%");
//
//        if (dto.getNgayDen() != null)
//            query.setParameter("ngayDen", dto.getNgayDen());
//        if (dto.getNgayBanHanh() != null)
//            query.setParameter("ngayBanHanh", dto.getNgayBanHanh());
//
//        if (loaiVanBanEntity != null)
//            query.setParameter("loaiVanBan", loaiVanBanEntity);
//        if (coQuanBanHanhEntity != null)
//            query.setParameter("coQuanBanHanh", coQuanBanHanhEntity);
//        if (donViPhoBienEntity != null)
//            query.setParameter("donViPhoBien", donViPhoBienEntity);
//
//        if (nguoiKyGiuChucVuEntity != null)
//            query.setParameter("nguoiKyGiuChucVu", nguoiKyGiuChucVuEntity);
//        if (nguoiKyGiuChucVuByNguoiKy != null && nguoiKyGiuChucVuByChucVu == null)
//            query.setParameter("nguoiKyGiuChucVuList", nguoiKyGiuChucVuByNguoiKy);
//
//        if (nguoiKyGiuChucVuByChucVu != null && nguoiKyGiuChucVuByNguoiKy == null)
//            query.setParameter("nguoiKyGiuChucVuList", nguoiKyGiuChucVuByChucVu);
//
//
//        return query.getResultList();
//    }



    public VanBanHanhChinh findVanBanHanhChinhById(String id) {
        return em.find(VanBanHanhChinh.class, id);
    }

    public List<VanBanHanhChinh> listVanBanHanhChinh() {
        return em.createQuery("SELECT v FROM VanBanHanhChinh v", VanBanHanhChinh.class).getResultList();
    }

    // ----------- LoaiVanBan -----------
    @Transactional
    public void createLoaiVanBan(LoaiVanBan entity) {
        em.persist(entity);
    }

    @Transactional
    public LoaiVanBan updateLoaiVanBan(LoaiVanBan entity) {
        return em.merge(entity);
    }

    @Transactional
    public void removeLoaiVanBan(String id) {
        LoaiVanBan found = em.find(LoaiVanBan.class, id);
        if (found != null) {
            em.remove(found);
        }
    }

    public LoaiVanBan findLoaiVanBanByIdOrTen(String idOrTen) {
        LoaiVanBan loaiVanBanById = em.find(LoaiVanBan.class, idOrTen);
        if (loaiVanBanById != null) return loaiVanBanById;

        LoaiVanBan loaiVanBanByTen =em.createQuery(
                        "SELECT l FROM LoaiVanBan l WHERE l.tenLoai = :tenLoai", LoaiVanBan.class)
                .setParameter("tenLoai", idOrTen)
                .getResultStream().findFirst().orElse(null);
        if (loaiVanBanByTen != null) return loaiVanBanByTen;
        return null;

    }

    public LoaiVanBan findOrAddLoaiVanBanByIdorTen(String idOrTen) {
        LoaiVanBan loaiVanBanByIdOrTen = findLoaiVanBanByIdOrTen(idOrTen);
        if (loaiVanBanByIdOrTen != null) return loaiVanBanByIdOrTen;
        return themLoaiVB(idOrTen);
    }

    public List<LoaiVanBan> findLoaiVanBanByTen(String tenLoai) {
        return em.createQuery(
                        "SELECT l FROM LoaiVanBan l WHERE l.tenLoai LIKE :tenLoai", LoaiVanBan.class)
                .setParameter("tenLoai", "%" + tenLoai + "%")
                .getResultList();
    }
    public List<LoaiVanBan> listLoaiVanBan() {
        return em.createQuery("SELECT l FROM LoaiVanBan l", LoaiVanBan.class).getResultList();
    }

    public List<TinhThanhPho> listTinhThanhPho() {
        return em.createQuery("SELECT l FROM TinhThanhPho l", TinhThanhPho.class).getResultList();
    }

    // ----------- CoQuanDonVi -----------
    @Transactional
    public void createCoQuanDonVi(CoQuanDonVi entity) {
        em.persist(entity);
    }

    @Transactional
    public CoQuanDonVi updateCoQuanDonVi(CoQuanDonVi entity) {
        return em.merge(entity);
    }

    @Transactional
    public void removeCoQuanDonVi(String id) {
        CoQuanDonVi found = em.find(CoQuanDonVi.class, id);
        if (found != null) {
            em.remove(found);
        }
    }

    @Transactional
    public CoQuanDonVi findOrAddCoQuanDonVi(String idOrTen) {
        CoQuanDonVi found = findCoQuanDonViByIdOrTen(idOrTen);
        if (found != null) {
           return found;
        }

        CoQuanDonVi coQuanDonVi = themCQ(idOrTen);
        return coQuanDonVi;
    }

    public CoQuanDonVi findCoQuanDonViByIdOrTen(String idOrTen) {
        CoQuanDonVi coQuanDonViById = em.find(CoQuanDonVi.class, idOrTen);
        if(coQuanDonViById != null) return  coQuanDonViById;

        CoQuanDonVi cq = em.createQuery(
                        "SELECT c FROM CoQuanDonVi c WHERE c.tenCoQuanDonVi = :ten", CoQuanDonVi.class)
                .setParameter("ten", idOrTen)
                .getResultStream().findFirst().orElse(null);
        if(cq != null) return cq;
        return null;
    }

    public List<CoQuanDonVi> listCoQuanDonVi() {
        return em.createQuery("SELECT c FROM CoQuanDonVi c", CoQuanDonVi.class).getResultList();
    }

    // ----------- ChucVu -----------
    @Transactional
    public void createChucVu(ChucVu entity) {
        em.persist(entity);
    }

    @Transactional
    public ChucVu updateChucVu(ChucVu entity) {
        return em.merge(entity);
    }

    @Transactional
    public void removeChucVu(String id) {
        ChucVu found = em.find(ChucVu.class, id);
        if (found != null) {
            em.remove(found);
        }
    }

    public ChucVu themChucVu(String ten) {
        ChucVu lvb = new ChucVu(UUID.randomUUID().toString(), ten);
        createChucVu(lvb);
        return lvb;
    }
    @Transactional
    public ChucVu findOrAddChucVu(String tenOrId) {
        ChucVu found = findChucVuByIdOrTen(tenOrId);
        if (found != null) {
            return found;
        }

        return themChucVu(tenOrId);
    }

    public ChucVu findChucVuByIdOrTen(String idOrTen) {
        ChucVu cq = em.createQuery(
                        "SELECT c FROM ChucVu c WHERE c.tenChucVu = :ten", ChucVu.class)
                .setParameter("ten", idOrTen)
                .getResultStream().findFirst().orElse(null);
        if (cq != null) return cq;

        ChucVu chucVuById = em.find(ChucVu.class, idOrTen);
        return chucVuById;
    }

    public List<ChucVu> listChucVu() {
        return em.createQuery("SELECT c FROM ChucVu c", ChucVu.class).getResultList();
    }

    // ----------- NguoiKy -----------
    @Transactional
    public void createNguoiKy(NguoiKy entity) {
        em.persist(entity);
    }

    @Transactional
    public NguoiKy updateNguoiKy(NguoiKy entity) {
        return em.merge(entity);
    }

    @Transactional
    public void removeNguoiKy(String id) {
        NguoiKy found = em.find(NguoiKy.class, id);
        if (found != null) {
            em.remove(found);
        }
    }

//    @Transactional
//    public NguoiKy findOrAddNguoiKy(String idOrTen, String tenCQBH) {
//
//        NguoiKy found = em.createQuery("SELECT n FROM NguoiKy n where n.hoTenNguoiKy = :ten and n.coQuanDonVi = :cq", NguoiKy.class)
//                .setParameter("ten", idOrTen)
//                .setParameter("cq", tenCQBH)
//                .getResultStream().findFirst().orElse(null);
//        if (found != null) {
//            return found;
//        }
//
//        return themNguoiKy(idOrTen, tenCQBH);
//    }

    @Transactional
    public NguoiKy findOrAddNguoiKy(String hoTenNguoiKy, String tenCQBH) {
        // Find CoQuanDonVi by name
        CoQuanDonVi cq = em.createQuery(
                        "SELECT c FROM CoQuanDonVi c WHERE c.tenCoQuanDonVi = :ten", CoQuanDonVi.class)
                .setParameter("ten", tenCQBH)
                .getResultStream().findFirst().orElse(null);

        if (cq == null) {
            throw new IllegalArgumentException("CoQuanDonVi not found: " + tenCQBH);
        }

        // Find NguoiKy by name and CoQuanDonVi
        NguoiKy found = em.createQuery(
                        "SELECT n FROM NguoiKy n WHERE n.hoTenNguoiKy = :ten AND n.coQuanDonVi = :cq",
                        NguoiKy.class)
                .setParameter("ten", hoTenNguoiKy)
                .setParameter("cq", cq)
                .getResultStream().findFirst().orElse(null);

        if (found != null) {
            return found;
        }

        return themNguoiKy(hoTenNguoiKy, tenCQBH);
    }

    public NguoiKy findNguoiKyByIdOrTen(String idOrTen) {

        NguoiKy cq = em.createQuery(
                        "SELECT c FROM NguoiKy c WHERE c.hoTenNguoiKy = :ten", NguoiKy.class)
                .setParameter("ten", idOrTen)
                .getResultStream().findFirst().orElse(null);
        if(cq != null) return cq;
        NguoiKy nguoiKyById = em.find(NguoiKy.class, idOrTen);
        return nguoiKyById;
    }

    public List<NguoiKy> listNguoiKy() {
        return em.createQuery("SELECT n FROM NguoiKy n", NguoiKy.class).getResultList();
    }

    // ----------- NguoiDung -----------
    @Transactional
    public void createNguoiDung(NguoiDung entity) {
        em.persist(entity);
    }

    @Transactional
    public NguoiDung updateNguoiDung(NguoiDung entity) {
        return em.merge(entity);
    }

    @Transactional
    public void removeNguoiDung(String id) {
        NguoiDung found = em.find(NguoiDung.class, id);
        if (found != null) {
            em.remove(found);
        }
    }

    public NguoiDung findNguoiDungById(String id) {
        return em.find(NguoiDung.class, id);
    }

    public List<NguoiDung> listNguoiDung() {
        return em.createQuery("SELECT n FROM NguoiDung n", NguoiDung.class).getResultList();
    }

    // ----------- NguoiKyGiuChucVu -----------
    @Transactional
    public void createNguoiKyGiuChucVu(NguoiKyGiuChucVu entity) {
        em.persist(entity);
    }

    @Transactional
    public NguoiKyGiuChucVu updateNguoiKyGiuChucVu(NguoiKyGiuChucVu entity) {
        return em.merge(entity);
    }

    @Transactional
    public NguoiKyGiuChucVu addNguoiKyGiuChucVu(String nguoiKyId, String chucVuId) {
        NguoiKy nguoiKy = findNguoiKyByIdOrTen(nguoiKyId);

        ChucVu chucVu  = findChucVuByIdOrTen(chucVuId);

        if (nguoiKy == null || chucVu == null) {
            throw new IllegalArgumentException("NguoiKy or ChucVu not found!");
        }

        // Check if already exists
        NguoiKyGiuChucVu.NguoiKyGiuChucVuId pk = new NguoiKyGiuChucVu.NguoiKyGiuChucVuId(nguoiKyId, chucVuId);
        NguoiKyGiuChucVu existing = em.find(NguoiKyGiuChucVu.class, pk);
        if (existing != null) {
            System.out.println("NguoiKyGiuChucVu already exists.");
            return existing;
        }

        NguoiKyGiuChucVu entity = new NguoiKyGiuChucVu();
        entity.setNguoiKy(nguoiKy);
        entity.setChucVu(chucVu);
        entity.setNgayBatDau(java.time.LocalDate.now());

        em.persist(entity);
        em.flush();
        return entity;
    }

    @Transactional
    public NguoiKyGiuChucVu addNguoiKyGiuChucVu(NguoiKy nguoiKy, ChucVu chucVu) {

        if (nguoiKy == null || chucVu == null) {
            throw new IllegalArgumentException("NguoiKy or ChucVu not found!");
        }

        // Check if already exists
        NguoiKyGiuChucVu.NguoiKyGiuChucVuId pk = new NguoiKyGiuChucVu.NguoiKyGiuChucVuId(nguoiKy.getNguoiKyId(), chucVu.getChucVuId());
        NguoiKyGiuChucVu existing = em.find(NguoiKyGiuChucVu.class, pk);


        if (existing != null) {
            System.out.println("NguoiKyGiuChucVu already exists.");
            return existing;
        }

        NguoiKyGiuChucVu entity = new NguoiKyGiuChucVu();
        entity.setNguoiKy(nguoiKy);
        entity.setChucVu(chucVu);
        entity.setNgayBatDau(java.time.LocalDate.now());

        em.persist(entity);
        em.flush();
        return entity;
    }

    @Transactional
    public void removeNguoiKyGiuChucVu(String nguoiKyId, String chucVuId) {
        NguoiKyGiuChucVu.NguoiKyGiuChucVuId pk = new NguoiKyGiuChucVu.NguoiKyGiuChucVuId(nguoiKyId, chucVuId);
        NguoiKyGiuChucVu found = em.find(NguoiKyGiuChucVu.class, pk);
        if (found != null) {
            em.remove(found);
        }
    }

    public NguoiKyGiuChucVu findNguoiKyGiuChucVuById(String nguoiKyId, String chucVuId) {
        NguoiKyGiuChucVu.NguoiKyGiuChucVuId pk = new NguoiKyGiuChucVu.NguoiKyGiuChucVuId(nguoiKyId, chucVuId);
        return em.find(NguoiKyGiuChucVu.class, pk);
    }

    public List<NguoiKyGiuChucVu> findNguoiKyGiuChucVuByNguoiKy(NguoiKy nguoiKy) {

        // Find CoQuanDonVi by name
        List<NguoiKyGiuChucVu> cq = em.createQuery(
                        "SELECT c FROM NguoiKyGiuChucVu c WHERE c.nguoiKy = :nguoiKy", NguoiKyGiuChucVu.class)
                .setParameter("nguoiKy", nguoiKy.getNguoiKyId())
                .getResultList();
        return cq;
    }

    public List<NguoiKyGiuChucVu> findNguoiKyGiuChucVuByChucVu(ChucVu chucVu) {

        // Find CoQuanDonVi by name
        List<NguoiKyGiuChucVu> cq = em.createQuery(
                        "SELECT c FROM NguoiKyGiuChucVu c WHERE c.chucVu = :chucVu", NguoiKyGiuChucVu.class)
                .setParameter("chucVu", chucVu.getChucVuId())
                .getResultList();
        return cq;
    }

    public List<NguoiKyGiuChucVu> listNguoiKyGiuChucVu() {
        return em.createQuery("SELECT n FROM NguoiKyGiuChucVu n", NguoiKyGiuChucVu.class).getResultList();
    }


    public List<VanBanHanhChinh> searchBy(VanBanHanhChinhDTOV2 dto) {
        StringBuilder jpql = new StringBuilder("SELECT v FROM VanBanHanhChinh v WHERE 1=1");

        // String fields (partial match, case-insensitive)
        if (dto.getTrichYeu() != null && !dto.getTrichYeu().isEmpty())
            jpql.append(" AND LOWER(v.trichYeu) LIKE :trichYeu");
        if (dto.getSoHieu() != null && !dto.getSoHieu().isEmpty())
            jpql.append(" AND LOWER(v.soHieu) LIKE :soHieu");
        if (dto.getTepDinhKem() != null && !dto.getTepDinhKem().isEmpty())
            jpql.append(" AND LOWER(v.tepDinhKem) LIKE :tepDinhKem");

        // Date fields (exact match)
        if (dto.getNgayDen() != null)
            jpql.append(" AND v.ngayDen = :ngayDen");
        if (dto.getNgayBanHanh() != null)
            jpql.append(" AND v.ngayBanHanh = :ngayBanHanh");

        // Relationship: LoaiVanBan
        LoaiVanBan loaiVanBanEntity = null;
        if (dto.getLoaiVanBan() != null) {
            loaiVanBanEntity = findLoaiVanBanByIdOrTen(dto.getLoaiVanBan());
            if (loaiVanBanEntity != null)
                jpql.append(" AND v.loaiVanBan = :loaiVanBan");
        }

        // Relationship: CoQuanDonVi (Ban Hanh)
        CoQuanDonVi coQuanBanHanhEntity = null;
        if (dto.getCoQuanBanHanh() != null) {
            coQuanBanHanhEntity = findCoQuanDonViByIdOrTen(dto.getCoQuanBanHanh());
            if (coQuanBanHanhEntity != null)
                jpql.append(" AND v.coQuanBanHanh = :coQuanBanHanh");
        }

        // Relationship: DonViPhoBien
        CoQuanDonVi donViPhoBienEntity = null;
        if (dto.getDonViPhoBien() != null) {
            donViPhoBienEntity = findCoQuanDonViByIdOrTen(dto.getDonViPhoBien());
            if (donViPhoBienEntity != null)
                jpql.append(" AND v.donViPhoBien = :donViPhoBien");
        }

        // ========== Mutually Exclusive Composite Key Logic ==========
        NguoiKyGiuChucVu nguoiKyGiuChucVuEntity = null;
        List<NguoiKyGiuChucVu> nguoiKyGiuChucVuByNguoiKy = null;
        List<NguoiKyGiuChucVu> nguoiKyGiuChucVuByChucVu = null;

        if (dto.getNguoiKy() != null && dto.getChucVuNguoiKy() != null) {
            // Composite key search (exact match)
            NguoiKyGiuChucVu.NguoiKyGiuChucVuId pk = new NguoiKyGiuChucVu.NguoiKyGiuChucVuId(dto.getNguoiKy(), dto.getChucVuNguoiKy());
            nguoiKyGiuChucVuEntity = em.find(NguoiKyGiuChucVu.class, pk);
            if (nguoiKyGiuChucVuEntity != null)
                jpql.append(" AND v.nguoiKyGiuChucVu = :nguoiKyGiuChucVu");
        } else if (dto.getNguoiKy() != null) {
            // Search by NguoiKy only (all ChucVu)
            NguoiKy nguoiKyByIdOrTen = findNguoiKyByIdOrTen(dto.getNguoiKy());
            nguoiKyGiuChucVuByNguoiKy = findNguoiKyGiuChucVuByNguoiKy(nguoiKyByIdOrTen);
            if (nguoiKyGiuChucVuByNguoiKy != null && !nguoiKyGiuChucVuByNguoiKy.isEmpty())
                jpql.append(" AND v.nguoiKyGiuChucVu IN :nguoiKyGiuChucVuList");
        } else if (dto.getChucVuNguoiKy() != null) {
            // Search by ChucVu only (all NguoiKy)
            ChucVu chucVu = findChucVuByIdOrTen(dto.getChucVuNguoiKy());
            nguoiKyGiuChucVuByChucVu = findNguoiKyGiuChucVuByChucVu(chucVu);
            if (nguoiKyGiuChucVuByChucVu != null && !nguoiKyGiuChucVuByChucVu.isEmpty())
                jpql.append(" AND v.nguoiKyGiuChucVu IN :nguoiKyGiuChucVuList");
        }
        // ===========================================================

        TypedQuery<VanBanHanhChinh> query = em.createQuery(jpql.toString(), VanBanHanhChinh.class);

        // Set parameters for string fields
        if (dto.getTrichYeu() != null && !dto.getTrichYeu().isEmpty())
            query.setParameter("trichYeu", "%" + dto.getTrichYeu().toLowerCase() + "%");
        if (dto.getSoHieu() != null && !dto.getSoHieu().isEmpty())
            query.setParameter("soHieu", "%" + dto.getSoHieu().toLowerCase() + "%");
        if (dto.getTepDinhKem() != null && !dto.getTepDinhKem().isEmpty())
            query.setParameter("tepDinhKem", "%" + dto.getTepDinhKem().toLowerCase() + "%");

        // Set parameters for date fields
        if (dto.getNgayDen() != null)
            query.setParameter("ngayDen", dto.getNgayDen());
        if (dto.getNgayBanHanh() != null)
            query.setParameter("ngayBanHanh", dto.getNgayBanHanh());

        // Set parameters for relationships
        if (loaiVanBanEntity != null)
            query.setParameter("loaiVanBan", loaiVanBanEntity);
        if (coQuanBanHanhEntity != null)
            query.setParameter("coQuanBanHanh", coQuanBanHanhEntity);
        if (donViPhoBienEntity != null)
            query.setParameter("donViPhoBien", donViPhoBienEntity);

        // Set parameters for composite key logic
        if (nguoiKyGiuChucVuEntity != null) {
            query.setParameter("nguoiKyGiuChucVu", nguoiKyGiuChucVuEntity);
        } else if (nguoiKyGiuChucVuByNguoiKy != null && !nguoiKyGiuChucVuByNguoiKy.isEmpty()) {
            query.setParameter("nguoiKyGiuChucVuList", nguoiKyGiuChucVuByNguoiKy);
        } else if (nguoiKyGiuChucVuByChucVu != null && !nguoiKyGiuChucVuByChucVu.isEmpty()) {
            query.setParameter("nguoiKyGiuChucVuList", nguoiKyGiuChucVuByChucVu);
        }
        // End composite key logic

        return query.getResultList();
    }
}
