package studentLife.demo.service.dto.expense.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import studentLife.demo.domain.expense.ExpenseEntity;
import studentLife.demo.enums.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UpdateExpenseDTO {

    private BigDecimal amount;
    private ExpenseCategory category;
    private String description;
    private LocalDateTime expenseDate;
    private String paymentMethod;

    public static ExpenseEntity toEntity(UpdateExpenseDTO dto) {
        if (dto == null) return null;

        ExpenseEntity entity = new ExpenseEntity();
        entity.setAmount(dto.getAmount());
        entity.setCategory(dto.getCategory());
        entity.setDescription(dto.getDescription());
        entity.setExpenseDate(dto.getExpenseDate());
        entity.setPaymentMethod(dto.getPaymentMethod());

        return entity;
    }
}
