package ru.kata.spring.boot_security.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name = "roles")
@ToString
public class Role implements GrantedAuthority { // GrantedAuthority стандартизированный интерфейс

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
