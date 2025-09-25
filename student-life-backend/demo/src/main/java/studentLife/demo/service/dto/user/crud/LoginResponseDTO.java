package studentLife.demo.service.dto.user.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import studentLife.demo.service.dto.user.UserDTO;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDTO {
    private UserDTO userDTO;
    private String token;
}
