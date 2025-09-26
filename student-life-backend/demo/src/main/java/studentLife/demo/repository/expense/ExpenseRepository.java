package studentLife.demo.repository.expense;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import studentLife.demo.domain.expense.ExpenseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, String> {

    ExpenseEntity findOneById(String id);

    @Query("""
    SELECT e FROM ExpenseEntity e
    WHERE (:category IS NULL OR e.category = :category)
      AND (:minAmount IS NULL OR e.amount >= :minAmount)
      AND (:maxAmount IS NULL OR e.amount <= :maxAmount)
      AND (:description IS NULL OR LOWER(e.description) LIKE LOWER(CONCAT('%', :description, '%')))
      AND (:startDate IS NULL OR e.expenseDate >= :startDate)
      AND (:endDate IS NULL OR e.expenseDate <= :endDate)
      AND (:paymentMethod IS NULL OR LOWER(e.paymentMethod) LIKE LOWER(CONCAT('%', :paymentMethod, '%')))
    """)
    Page<ExpenseEntity> searchExpenses(
            @Param("category") String category,
            @Param("minAmount") BigDecimal minAmount,
            @Param("maxAmount") BigDecimal maxAmount,
            @Param("description") String description,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("paymentMethod") String paymentMethod,
            Pageable pageable
    );
}
