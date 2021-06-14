package nguyenhuuvu.core.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
    @RequestMapping
    public String test()
    {
        return "oke";
    }
}
