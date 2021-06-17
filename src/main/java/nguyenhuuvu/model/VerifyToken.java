package nguyenhuuvu.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "verify")
public class VerifyToken {
    private String token;
    private Date timeGeneric = new Date();
    @DBRef(lazy = false)
    private Account account;
}
