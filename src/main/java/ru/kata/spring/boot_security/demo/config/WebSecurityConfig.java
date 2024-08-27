package ru.kata.spring.boot_security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

//    private final SuccessUserHandler successUserHandler;
//
//    public WebSecurityConfig(SuccessUserHandler successUserHandler) {
//        this.successUserHandler = successUserHandler;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/index").permitAll() // доступно всем
                        .requestMatchers("/admin/**").hasRole("admin") // доступ к /admin только для пользователей с ролью admin
                        .requestMatchers("/user").hasAnyRole("user", "admin") // доступ к /user для ролей user и admin
                        .anyRequest().authenticated() // все остальные запросы требуют аутентификации
                )
                .formLogin((form) -> form

                        //.loginPage("/login")
                        //.successHandler(successUserHandler) // обрабатываем успешный логин эту строку можно убрать
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());// .logout(LogoutConfigurer::permitAll

        return http.build();
    }
//@Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//
//        //authenticationProvider.setUserDetailsService();
//
//        return authenticationProvider;
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$12$jgkQ8uqr.hwnmBTRpej2LuOyMUGrFftyua6XJv6/xe/RVwjmGhcCu")
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$12$sWHEI.Ex0KsVDxtdG1mfmen3WSFEwS4KW5/ffDQsCNVNIkNPXLrM6")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }


//jdbcAutentification

//    @Bean
//    public JdbcUserDetailsManager users(DataSource dataSource) {
//        UserDetails user = User.builder() // оказывается обязательно шифровать надо!!!
//                .username("user")
//                .password("{bcrypt}$2a$12$jgkQ8uqr.hwnmBTRpej2LuOyMUGrFftyua6XJv6/xe/RVwjmGhcCu") // "{bcrypt}$2a$12$jgkQ8uqr.hwnmBTRpej2LuOyMUGrFftyua6XJv6/xe/RVwjmGhcCu" user
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$sWHEI.Ex0KsVDxtdG1mfmen3WSFEwS4KW5/ffDQsCNVNIkNPXLrM6")
//                .roles("ADMIN") // "USER",
//                .build();
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        if (jdbcUserDetailsManager.userExists(user.getUsername())) {
//            jdbcUserDetailsManager.deleteUser(user.getUsername());
//        }
//        if (jdbcUserDetailsManager.userExists(admin.getUsername())) {
//            jdbcUserDetailsManager.deleteUser(admin.getUsername());
//        }
//        jdbcUserDetailsManager.createUser(user);
//        jdbcUserDetailsManager.createUser(admin);
//        return jdbcUserDetailsManager;
//    }
}