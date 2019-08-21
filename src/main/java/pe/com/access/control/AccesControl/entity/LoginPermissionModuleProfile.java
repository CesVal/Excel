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
@Table(name = "permition_module_profile")
public class LoginPermissionModuleProfile implements Serializable {


    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idprofile")
    @Fetch(FetchMode.JOIN)
    private LoginAccessProfile loginAccessProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpermition")
    @Fetch(FetchMode.JOIN)
    private LoginPermissionModule loginPermissionModule;

    private String status;

}
