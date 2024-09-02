package ru.kata.spring.boot_security.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Getter // убрать
@Setter // убрать
public class User implements UserDetails { // UserDetails стандартизированный интерфейс

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    //имя юзера олжно быть уникально (аннотация @Uniq или первичный ключ)
    @Column(name = "name")
    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 2, max = 15, message = "Имя должно быть между 2 и 15 символами в длину")
    private String username;

    @Column(name = "age")
    @Min(value = 18, message = "Возраст должен быть больше 18")
    private int userAge;

    @Column(name = "email")
    @Email(message = "введите корректный email")
    private String userEmail;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
            //cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;


    public User() {
    }

    public User(Long userId, String username, int userAge, String userEmail, String password, Set<Role> roles) {
        this.userId = userId;
        this.username = username;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.password = password;
        this.roles = roles;
    }

    public User(String username, int userAge, String userEmail, String password, Set<Role> roles) {
        this.username = username;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("password='").append(password).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", username='").append(username).append('\'');
        sb.append(", userAge=").append(userAge);
        sb.append(", userEmail='").append(userEmail).append('\'');
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}