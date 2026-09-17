package com.poorvi.library_system.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
public class Loan
{
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne @JoinColumn(name = "book_id")
    private Book book;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    public String getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setId(String id) {
        this.id = id;
    }
}
