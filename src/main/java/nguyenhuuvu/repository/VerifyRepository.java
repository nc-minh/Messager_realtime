package nguyenhuuvu.repository;

import nguyenhuuvu.entity.VerifyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifyRepository extends JpaRepository<VerifyEntity, Integer> {
    VerifyEntity findVerifyEntityByToken(String token);
}
