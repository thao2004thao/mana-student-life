package studentLife.demo.web.rest.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import studentLife.demo.security.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;

import java.util.Map;

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
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        try {
            SecretKey key = Keys.hmacShaKeyFor("mySecretKeyThatIsLongEnoughForHS256Algorithm".getBytes());
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(refreshToken)
                    .getPayload();

            String username = claims.getSubject();

            // Nếu refresh token hết hạn
            if (claims.getExpiration().before(new Date())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token expired");
            }

            // Tạo access token mới
            String newAccessToken = JwtUtil.generateAccessToken(username);

            return ResponseEntity.ok(Map.of(
                    "accessToken", newAccessToken,
                    "refreshToken", refreshToken
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
    }

}
