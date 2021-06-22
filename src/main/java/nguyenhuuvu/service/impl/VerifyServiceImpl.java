package nguyenhuuvu.service.impl;

import lombok.AllArgsConstructor;
import nguyenhuuvu.entity.UserEntity;
import nguyenhuuvu.entity.VerifyEntity;
import nguyenhuuvu.exception.UserHandleException;
import nguyenhuuvu.repository.UserRepository;
import nguyenhuuvu.repository.VerifyRepository;
import nguyenhuuvu.service.VerifyService;
import nguyenhuuvu.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VerifyServiceImpl implements VerifyService {
    final UserRepository userRepository;
    final VerifyRepository verifyRepository;

    public boolean verifyToken(String token) {
        VerifyEntity verify = verifyRepository.findVerifyEntityByToken(token);
        if (verify != null) {
            if (!verify.isUsed() && DateTimeUtil.checkTokenExpire(verify.getTimeExpire())) {
                verify.setUsed(true);
                verify.getUserEntity().setEnabled(true);
                verifyRepository.save(verify);
                return true;
            }
        }
        return false;
    }

    public boolean verifyCode(String email, String code) {
        UserEntity user = userRepository.findUserEntityByEmail(email);
        if (user == null)
            throw new UserHandleException("Email is not linked to any accounts");
        if (user.getVerifyEntity().getCode().equals(code)) {
            if (!user.getVerifyEntity().isUsed()) {
                user.getVerifyEntity().setUsed(true);
                user.setEnabled(true);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

}