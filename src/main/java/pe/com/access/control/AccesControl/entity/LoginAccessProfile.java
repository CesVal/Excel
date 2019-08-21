package pe.com.access.control.AccesControl.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "access_profile")
public class LoginAccessProfile {

    @Id
    private String idprofile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_idrole")
    @Fetch(FetchMode.JOIN)
    private LoginRole loginRole;

    private String nameprofile;
    private String status;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idprofile")
    @Fetch(FetchMode.JOIN)
    private List<LoginPermissionModuleProfile> loginPermissionModuleProfiles;
}
