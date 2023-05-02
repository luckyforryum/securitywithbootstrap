package ru.kata.spring.boot_security.demo.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RoleDaoImpl implements RoleDao {

    private final EntityManager entityManager;

    @Autowired
    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public List<Role> getAllURoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Role getRoleById(Long id) {
        TypedQuery<Role> query = entityManager.createQuery(
                "SELECT u FROM Role u WHERE u.id = :id", Role.class);
        query.setParameter("id", id);
        Role role = query.getSingleResult();
        return role;
    }


}
