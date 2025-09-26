package studentLife.demo.web.rest.expense;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import studentLife.demo.service.ResponseDTO;
import studentLife.demo.service.business.expense.ExpenseService;
import studentLife.demo.service.dto.expense.ExpenseDTO;
import studentLife.demo.service.dto.expense.crud.InsertExpenseDTO;
import studentLife.demo.service.dto.expense.crud.SearchExpenseDTO;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseResource {

    private final ExpenseService expenseService;

    public ExpenseResource(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/add")
    public ResponseDTO<ExpenseDTO> addExpense(@RequestBody InsertExpenseDTO dto) {
        return expenseService.addExpense(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseDTO<ExpenseDTO> updateExpense(@PathVariable String id,
                                                 @RequestBody InsertExpenseDTO dto) {
        return expenseService.updateExpense(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO<String> deleteExpense(@PathVariable String id) {
        return expenseService.deleteExpense(id);
    }

    @PostMapping("/search")
    public ResponseDTO<Page<ExpenseDTO>> searchExpenses(@RequestBody SearchExpenseDTO dto) {
        return expenseService.searchExpenses(dto);
    }
}
