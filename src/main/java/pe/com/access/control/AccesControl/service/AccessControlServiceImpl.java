package pe.com.access.control.AccesControl.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import pe.com.access.control.AccesControl.dto.*;
import pe.com.access.control.AccesControl.dto.Module;
import pe.com.access.control.AccesControl.entity.LoginModule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccessControlServiceImpl implements AccessControlService {


    @Override
    public UserResponse accessControlUser() {

        List<Role> roles = getListRoles();
        List<LoginModule> loginModules = getListModules();

        UserResponse userResponse = new UserResponse();
        userResponse.setUserId("1");
        userResponse.setIdentificationDocument("74838761");
        userResponse.setEmail("ceval1004@gmail.com");
        userResponse.setFirstName("Cesar");
        userResponse.setLastName("Valverde");

        userResponse.setDefaultRole(roles.stream().filter(role -> role.getIsDefault().equals("1")).findFirst()
                .orElse(new Role()).getId());
        userResponse.setRoles(roles.stream().map(role ->
                pe.com.access.control.AccesControl.dto.Role.builder()
                        .id(role.getId())
                        .name(role.getName())
                        .description(role.getDescription())
                        .build()).collect(Collectors.toList()));
        RolDetail rolDetail = new RolDetail();
        rolDetail.setMenu(loginModules.stream().filter(loginModule -> loginModule.getIsOptionMenu().equals("1") && loginModule.getIdModuleParent() == null)
                .map(loginModule -> buildMenuStructureByModuleParent(loginModules, loginModule))
                .collect(Collectors.toList()));

        List<LoginModule> menusWithoutChildren = loginModules.stream().filter(loginModule -> loginModule.getIsOptionMenu().equals("1"))
                .filter(menu -> loginModules.stream()
                        .filter(loginModule -> loginModule.getIsOptionMenu().equals("1"))
                        .noneMatch(treeCompare -> menu.getIdModule().equals(treeCompare.getIdModuleParent())))
                .collect(Collectors.toList());

        List<Module> listParentModule = menusWithoutChildren.stream().map(loginModule -> {

            Module module = new Module();
            module.setKey(loginModule.getCodeModule());
            module.setPermissions(null);
            module.setChildRoutes(loginModules.stream().filter(loginModule1 -> loginModule.getIdModule().equals(loginModule1.getIdModuleParent()))
                    .map(loginModule1 -> {
                        ChildRouteModule childRouteModule = new ChildRouteModule();
                        childRouteModule.setName(loginModule1.getNameModule());
                        childRouteModule.setPath(loginModule1.getPath());
                        childRouteModule.setKey(loginModule1.getCodeModule());
                        return childRouteModule;
                    }).collect(Collectors.toList()));
            module.setTitle(loginModule.getTittle());

            return module;

        }).collect(Collectors.toList());

        List<Module> listChildModule = loginModules.stream()
                .filter(loginModule -> menusWithoutChildren.stream()
                        .anyMatch(loginModule1 -> loginModule1.getIdModule().equals(loginModule.getIdModuleParent())))
                .peek(debug -> System.out.println("CHILD MODULE: " + debug.getIdModule()))
                .map(loginModule -> {

                    Module module = new Module();
                    module.setKey(loginModule.getCodeModule());
                    module.setPermissions(getListPermission().stream()
                            .filter(permission -> loginModule.getIdModule().equals(permission.getIdModule()))
                            .map(Permission::getNamePermission)
                            .toArray(String[]::new));
                    module.setChildRoutes(null);
                    module.setTitle(loginModule.getTittle());

                    return module;
                })
                .collect(Collectors.toList());


        listParentModule.addAll(listChildModule);

        rolDetail.setModules(listParentModule.stream().collect(Collectors.toMap(Module::getKey, module -> module)));
        userResponse.setRolDetail(rolDetail);

        return userResponse;
    }


    private Menu buildMenuStructureByModuleParent(List<LoginModule> loginModules, LoginModule moduleEntityParent) {
        Menu menu = new Menu();
        menu.setKey(moduleEntityParent.getCodeModule());
        menu.setPath(moduleEntityParent.getPath());
        menu.setName(moduleEntityParent.getNameModule());
        menu.setSubmenu(loginModules.stream().
                filter(loginModule -> loginModule.getIsOptionMenu().equals("1")
                        && loginModule.getIdModuleParent() != null
                        && loginModule.getIdModuleParent().equals(moduleEntityParent.getIdModule()))
                .map(loginModule -> buildMenuStructureByModuleParent(loginModules, loginModule))
                .collect(Collectors.toList()));
        return menu;
    }

    private List<LoginModule> getListModules() {

        List<LoginModule> loginModules = new ArrayList<>();

        LoginModule loginModule1 = new LoginModule();
        LoginModule loginModule2 = new LoginModule();
        LoginModule loginModule3 = new LoginModule();
        LoginModule loginModule4 = new LoginModule();

        loginModule1.setIdModule("1");
        loginModule1.setIdModuleParent(null);
        loginModule1.setNameModule("Reportes");
        loginModule1.setCodeModule("report");
        loginModule1.setPath("/reports");
        loginModule1.setTittle(null);
        loginModule1.setStatus("1");
        loginModule1.setIsOptionMenu("1");

        loginModule2.setIdModule("2");
        loginModule2.setIdModuleParent("1");
        loginModule2.setNameModule("Órdenes");
        loginModule2.setCodeModule("orders");
        loginModule2.setPath("/reports/orders");
        loginModule2.setTittle("Reporte de Órdenes");
        loginModule2.setStatus("1");
        loginModule2.setIsOptionMenu("1");

        loginModule3.setIdModule("3");
        loginModule3.setIdModuleParent("2");
        loginModule3.setNameModule("Farmacias");
        loginModule3.setCodeModule("drugstores");
        loginModule3.setPath("/reports/ordenes/drugstores");
        loginModule3.setTittle("Reporte de Órdenes de farmacia");
        loginModule3.setStatus("1");
        loginModule3.setIsOptionMenu("0");

        loginModule4.setIdModule("4");
        loginModule4.setIdModuleParent("2");
        loginModule4.setNameModule("DC");
        loginModule4.setCodeModule("dc");
        loginModule4.setPath("/reports/ordenes/dc");
        loginModule4.setTittle("Reporte de Órdenes de DC");
        loginModule4.setStatus("1");
        loginModule4.setIsOptionMenu("0");

        loginModules.add(loginModule1);
        loginModules.add(loginModule2);
        loginModules.add(loginModule3);
        loginModules.add(loginModule4);

        return loginModules;
    }

    private List<Role> getListRoles() {

        Role role1 = new Role();
        Role role2 = new Role();

        role1.setId("1");
        role1.setName("ADMINISTRATOR");
        role1.setDescription("administrador");
        role1.setIsDefault("1");

        role2.setId("2");
        role2.setName("OPERATOR");
        role2.setDescription("operador");
        role2.setIsDefault("2");

        List<Role> roles = new ArrayList<>();
        roles.add(role1);
        roles.add(role2);

        return roles;
    }

    private List<Permission> getListPermission() {

        List<Permission> permissions = new ArrayList<>();

        Permission permission1 = new Permission();
        permission1.setIdPermission("1");
        permission1.setNamePermission("CAN_ACTIVE");
        permission1.setIdModule("3");
        permission1.setStatus("1");

        Permission permission2 = new Permission();
        permission2.setIdPermission("3");
        permission2.setNamePermission("CAN_EXPORT");
        permission2.setIdModule("4");
        permission2.setStatus("1");

        permissions.add(permission1);
        permissions.add(permission2);

        return permissions;
    }

}


@Getter
@Setter
class Role {

    private String id;
    private String name;
    private String description;
    private String isDefault;
}

@Getter
@Setter
class Permission {

    private String idPermission;
    private String namePermission;
    private String idModule;
    private String status;
}