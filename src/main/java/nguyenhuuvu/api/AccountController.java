package nguyenhuuvu.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import nguyenhuuvu.dto.AccountDTO;
import nguyenhuuvu.model.Account;
import nguyenhuuvu.model.Mail;
import nguyenhuuvu.service.AccountService;
import nguyenhuuvu.service.EmailSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;

import static nguyenhuuvu.utils.Constant.VERIFY_ACCOUNT_TIME_EXPIRE;


@RestController
@RequestMapping(path = {"/api/v1/accounts"})
@CrossOrigin("*")
@AllArgsConstructor
public class AccountController {
    final AccountService accountService;
    final EmailSenderService emailSenderService;

    @Operation(description = "Tạo tài khoản mới", parameters = {
            @Parameter(name = "fullname", description = "Bắt buộc"),
            @Parameter(name = "email", description = "Bắt buộc"),
            @Parameter(name = "password", description = "Bắt buộc")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tạo tài khoản thành công"),
            @ApiResponse(responseCode = "400", description = "Email này đã liên kết với tài khoản khác")
    })
    @PostMapping
    public ResponseEntity<?> signInAccount(@Valid @RequestBody Account account) throws MessagingException, IOException {
        // save account
        account = accountService.signUpAccount(account);

        // send mail verify
        Mail mail = emailSenderService.createMailVerify(account, VERIFY_ACCOUNT_TIME_EXPIRE);
        emailSenderService.sendEmail(mail);

        return new ResponseEntity<>(
                AccountDTO
                        .builder()
                        .withUsername(account.getUsername())
                        .withEmail(account.getEmail())
                        .withFullname(account.getFullname())
                        .withGender(account.getGender())
                        .withBirthday(account.getBirthday())
                        .withAddress(account.getAddress())
                        .build(),
                HttpStatus.OK);
    }


}
