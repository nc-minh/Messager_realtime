package nguyenhuuvu.model;

import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyToken {
    private String token;
    private int code;
    private Date timeExpire;
    private boolean isUsed;
}
