//package ru.kata.spring.boot_security.demo.models;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//@Entity
//@Table(name = "users")
//public class  User implements UserDetails {
//
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long userId;
//
//    @Column(name = "name")
//    @NotBlank(message = "Имя не может быть пустым")
//    @Size(min = 2, max = 15, message = "Имя должно быть между 2 и 15 символами в длину")
//    private String userName;
//
//    @Column(name = "age")
//    @Min(value = 1, message = "Возраст должен быть больше 1")
//    private int userAge;
//
//    @Column(name = "email")
//    @Email
//    private String userEmail;
//
//    @Column(name = "password")
//    private String password;
//
//
//    private Set<Role> roles
//
//
//
//    public User() {
//    }
//
//    public User(String userName, int userAge, String userEmail, String password) {
//        this.userName = userName;
//        this.userAge = userAge;
//        this.userEmail = userEmail;
//        this.password = password;
//    }
//
//    public User(int userId, String userName, int userAge, String userEmail, String password) {
//        this.userId = userId;
//        this.userName = userName;
//        this.userAge = userAge;
//        this.userEmail = userEmail;
//        this.password = password;
//    }
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public String getUserName() {
//        return userName;
//    } //
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public int getUserAge() {
//        return userAge;
//    }
//
//    public void setUserAge(int userAge) {
//        this.userAge = userAge;
//    }
//
//    public String getUserEmail() {
//        return userEmail;
//    }
//
//    public void setUserEmail(String userEmail) {
//        this.userEmail = userEmail;
//    }
//
//
//    public void password(String userSex) {
//        this.password = userSex;
//    }
//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("User{");
//        sb.append("userId=").append(userId);
//        sb.append(", userName='").append(userName).append('\'');
//        sb.append(", userAge=").append(userAge);
//        sb.append(", userEmail='").append(userEmail).append('\'');
//        sb.append(", userSex='").append(password).append('\'');
//        sb.append('}');
//        return sb.toString();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return getRoles();
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return userName;
//    }
//}