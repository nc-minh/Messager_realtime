package nguyenhuuvu.api;

import nguyenhuuvu.exception.AccountNotFoundException;
import nguyenhuuvu.model.Account;
import nguyenhuuvu.model.Mail;
import nguyenhuuvu.model.SimpleResponse;
import nguyenhuuvu.service.AccountService;
import nguyenhuuvu.service.EmailSenderService;
import nguyenhuuvu.service.VerifyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

import static nguyenhuuvu.utils.Constant.VERIFY_ACCOUNT_TIME_EXPIRE;

@RestController
@RequestMapping(path = {"/api/v1/accounts/verification"})
public class VerifyController {

    @Value("${nguyenhuuvu.system.domain}")
    String domain;

    final EmailSenderService emailSenderService;
    final AccountService accountService;
    final VerifyService verifyService;

    public VerifyController(EmailSenderService emailSenderService, AccountService accountService, VerifyService verifyService) {
        this.emailSenderService = emailSenderService;
        this.accountService = accountService;
        this.verifyService = verifyService;
    }

    // fix sau - loi logic
    // chua check token het han chua
    @PostMapping("?action=resend-code")
    public ResponseEntity<?> resendCode(@RequestBody Map<String, String> body) throws MessagingException, IOException {
        Account account = accountService.findAccountByEmail(body.get("email"));
        if (account != null) {
            Mail mail = emailSenderService.createMailVerify(account, VERIFY_ACCOUNT_TIME_EXPIRE);
            emailSenderService.sendEmail(mail);
            return new ResponseEntity<>(new SimpleResponse(200, "Đã gửi lại email xác thực"), HttpStatus.OK);
        } else
            throw new AccountNotFoundException("This token does not exist");
    }

    @GetMapping
    public ResponseEntity<?> verifyToLink(@RequestParam("token") String token) {
        boolean isOke = verifyService.verifyToken(token);
        if (isOke)
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/signin?msg=verify-success")).build();
        else
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/url-invalid?msg=expire")).build();
    }

    @PostMapping
    public ResponseEntity<?> verifyToCode(@RequestBody Map<String, String> info) {
        String code = info.get("code");
        String email = info.get("email");
        boolean isOke = verifyService.verifyCode(email, code);
        if (isOke)
            return new ResponseEntity<>(new SimpleResponse(200, "Account confirmation successful!"), HttpStatus.OK);
        else
            return new ResponseEntity<>(new SimpleResponse(400, "The verification code is invalid or has expired!"), HttpStatus.NOT_ACCEPTABLE);
    }

}
