//package ru.kata.spring.boot_security.demo.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ru.kata.spring.boot_security.demo.dao.UserDAO;
//import ru.kata.spring.boot_security.demo.models.User;
//
//
//import java.util.List;
//
//
//@Service
//@Transactional(readOnly = true)
//public class UsersServiceImpl implements UsersService {
//
//    private final UserDAO usersDao;
//
//    public UsersServiceImpl(@Autowired UserDAO usersDao) {
//        this.usersDao = usersDao;
//    }
//
//    @Override
//    public List<User> findAll() {
//        return usersDao.findAllUsers();
//    }
//
//    @Transactional
//    @Override
//    public void add(User user) {
//        usersDao.addUser(user);
//    }
//
//    @Transactional
//    @Override
//    public void delete(User user) {
//        usersDao.deleteUser(user);
//    }
//
//    @Transactional
//    @Override
//    public void edit(User user) {
//        usersDao.editUser(user);
//
//    }
//
//    @Override
//    public User getById(int id) {
//        return usersDao.getUserById(id);
//    }
//
//}
