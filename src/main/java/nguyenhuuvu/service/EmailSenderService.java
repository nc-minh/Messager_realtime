package nguyenhuuvu.service;

import lombok.AllArgsConstructor;
import nguyenhuuvu.model.Account;
import nguyenhuuvu.model.Mail;
import nguyenhuuvu.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@AllArgsConstructor
public class EmailSenderService {
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    private final Mail mail;

    public Mail createMailVerify(Account account) {
        mail.setMailTo(account.getEmail());
        mail.setSubject(Constant.VERIFY_ACCOUNT_SUBJECT);
        mail.setTemplateName(Constant.VERIFY_MAIL_TEMPLATE);
        Map<String, Object> props = new HashMap<>();
        props.put("link", mail.getDomain() + "/api/v1/account/verify?token=" + account.getVerifyToken().getToken());
        props.put("code", account.getVerifyToken().getCode());
        mail.setProps(props);
        return mail;
    }

    public void sendEmail(Mail mail) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(mail.getProps());

        String html = templateEngine.process(mail.getTemplateName(), context);

        helper.setTo(mail.getMailTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        new Thread(() -> {
            emailSender.send(message);
        }).start();
    }
}
