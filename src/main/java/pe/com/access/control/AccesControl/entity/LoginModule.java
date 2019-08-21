package pe.com.access.control.AccesControl.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class LoginModule {

    @Id
    private String idModule;
    private String idModuleParent;
    private String nameModule;
    private String codeModule;
    private String path;
    private String tittle;
    private String status;
    private String isOptionMenu;
}
