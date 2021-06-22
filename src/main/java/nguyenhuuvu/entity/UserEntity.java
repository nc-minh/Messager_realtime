package nguyenhuuvu.entity;

import lombok.Data;
import nguyenhuuvu.enums.Gender;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user", indexes = {
        @Index(columnList = "username", unique = true),
        @Index(columnList = "email", unique = true)
})
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @NotBlank(message = "Password is mandatory")
    @Length(min = 5)
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid format")
    private String email;

    @NotBlank(message = "Fullname is mandatory")
    @Length(min = 5, max = 30)
    private String fullname;

    private Gender gender;

    private Date birthday;

    private String address;
    private boolean enabled = false;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "verify_id")
    private VerifyEntity verifyEntity;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<JoinEntity> joins;
}
