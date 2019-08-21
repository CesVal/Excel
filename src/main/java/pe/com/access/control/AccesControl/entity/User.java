package pe.com.access.control.AccesControl.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    private String id;
    private String dni;
    private String email;
    private String nombre;
    private String apellido;
    private String foto;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Fetch(FetchMode.JOIN)
    private List<RoleHasUser> roleHasUserList;
}
