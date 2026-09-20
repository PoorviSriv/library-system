package com.poorvi.library_system.repository;
import com.poorvi.library_system.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, String>
{
    List<Loan> findByMember_IdAndReturnDateIsNull(String memberId);
}
