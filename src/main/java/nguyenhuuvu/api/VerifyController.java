package nguyenhuuvu.api;

import lombok.AllArgsConstructor;
import nguyenhuuvu.dto.AccountDTO;
import nguyenhuuvu.exception.AccountNotFoundException;
import nguyenhuuvu.model.Account;
import nguyenhuuvu.model.Mail;
import nguyenhuuvu.model.SimpleResponse;
import nguyenhuuvu.service.AccountService;
import nguyenhuuvu.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = {"/api/v1/accounts/verification"})
public class VerifyController {

    @Value("${nguyenhuuvu.system.domain}")
    String domain;

    final EmailSenderService emailSenderService;
    final AccountService accountService;

    public VerifyController(EmailSenderService emailSenderService, AccountService accountService) {
        this.emailSenderService = emailSenderService;
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<?> resendCode(@RequestBody Map<String, String> req) throws MessagingException, IOException {
        Account account = accountService.findAccountByUsernameOrEmail(req.get("key"));
        if (account != null) {
            Mail mail = emailSenderService.createMailVerify(account);
            emailSenderService.sendEmail(mail);
            return new ResponseEntity<>(new SimpleResponse(200, "Đã gửi lại email xác thực"), HttpStatus.OK);
        } else
            throw new AccountNotFoundException();
    }

    @GetMapping
    public ResponseEntity<?> verifyToLink(@RequestParam("token") String token) {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(domain + "/signin?msg=success")).build();
    }

}
