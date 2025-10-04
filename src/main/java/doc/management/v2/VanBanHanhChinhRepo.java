package doc.management.v2;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class VanBanHanhChinhRepo {

    @Inject
    EntityManager em;

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

    public LoaiVanBan findLoaiVanBanById(String id) {
        return em.find(LoaiVanBan.class, id);
    }

    public List<LoaiVanBan> listLoaiVanBan() {
        return em.createQuery("SELECT l FROM LoaiVanBan l", LoaiVanBan.class).getResultList();
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

    public CoQuanDonVi findCoQuanDonViById(String id) {
        return em.find(CoQuanDonVi.class, id);
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

    public ChucVu findChucVuById(String id) {
        return em.find(ChucVu.class, id);
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

    public NguoiKy findNguoiKyById(String id) {
        return em.find(NguoiKy.class, id);
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

    public List<NguoiKyGiuChucVu> listNguoiKyGiuChucVu() {
        return em.createQuery("SELECT n FROM NguoiKyGiuChucVu n", NguoiKyGiuChucVu.class).getResultList();
    }
}
