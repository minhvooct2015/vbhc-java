package doc.management;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DocRepo {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void save(VanBanHanhChinh vanBanHanhChinh) {
        entityManager.persist(vanBanHanhChinh);
    }
}
