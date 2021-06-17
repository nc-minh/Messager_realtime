package nguyenhuuvu.repository;

import nguyenhuuvu.model.VerifyToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VerifyTokenRepository extends MongoRepository<VerifyToken, String> {
    VerifyToken findVerifyTokenByToken(String token);
}
