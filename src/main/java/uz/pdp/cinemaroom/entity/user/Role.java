package uz.pdp.cinemaroom.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.cinemaroom.entity.absEntity.AbsEntity;
import uz.pdp.cinemaroom.entity.enums.RoleEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "roles")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Role extends AbsEntity {

    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    @Column(unique = true)
    private String name;
    @ManyToMany
    @JoinTable(name = "roles_permissions",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    private Collection<Permission> permissions = new ArrayList<>();

    public Role(String name) {
        this.name=name;
    }
}
