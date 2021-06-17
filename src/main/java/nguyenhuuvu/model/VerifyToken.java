package nguyenhuuvu.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Document(collection = "verify")
public class VerifyToken {
    @Id
    private String id;
    private String token;
    private Timestamp timeExpire;
    @DBRef(lazy = false)
    private Account account;
}
