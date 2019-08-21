package pe.com.access.control.AccesControl.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {

    private String userId;
    private String identificationDocument;
    private String email;
    private String firstName;
    private String lastName;
    private String defaultRole;
    private List<Role> roles;
    private RolDetail rolDetail;

}
