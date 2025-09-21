package studentLife.demo.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import studentLife.demo.domain.AbstractEntity;
import studentLife.demo.domain.course.CourseEntity;
import studentLife.demo.domain.expense.ExpenseEntity;


import java.io.Serializable;
import java.util.List;

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


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseEntity> courses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExpenseEntity> expenses;
}
