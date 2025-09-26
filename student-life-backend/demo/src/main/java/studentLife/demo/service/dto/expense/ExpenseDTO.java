package studentLife.demo.service.dto.expense;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import studentLife.demo.domain.expense.ExpenseEntity;
import studentLife.demo.enums.ExpenseCategory;
import studentLife.demo.service.AbstractDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ExpenseDTO extends AbstractDTO<String> {

    private String id;
    private BigDecimal amount;
    private ExpenseCategory category;
    private String description;
    private LocalDateTime expenseDate;
    private String paymentMethod;

    public static ExpenseDTO toDTO(ExpenseEntity entity) {
        if (entity == null) return null;

        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setCategory(entity.getCategory());
        dto.setDescription(entity.getDescription());
        dto.setExpenseDate(entity.getExpenseDate());
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setLastModifiedDate(entity.getLastModifiedDate());

        return dto;
    }
}
