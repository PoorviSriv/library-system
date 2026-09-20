package com.poorvi.library_system.dto;
import jakarta.validation.constraints.NotBlank;

public class LoanRequestDTO
{
    @NotBlank(message = "Member ID is required")
    private String memberId;
    @NotBlank(message = "Book ID is required")
    private String bookId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}

