package nguyenhuuvu.service.impl;

import lombok.AllArgsConstructor;
import nguyenhuuvu.entity.UserEntity;
import nguyenhuuvu.model.Mail;
import nguyenhuuvu.service.EmailSenderService;
import nguyenhuuvu.utils.Constant;
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

@Service
@AllArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    private final Mail mail;

    //mail.getDomain()
    public Mail createMailVerify(UserEntity user, String timeExpire) {
        mail.setMailTo(user.getEmail());
        mail.setSubject(Constant.VERIFY_ACCOUNT_SUBJECT);
        mail.setTemplateName(Constant.VERIFY_MAIL_TEMPLATE);
        Map<String, Object> props = new HashMap<>();
        props.put("link", "https://apidevchat.herokuapp.com" + "/api/v1/accounts/verification?token=" + user.getVerifyEntity().getToken());
        props.put("code", user.getVerifyEntity().getCode());
        props.put("expire", timeExpire);
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
