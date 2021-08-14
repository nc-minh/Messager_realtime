package nguyenhuuvu.api;

import lombok.AllArgsConstructor;
import nguyenhuuvu.entity.UserEntity;
import nguyenhuuvu.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestControler {
    final UserService userService;

    @RequestMapping("/")
    public UserEntity fetchAccount()
    {
        return userService.findUserByEmail("s2huuvuno1@gmail.com");
    }
}
