package nguyenhuuvu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Verify {
    private String token;
    private String code;
    private Date timeExpire;
    private boolean used;
}
