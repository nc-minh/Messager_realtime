package nguyenhuuvu.service;

import nguyenhuuvu.entity.UserEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserEntity signUpUser(UserEntity user);

    UserEntity findUserByEmail(String email);

    List<UserEntity> findAll();

    List<UserEntity> findUserByFullnameOrEmailLimit(String q, Pageable pageable);
}
