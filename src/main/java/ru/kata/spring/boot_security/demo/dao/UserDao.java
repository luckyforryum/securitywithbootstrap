package ru.kata.spring.boot_security.demo.dao;
import ru.kata.spring.boot_security.demo.model.UserEntity;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    public List<UserEntity> getAllUsers();

    Optional<UserEntity> findByEmail(String email);

    public UserEntity getInfoByEmail(String email);

    public void save(UserEntity user);

    public void update(UserEntity user);

    public UserEntity getUser(Long id);
    public void delete(Long id);
}
