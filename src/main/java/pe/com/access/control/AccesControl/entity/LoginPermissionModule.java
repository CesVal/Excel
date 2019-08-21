package pe.com.access.control.AccesControl.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "permition_module")
public class LoginPermissionModule {

    @Id
    private String idpermition;
    private String idmodule;
    private String namepermition;
    private String status;
}
