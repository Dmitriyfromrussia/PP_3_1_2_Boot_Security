package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;

import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService { // его задача по имени пользователя предоставить самого Юзера

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // можно посмотреть в документации UserDetails и вспомнить какие данные ему надо
        User foundUser = findByUsername(username);
        if (foundUser == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username)); // на всякий форматируем сообщение о том, что Бзер с таким-то именем не найден
        }
        return new org.springframework.security.core.userdetails.User(foundUser.getUsername(), foundUser.getPassword(),
                mapRolesToAuthorities(foundUser.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) { //этот метод для кастинга списка ролей в коллекцию прав доступа, тк String ее ждет
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }
}
//            @Override
//            public Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
//                return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
//            }
//
//            @Override
//            public String getPassword() {
//                return user.getPassword();
//            }
//
//            @Override
//            public String getUsername() {
//                return user.getUserName();
//            }
//        };
