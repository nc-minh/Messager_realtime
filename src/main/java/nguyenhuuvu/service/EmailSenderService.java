package nguyenhuuvu.service;

import nguyenhuuvu.entity.UserEntity;
import nguyenhuuvu.model.Mail;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailSenderService {
    Mail createMailVerify(UserEntity user, String timeExpire);
    void sendEmail(Mail mail) throws MessagingException, IOException;
}
