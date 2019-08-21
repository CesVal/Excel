package pe.com.access.control.AccesControl.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "role_has_user")
public class RoleHasUser implements Serializable {

    @Id
    private String user_id;

//    private Integer role_idrole;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id")
//    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_idrole")
    @Fetch(FetchMode.JOIN)
    private LoginRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "access_profile_idprofile")
    @Fetch(FetchMode.JOIN)
    private LoginAccessProfile loginAccessProfile;

    private String is_default;

}
