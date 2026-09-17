package com.poorvi.library_system.repository;
import com.poorvi.library_system.entity.Author;
import com.poorvi.library_system.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String>
{
}
