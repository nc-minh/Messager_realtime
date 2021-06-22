package nguyenhuuvu.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import nguyenhuuvu.dto.UserDTO;
import nguyenhuuvu.entity.UserEntity;
import nguyenhuuvu.model.Mail;
import nguyenhuuvu.service.EmailSenderService;
import nguyenhuuvu.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static nguyenhuuvu.utils.Constant.VERIFY_ACCOUNT_TIME_EXPIRE;


@RestController
@RequestMapping(path = {"/api/v1/accounts"})
@CrossOrigin("*")
@AllArgsConstructor
public class AccountController {
    final UserService userService;
    final EmailSenderService emailSenderService;

    @GetMapping
    public ResponseEntity<?> fetchAllUsers() {
        List<UserEntity> accounts = userService.findAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

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
    public ResponseEntity<?> signUpUser(@Valid @RequestBody UserEntity user) throws MessagingException, IOException {
        // save account
        user = userService.signUpUser(user);

        // send mail verify
        Mail mail = emailSenderService.createMailVerify(user, VERIFY_ACCOUNT_TIME_EXPIRE);
        emailSenderService.sendEmail(mail);

        return new ResponseEntity<>(
                UserDTO
                        .builder()
                        .withUsername(user.getUsername())
                        .withEmail(user.getEmail())
                        .withFullname(user.getFullname())
                        .build(),
                HttpStatus.OK);
    }


}
