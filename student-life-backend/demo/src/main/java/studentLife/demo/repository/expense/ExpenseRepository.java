package studentLife.demo.repository.expense;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import studentLife.demo.domain.expense.ExpenseEntity;
import studentLife.demo.enums.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, String> {

    ExpenseEntity findOneById(String id);

    @Query("""
    SELECT e FROM ExpenseEntity e
    WHERE e.category = COALESCE(:category, e.category)
      AND e.amount >= COALESCE(:minAmount, e.amount)
      AND e.amount <= COALESCE(:maxAmount, e.amount)
      AND LOWER(e.description) LIKE LOWER(CONCAT('%', COALESCE(:description, e.description), '%'))
      AND e.expenseDate >= COALESCE(:startDate, e.expenseDate)
      AND e.expenseDate <= COALESCE(:endDate, e.expenseDate)
      AND LOWER(e.paymentMethod) LIKE LOWER(CONCAT('%', COALESCE(:paymentMethod, e.paymentMethod), '%'))
      AND e.user.userName = :username
    ORDER BY e.expenseDate DESC
    """)
    Page<ExpenseEntity> searchExpenses(
            @Param("category") ExpenseCategory category,
            @Param("minAmount") BigDecimal minAmount,
            @Param("maxAmount") BigDecimal maxAmount,
            @Param("description") String description,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("paymentMethod") String paymentMethod,
            @Param("username") String username,
            Pageable pageable
    );
}
