package studentLife.demo.service.dto.expense.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import studentLife.demo.enums.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SearchExpenseDTO {

    private int pageSize;
    private int pageIndex;

    private ExpenseCategory category;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String paymentMethod;
}
