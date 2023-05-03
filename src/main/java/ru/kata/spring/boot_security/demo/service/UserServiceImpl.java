package ru.kata.spring.boot_security.demo.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.UserEntity;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List<UserEntity> getAllUsers() {
        return userDao.getAllUsers();
    }



    @Override
    @Transactional
    public Optional<UserEntity> findByEmail(String email) {
        return userDao.findByEmail(email);
    }


    @Override
    @Transactional
    public UserEntity getInfoByEmail(String email) {
        return userDao.getInfoByEmail(email);
    }

    @Override
    @Transactional
    public void save(UserEntity user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void update(UserEntity user) {
        userDao.update(user);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }
}
