package studentLife.demo.service.business.expense;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studentLife.demo.domain.expense.ExpenseEntity;
import studentLife.demo.enums.ExpenseCategory;
import studentLife.demo.repository.expense.ExpenseRepository;
import studentLife.demo.repository.user.UserRepository;
import studentLife.demo.service.ResponseDTO;
import studentLife.demo.service.dto.expense.ExpenseDTO;
import studentLife.demo.service.dto.expense.crud.InsertExpenseDTO;
import studentLife.demo.service.dto.expense.crud.SearchExpenseDTO;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseDTO<ExpenseDTO> addExpense(InsertExpenseDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByUserName(auth.getName())
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        ExpenseEntity entity = InsertExpenseDTO.toEntity(dto);
        entity.setUser(user);

        entity = expenseRepository.save(entity);

        ResponseDTO<ExpenseDTO> response = new ResponseDTO<>();
        response.setData(ExpenseDTO.toDTO(entity));
        response.setStatus(String.valueOf(HttpStatus.OK));
        return response;
    }

    @Transactional
    public ResponseDTO<ExpenseDTO> updateExpense(String id, InsertExpenseDTO dto) {
        ExpenseEntity entity = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense không tồn tại"));

        if(dto.getAmount() != null) entity.setAmount(dto.getAmount());
        if(dto.getCategory() != null) entity.setCategory(dto.getCategory());
        if(dto.getDescription() != null) entity.setDescription(dto.getDescription());
        if(dto.getExpenseDate() != null) entity.setExpenseDate(dto.getExpenseDate());
        if(dto.getPaymentMethod() != null) entity.setPaymentMethod(dto.getPaymentMethod());

        entity = expenseRepository.save(entity);

        ResponseDTO<ExpenseDTO> response = new ResponseDTO<>();
        response.setData(ExpenseDTO.toDTO(entity));
        response.setStatus(String.valueOf(HttpStatus.OK));
        return response;
    }

    @Transactional
    public ResponseDTO<String> deleteExpense(String id) {
        ExpenseEntity entity = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense không tồn tại"));

        expenseRepository.delete(entity);

        ResponseDTO<String> response = new ResponseDTO<>();
        response.setData("Đã xóa chi tiêu với ID: " + id);
        response.setStatus(String.valueOf(HttpStatus.OK));
        return response;
    }

    @Transactional(readOnly = true)
    public ResponseDTO<Page<ExpenseDTO>> searchExpenses(SearchExpenseDTO dto) {
        var pageable = PageRequest.of(dto.getPageIndex(), dto.getPageSize());

        var username = currentUsername();

        Page<ExpenseEntity> page = expenseRepository.searchExpenses(
                dto.getCategory(),
                dto.getMinAmount(),
                dto.getMaxAmount(),
                dto.getDescription(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getPaymentMethod(),
                username,
                pageable
        );

        ResponseDTO<Page<ExpenseDTO>> response = new ResponseDTO<>();
        response.setData(page.map(ExpenseDTO::toDTO));
        response.setStatus(String.valueOf(HttpStatus.OK));
        return response;
    }

    private String currentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
