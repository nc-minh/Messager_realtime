package nguyenhuuvu.model;

import lombok.Data;
import nguyenhuuvu.enums.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
@Document(collection = "account")
public class Account {
    @Id
    private String id;

    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid format")
    @Indexed(unique = true)
    private String email;

    @NotBlank(message = "Firstname is mandatory")
    private String firstname;

    @NotBlank(message = "Lastname is mandatory")
    private String lastname;

    @NotNull(message = "Gender is not null")
    private Gender gender;

    @NotNull(message = "Birthday is not null")
    private Date birthday;

    private String address;

    private Set<String> listUsernameFriends;

    private boolean enabled = false;

    @DBRef(lazy = false)
    private VerifyToken verifyToken;
}