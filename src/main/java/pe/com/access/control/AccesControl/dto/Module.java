package pe.com.access.control.AccesControl.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Module {

    private String key;
    private String[] permissions;
    private List<ChildRouteModule> childRoutes;
    private String title;
}
