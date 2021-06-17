package nguyenhuuvu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyException {
    private String errorCode;
    private String message;
    private String status;
}
