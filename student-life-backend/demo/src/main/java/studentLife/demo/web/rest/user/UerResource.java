package studentLife.demo.web.rest.user;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studentLife.demo.service.ResponseDTO;
import studentLife.demo.service.business.user.UserService;
import studentLife.demo.service.dto.user.UserDTO;
import studentLife.demo.service.dto.user.crud.RegisterDTO;

@RestController
@RequestMapping("/api/user")
public class UerResource {

    private final UserService userService;

    public UerResource(UserService userService) {
        this.userService = userService;
    }

    public ResponseDTO<UserDTO> regiterUser(@RequestBody RegisterDTO registerDTO) {
            return userService.registerUser(registerDTO);
    }
}
