package com.poorvi.library_system.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class BookRequestDTO
{
    @NotBlank(message = "Title is required") private String title;
    @NotBlank(message = "ISBN is required") private String isbn;
    @Min(value = 1, message = "Total copies must be at least 1") private int totalCopies;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }
}
