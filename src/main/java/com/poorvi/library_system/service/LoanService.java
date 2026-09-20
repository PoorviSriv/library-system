package com.poorvi.library_system.service;

import com.poorvi.library_system.dto.LoanRequestDTO;
import com.poorvi.library_system.dto.LoanResponseDTO;
import com.poorvi.library_system.entity.Book;
import com.poorvi.library_system.entity.Loan;
import com.poorvi.library_system.entity.Member;
import com.poorvi.library_system.exception.LoanRuleViolationException;
import com.poorvi.library_system.exception.ResourceNotFoundException;
import com.poorvi.library_system.repository.BookRepository;
import com.poorvi.library_system.repository.LoanRepository;
import com.poorvi.library_system.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService 
{
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MemberRepository memberRepository;
    
    private static final int MAX_ACTIVE_LOANS = 3;
    private static final int LOAN_PERIOD_DAYS = 14;
    
    @Transactional
    public LoanResponseDTO checkoutBook(LoanRequestDTO request)
    {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Member not found with id:"+ request.getMemberId()));
        
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Book not found with id:" + request.getBookId()));
        
        if(book.getAvailableCopies() <= 0)
        {
            throw new LoanRuleViolationException("No available copies of this book");
        }

        List<Loan> activeLoans = loanRepository.findByMember_IdAndReturnDateIsNull(member.getId());
        if(activeLoans.size() >= MAX_ACTIVE_LOANS)
        {
            throw new LoanRuleViolationException("Member already has " + MAX_ACTIVE_LOANS + " active loans");
        }
        
        Loan loan = new Loan();
        loan.setMember(member);
        loan.setBook(book);
        loan.setCheckoutDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(LOAN_PERIOD_DAYS));
        
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
        
        return toResponseDTO(loanRepository.save(loan));
    }
    @Transactional
    public LoanResponseDTO returnBook(String loanId)
    {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Loan not found with id: " + loanId));
        if(loan.getReturnDate() != null)
        {
            throw new LoanRuleViolationException("This loan has already been returned");
        }
        loan.setReturnDate(LocalDate.now());

        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return toResponseDTO(loanRepository.save(loan));
    }
    private LoanResponseDTO toResponseDTO(Loan loan)
    {
        LoanResponseDTO dto = new LoanResponseDTO();
        dto.setId(loan.getId());
        dto.setBookId(loan.getBook().getId());
        dto.setBookTitle(loan.getBook().getTitle());
        dto.setMemberId(loan.getMember().getId());
        dto.setMemberName(loan.getMember().getName());
        dto.setCheckoutDate(loan.getCheckoutDate());
        dto.setDueDate(loan.getDueDate());
        dto.setReturnDate(loan.getReturnDate());
        return dto;
    }
}
