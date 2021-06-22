package nguyenhuuvu.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "verify", indexes = @Index(columnList = "token", unique = true))
public class VerifyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;
    private String token;
    private String code;
    private Date timeExpire;
    private boolean used;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public VerifyEntity(String token, String code, Date timeExpire, boolean used, UserEntity userEntity) {
        this.token = token;
        this.code = code;
        this.timeExpire = timeExpire;
        this.used = used;
        this.userEntity = userEntity;
    }
}
