package pe.com.access.control.AccesControl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.access.control.AccesControl.entity.LoginAccessProfile;

import java.util.List;

public interface LoginAccessProfileRepository extends JpaRepository<LoginAccessProfile, String> {

    @Query(value = "SELECT ap.* FROM login.access_profile ap inner join login.role_has_user ru\n" +
            "on ap.idprofile = ru.access_profile_idprofile where ru.user_id=:id", nativeQuery = true)
    List<LoginAccessProfile> userAccessProfile(@Param("id") String id);
}
