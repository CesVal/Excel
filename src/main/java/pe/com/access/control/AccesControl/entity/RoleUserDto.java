package pe.com.access.control.AccesControl.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoleUserDto {

    private String idrole;
    private String namerole;
    private String is_default;

}
