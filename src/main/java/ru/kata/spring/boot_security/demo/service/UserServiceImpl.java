package ru.kata.spring.boot_security.demo.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.сustomExeptions.UsernameAlreadyExistsException;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void create(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser != null && existingUser.isEnabled()) {
            throw new UsernameAlreadyExistsException("Пользователь с таким именем уже существует");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        Optional<User> existingUserOptional = userRepository.findById(user.getUserId());

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get(); //получили из БД юзера без обновленных данных и удостоверились что но существует

            User userWithDuplicateUsername = userRepository.findByUsername(user.getUsername()); //проверка существует ли в БД юзер с таким же именем
            if (userWithDuplicateUsername != null && !userWithDuplicateUsername.getUserId().equals(existingUser.getUserId())) {
                throw new UsernameAlreadyExistsException("Пользователь с таким именем уже существует");
            }

            // Проверка, изменялся ли пароль
            if (!existingUser.getPassword().equals(user.getPassword())) {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            } else {
                user.setPassword(existingUser.getPassword()); // сохранить старый пароль
            }

            userRepository.saveAndFlush(user);
        } else {
            throw new EntityNotFoundException("User for updating not found.");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("User not found for deletion.");
        }
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found by ID."));
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean isUserExist(User user) {
        return userRepository.findByUsername(user.getUsername()) != null;
    }
}
