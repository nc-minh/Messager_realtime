package nguyenhuuvu.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Component
public class Mail {
    @Value("${spring.mail.username}")
    private String from;
    private String mailTo;
    private String subject;
    private List<Object> attachments;
    private Map<String, Object> props = new HashMap<>();
    private String templateName;
    @Value("${nguyenhuuvu.system.domain}")
    private String domain;
}
