package pe.com.access.control.AccesControl.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "user_role")
public class LoginRole {

    @Id
    private Integer idrole;
    private String namerole;
    private String status;
    private String application_id;
}
