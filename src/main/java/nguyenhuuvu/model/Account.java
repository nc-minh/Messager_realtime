package nguyenhuuvu.model;

import lombok.Data;
import nguyenhuuvu.enums.Gender;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Data
@Document(collection = "account")
public class Account {
    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Length(min = 5)
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid format")
    @Indexed(unique = true)
    private String email;

    @NotBlank(message = "Fullname is mandatory")
    @Length(min = 5, max = 30)
    private String fullname;

    private Gender gender;

    private Date birthday;

    private String address;

    private Set<String> listUsernameFriends;

    private boolean enabled = false;

    private Verify verify;
}