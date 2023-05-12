package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getRolesList() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> cq = cb.createQuery(Role.class);
        Root<Role> root = cq.from(Role.class);
        cq.orderBy(cb.asc(root.get("id")));
        cb.desc(root.get("role"));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role getRoleByRole(String role) {
        return entityManager.find(Role.class, role);
    }

    @Override
    public Role findRoleByAuthority(String authority) {
        return getRolesList().stream().filter(r -> authority.equals(r.getAuthority())).findFirst().orElseThrow(()-> new NoSuchElementException(String.format("Role %s not found", authority)));
    }
}