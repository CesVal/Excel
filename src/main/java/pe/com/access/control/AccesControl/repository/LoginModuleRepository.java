package pe.com.access.control.AccesControl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.access.control.AccesControl.entity.LoginModule;

import java.util.List;

public interface LoginModuleRepository extends JpaRepository<LoginModule, String> {

    @Query(value = "SELECT m.* FROM module m inner join access_profile_has_module apm\n" +
            "on m.idmodule=apm.module_idmodule where apm.access_profile_idprofile =:idProfile", nativeQuery = true)
    List<LoginModule> profileModules(@Param("id") String idProfile);
}
