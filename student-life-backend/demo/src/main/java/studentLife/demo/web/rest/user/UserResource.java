package studentLife.demo.web.rest.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studentLife.demo.service.ResponseDTO;
import studentLife.demo.service.business.user.UserService;
import studentLife.demo.service.dto.user.UserDTO;
import studentLife.demo.service.dto.user.crud.LoginDTO;
import studentLife.demo.service.dto.user.crud.LoginResponseDTO;
import studentLife.demo.service.dto.user.crud.RegisterDTO;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseDTO<UserDTO> regiterUser(@RequestBody RegisterDTO registerDTO) {
            return userService.registerUser(registerDTO);
    }

    @PostMapping("/login")
    public ResponseDTO<LoginResponseDTO> loginUser(@RequestBody LoginDTO loginDTO){

        return userService.loginUser(loginDTO);
    }
}
