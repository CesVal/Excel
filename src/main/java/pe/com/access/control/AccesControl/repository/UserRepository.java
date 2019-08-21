package pe.com.access.control.AccesControl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.access.control.AccesControl.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT u.* FROM user u inner join role_has_user ru on u.id=ru.user_id where u.id =:id", nativeQuery = true)
    User userById(@Param("id") String id);
}
