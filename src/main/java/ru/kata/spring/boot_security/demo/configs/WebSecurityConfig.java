package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;

    public WebSecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index").permitAll() //доступно всем заходящим

 //Все CRUD-операции и страницы для них должны быть доступны только пользователю с ролью admin по url: /admin/
                .antMatchers("/admin/**").hasRole("admin")

                .antMatchers("/users").hasAnyRole("user", "admin") // !!! ПОТОМ ИСПРАВИТЬ НА user
                //.antMatchers().authenticated() РАЗОБРАТЬСЯ С authenticated
                .anyRequest().authenticated() //есть учетка на сайте
                .and()
                .formLogin().successHandler(successUserHandler)//это стандартная форма заполнения даных
                .permitAll()
                .and()
                .logout()// .logoutSuccessUrl("/") -- куда провалится после logout По умолчанию доступ к URL "/logout" приведет к выходу
                .permitAll();
    }

    // аутентификация inMemory
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("user")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}