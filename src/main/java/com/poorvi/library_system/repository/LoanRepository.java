package com.poorvi.library_system.repository;
import com.poorvi.library_system.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LoanRepository extends JpaRepository<Loan, String>
{
}
