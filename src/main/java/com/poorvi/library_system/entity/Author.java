package com.poorvi.library_system.entity;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Author
{
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String ID;
    private String name;
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
