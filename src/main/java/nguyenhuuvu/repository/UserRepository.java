package nguyenhuuvu.repository;

import nguyenhuuvu.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findUserEntityByUsernameOrEmail(String username, String password);
    UserEntity findUserEntityByUsername(String username);
    UserEntity findUserEntityByEmail(String email);
}
