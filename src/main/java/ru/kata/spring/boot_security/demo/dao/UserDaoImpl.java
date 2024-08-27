//package ru.kata.spring.boot_security.demo.dao;
//
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityNotFoundException;
//import jakarta.persistence.PersistenceContext;
//import org.springframework.stereotype.Repository;
//import ru.kata.spring.boot_security.demo.models.User;
//
//
//import java.util.List;
//
//@Repository
//public class UserDaoImpl implements UserDAO {
//
//    @PersistenceContext // проверить либо @Autowired внедряем то, что раньше называлось SessionFactory
//    private EntityManager entityManager;
//
//    @Override
//    public List<User> findAllUsers() { // разобраться с null
//        //List<User> allUsers = entityManager.createNativeQuery("SELECT * from Users").getResultList();
//        //List<User> allUsers = entityManager.createQuery("from User", User.class).getResultList();
//        //Query query = entityManager.createQuery("FROM User", User.class); //указываем класс, не название таблицы
//        //List<User> allUsers = query.getResultList();
////        TypedQuery<User> query1 = entityManager.createQuery("from User", User.class);
////        return query1.getResultList();
//        //System.out.println(allUsers.toString());
//        return entityManager.createQuery("from User", User.class).getResultList();
//    }
//
//    @Override
//    public void addUser(User user) {
//        entityManager.persist(user);
//    }
//
//    @Override
//    public void deleteUser(User user) {
//        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
//    }
//
//    @Override
//    public void editUser(User user) {
//        entityManager.merge(user);
//    }
//
//
//    @Override
//    public User getUserById(int id) {
//        User user = entityManager.find(User.class, id);
//        if (user == null) {
//            throw new EntityNotFoundException("User with id " + id + " not found.");
//        }
//        return user;
//    }
//}