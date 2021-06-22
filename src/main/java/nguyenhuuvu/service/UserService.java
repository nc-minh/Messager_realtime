package nguyenhuuvu.service;

import nguyenhuuvu.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity signUpUser(UserEntity user);

    UserEntity findUserByEmail(String email);

    List<UserEntity> findAll();
}
