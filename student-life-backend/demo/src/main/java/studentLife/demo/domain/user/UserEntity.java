package studentLife.demo.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import studentLife.demo.domain.AbstractEntity;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class UserEntity extends AbstractEntity implements Serializable {

    @Id
    private String id;
    @Column(name = "user_name")
    private String userName;
    @Column(name= "email")
    private String email;
    @Column(name = " password")
    private String password;
    @Column(name = "rePassword")
    private String rePassword;
}
