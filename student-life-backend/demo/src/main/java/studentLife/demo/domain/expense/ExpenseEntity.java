package studentLife.demo.domain.expense;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import studentLife.demo.domain.AbstractEntity;
import studentLife.demo.domain.user.UserEntity;
import studentLife.demo.enums.ExpenseCategory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "expense")
@EntityListeners(AuditingEntityListener.class)
public class ExpenseEntity extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount; // số tiền chi tiêu

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ExpenseCategory category;

    @Column(name = "description")
    private String description;

    @Column(name = "expense_date", nullable = false)
    private LocalDateTime expenseDate; // ngày giờ chi tiêu

    @Column(name = "payment_method")
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user; // liên kết với người dùng
}
