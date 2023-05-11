package ru.kata.spring.boot_security.demo.dao;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.UserEntity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDaoImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return entityManager.createQuery("from UserEntity", UserEntity.class).getResultList();
    }


    @Override
    public Optional<UserEntity> findByEmail(String email) {
        TypedQuery<UserEntity> query = entityManager.createQuery("SELECT u FROM UserEntity u LEFT JOIN FETCH u.roles WHERE u.email = :email"
                , UserEntity.class);
        query.setParameter("email", email);
        UserEntity UserEntity;
        try {
            UserEntity = query.getSingleResult();
        } catch (NoResultException e) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        return Optional.ofNullable(UserEntity);
    }


    @Override
    public UserEntity getInfoByEmail(String email) {
        TypedQuery<UserEntity> query = entityManager.createQuery(
                "SELECT u FROM UserEntity u WHERE u.email = :email", UserEntity.class);
        return query.setParameter("email", email).getSingleResult();
    }

    @Override
    public void save(UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        entityManager.merge(user);
    }

    @Override
    public void update(UserEntity user) {
        entityManager.merge(user);

    }

    @Override
    public UserEntity getUser(Long id) {
        return entityManager.find(UserEntity.class,id);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(getUser(id));
    }
}
