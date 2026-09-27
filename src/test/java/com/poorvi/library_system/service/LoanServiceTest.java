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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest
{
    @Mock
    private LoanRepository loanRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private LoanService loanService;

    @Test
    void checkoutBook_success_decrementAvailableCopies()
    {
        //Arrange
        Member member = new Member();
        member.setId("member1");

        Book book = new Book();
        book.setId("book1");
        book.setAvailableCopies(2);

        LoanRequestDTO request = new LoanRequestDTO();
        request.setBookId("book1");
        request.setMemberId("member1");

        when(memberRepository.findById("member1")).thenReturn(Optional.of(member));
        when(bookRepository.findById("book1")).thenReturn(Optional.of(book));
        when(loanRepository.findByMember_IdAndReturnDateIsNull("member1")).thenReturn(Collections.emptyList());
        when(loanRepository.save(any(Loan.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //Act
        LoanResponseDTO response = loanService.checkoutBook(request);

        //Assert
        assertEquals("member1", response.getMemberId());
        assertEquals("book1", response.getBookId());
        assertEquals(1, book.getAvailableCopies());
        verify(bookRepository).save(book);
    }
}
