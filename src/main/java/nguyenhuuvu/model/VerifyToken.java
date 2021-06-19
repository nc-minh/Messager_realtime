package nguyenhuuvu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyToken {
    private String token;
    private int code;
    private Timestamp timeExpire;
    private boolean isUsed;
}
