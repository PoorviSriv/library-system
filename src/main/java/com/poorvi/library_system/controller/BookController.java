package com.poorvi.library_system.controller;
import com.poorvi.library_system.dto.BookRequestDTO;
import com.poorvi.library_system.dto.BookResponseDTO;
import com.poorvi.library_system.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/books")
public class BookController
{
    @Autowired private BookService bookService;
    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody BookRequestDTO request)
    {
        return ResponseEntity.ok(bookService.createBook(request));
    }
    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks()
    {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable String id)
    {
        return ResponseEntity.ok(bookService.getBookById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable String id, @Valid @RequestBody BookRequestDTO request)
    {
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id)
    {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
