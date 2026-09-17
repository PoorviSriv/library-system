package com.poorvi.library_system.entity;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Book
{
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    private String title;
    private String isbn;
    private int totalCopies;
    private int availableCopies;
    @ManyToMany @JoinTable( name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id") )
    private Set<Author> authors = new HashSet<>();

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

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

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}
