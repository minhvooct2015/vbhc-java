package doc.management;

import doc.management.v2.NguoiDung;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserRepository {

    @Inject
    EntityManager entityManager;

    public NguoiDung findByTenDangNhap(String tenDangNhap) {
        try {
            return entityManager.createQuery(
                            "FROM NguoiDung WHERE tenDangNhap = :tenDangNhap", NguoiDung.class)
                    .setParameter("tenDangNhap", tenDangNhap)
                    .getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        }
    }


    @Transactional
    public void save(NguoiDung user) {
        entityManager.persist(user);
    }
}
