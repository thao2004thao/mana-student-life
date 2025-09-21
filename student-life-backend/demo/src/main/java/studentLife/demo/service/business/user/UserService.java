package studentLife.demo.service.business.user;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import studentLife.demo.domain.user.UserEntity;
import studentLife.demo.repository.user.UserRepository;
import studentLife.demo.service.ResponseDTO;
import studentLife.demo.service.base.BaseService;
import studentLife.demo.service.dto.user.UserDTO;
import studentLife.demo.service.dto.user.crud.LoginDTO;
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
        userEntity.setPassword(registerDTO.getPassword());
        userEntity.setRePassword(registerDTO.getRePassword());

        UserEntity userSaved = userRepository.save(userEntity);

        ResponseDTO<UserDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setStatus(String.valueOf(HttpStatus.OK));
        responseDTO.setData(UserDTO.toDTO(userSaved));

        return responseDTO;
    }

    public ResponseDTO<UserDTO> loginUser(LoginDTO loginDTO){
            Optional<UserEntity> userOpt = userRepository.findByUserName(loginDTO.getUsername());
            if(userOpt.isEmpty()){
                throw new ServiceException(" Không tồn tại tên người dùng"+ loginDTO.getUsername());
            }
            UserEntity userEntity = userOpt.get();
            if(!userEntity.getPassword().equals(loginDTO.getPassword())){
                throw new RuntimeException("Sai mật khẩu");
            }

            ResponseDTO<UserDTO> responseDTO = new ResponseDTO<>();
            responseDTO.setStatus(String.valueOf(HttpStatus.OK));
            responseDTO.setData(UserDTO.toDTO(userEntity));
            return responseDTO;

    }


}
