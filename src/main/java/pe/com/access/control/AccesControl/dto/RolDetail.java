package pe.com.access.control.AccesControl.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RolDetail {

    private List<Menu> menu;
    private Map<String, Module> modules;
}
