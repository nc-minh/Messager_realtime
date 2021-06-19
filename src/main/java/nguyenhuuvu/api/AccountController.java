package nguyenhuuvu.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import nguyenhuuvu.dto.AccountDTO;
import nguyenhuuvu.model.Account;
import nguyenhuuvu.model.Mail;
import nguyenhuuvu.service.AccountService;
import nguyenhuuvu.service.EmailSenderService;
import nguyenhuuvu.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(path = {"/api/v1/account"})
@CrossOrigin("*")
public class AccountController {

    @Value("${nguyenhuuvu.system.domain}")
    String domain;

    final AccountService accountService;
    final EmailSenderService emailSenderService;

    @Autowired
    public AccountController(AccountService accountService, EmailSenderService emailSenderService) {
        this.accountService = accountService;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping(path = {"/signup"})
    public ResponseEntity<?> signInAccount(@Valid @RequestBody Account account) throws MessagingException, IOException {
        // save account
        account = accountService.signUpAccount(account);

        // send mail verify
        Mail mail = emailSenderService.createMailVerify(account);
        emailSenderService.sendEmail(mail);

        return new ResponseEntity<>(
                AccountDTO
                        .builder()
                        .withUsername(account.getUsername())
                        .withEmail(account.getEmail())
                        .withFirstname(account.getFirstname())
                        .withLastname(account.getLastname())
                        .withGender(account.getGender())
                        .withBirthday(account.getBirthday())
                        .withAddress(account.getAddress())
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping ("/verify")
    public ResponseEntity<?> verifyToLink(@RequestParam("token") String token)
    {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(domain + "/signin?msg=success")).build();
    }
}
