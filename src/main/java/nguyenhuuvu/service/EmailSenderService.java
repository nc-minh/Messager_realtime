package nguyenhuuvu.service;

import nguyenhuuvu.model.Account;
import nguyenhuuvu.model.Mail;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailSenderService {
    Mail createMailVerify(Account account, String timeExpire);
    void sendEmail(Mail mail) throws MessagingException, IOException;
}
