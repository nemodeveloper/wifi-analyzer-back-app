package ru.nemodev.wifi.analyzer.security.entity.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nemodev.wifi.analyzer.security.entity.privilege.Privilege;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ROLES")
public class Role
{
    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    private String id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private List<Privilege> privileges;
}
