package pe.com.access.control.AccesControl.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Menu {

    private String key;
    private String path;
    private String name;
    private List<Menu> submenu;
}
