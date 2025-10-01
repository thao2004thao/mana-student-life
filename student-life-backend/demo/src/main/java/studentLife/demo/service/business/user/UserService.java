package studentLife.demo.service.business.user;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studentLife.demo.domain.user.UserEntity;
import studentLife.demo.repository.user.UserRepository;
import studentLife.demo.security.JwtUtil;
import studentLife.demo.service.ResponseDTO;
import studentLife.demo.service.base.BaseService;
import studentLife.demo.service.dto.user.UserDTO;
import studentLife.demo.service.dto.user.crud.LoginDTO;
import studentLife.demo.service.dto.user.crud.LoginResponseDTO;
import studentLife.demo.service.dto.user.crud.RegisterDTO;

import java.util.Optional;

@Service
public class UserService extends BaseService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseDTO<UserDTO> registerUser(RegisterDTO registerDTO) {
        Optional<UserEntity> checkUser = userRepository.findByUserName(registerDTO.getUserName());
        if(checkUser.isPresent()){
            throw new ServiceException("Tên người dùng đã tồn tại " + registerDTO.getUserName());
        }
        if(!registerDTO.getPassword().equals(registerDTO.getRePassword())){
            throw new ServiceException("Xác nhận mật khẩu không đúng");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(registerDTO.getId());
        userEntity.setUserName(registerDTO.getUserName());
        userEntity.setEmail(registerDTO.getEmail());
        userEntity.setUniversity(registerDTO.getUniversity());
        userEntity.setMajor(registerDTO.getMajor());
        userEntity.setYearOfStudy(registerDTO.getYearOfStudy());
        userEntity.setPassword(registerDTO.getPassword());
        userEntity.setRePassword(registerDTO.getRePassword());

        UserEntity userSaved = userRepository.save(userEntity);

        ResponseDTO<UserDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setStatus(String.valueOf(HttpStatus.OK));
        responseDTO.setData(UserDTO.toDTO(userSaved));

        return responseDTO;
    }

    @Transactional(readOnly = true)
    public ResponseDTO<LoginResponseDTO> loginUser(LoginDTO loginDTO) {
        UserEntity user = userRepository.findByUserName(loginDTO.getUserName())
                .orElseThrow(() -> new RuntimeException("Sai username hoặc password"));

        if (!user.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException("Sai username hoặc password");
        }

        String token = JwtUtil.generateRefreshToken(user.getUserName());

        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setUserDTO(UserDTO.toDTO(user));
        loginResponse.setToken(token);

        ResponseDTO<LoginResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setStatus(String.valueOf(HttpStatus.OK.value()));
        responseDTO.setData(loginResponse);

        return responseDTO;
    }
}





