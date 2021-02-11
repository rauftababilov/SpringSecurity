package web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// Этот класс реализует интерфейс GrantedAuthority, в котором необходимо переопределить только один метод getAuthority() (возвращает имя роли).
// Имя роли должно соответствовать шаблону: «ROLE_ИМЯ», например, ROLE_USER.

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    private Long id;

    private String role;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }

    public Role(String role) {
        if (role.equals("ADMIN")) {
            this.id = 1L;
        } else if (role.equals("USER")) {
            this.id = 2L;
        }
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
