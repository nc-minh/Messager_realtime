package nguyenhuuvu.api;

import lombok.AllArgsConstructor;
import nguyenhuuvu.dto.UserDTO;
import nguyenhuuvu.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/searchs")
public class SearchController {
    final UserService userService;

    @GetMapping
    public ResponseEntity<?> search(@RequestParam String q, @RequestParam(required = false) Integer limit) {
        if (limit == null || limit < 0)
            limit = 10;
        Pageable pageable = PageRequest.of(0, limit);
        List<UserDTO> users = new ArrayList<>();
        userService.findUserByFullnameOrEmailLimit(q, pageable).stream().forEach((u) -> {
            users.add(UserDTO
                        .builder()
                        .withUsername(u.getUsername())
                        .withFullname(u.getFullname())
                        .withEmail("Not displayed for security reasons!")
                        .withGender(u.getGender())
                        .withAddress(u.getAddress())
                        .build());
        });
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
