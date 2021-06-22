package nguyenhuuvu.repository;

import nguyenhuuvu.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findUserEntityByUsernameOrEmail(String username, String password);
    UserEntity findUserEntityByUsername(String username);
    UserEntity findUserEntityByEmail(String email);

    @Query("SELECT e FROM UserEntity e where e.fullname like %:name% or e.email=:email")
    List<UserEntity> searchUsersLikeFullNameOrEqualEmail(@Param("name") String fullname, @Param("email") String email,
                                                         Pageable pageable);
}
