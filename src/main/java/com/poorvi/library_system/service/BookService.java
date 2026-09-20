package com.poorvi.library_system.service;
import com.poorvi.library_system.dto.BookRequestDTO;
import com.poorvi.library_system.dto.BookResponseDTO;
import com.poorvi.library_system.entity.Book;
import com.poorvi.library_system.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.poorvi.library_system.exception.ResourceNotFoundException;
@Service
public class BookService
{
    @Autowired private BookRepository bookRepository;
    public BookResponseDTO createBook(BookRequestDTO request)
    {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setTotalCopies(request.getTotalCopies());
        book.setAvailableCopies(request.getTotalCopies());
        return toResponseDTO(bookRepository.save(book));
    }
    public List<BookResponseDTO> getAllBooks()
    {
        return bookRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
    public BookResponseDTO getBookById(String id)
    { Book book = bookRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return toResponseDTO(book);
    }
    public BookResponseDTO updateBook(String id, BookRequestDTO request)
    {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setTotalCopies(request.getTotalCopies());
        return toResponseDTO(bookRepository.save(book));
    }
    public void deleteBook(String id)
    {
        bookRepository.deleteById(id);
    }
    private BookResponseDTO toResponseDTO(Book book)
    {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.setTotalCopies(book.getTotalCopies());
        dto.setAvailableCopies(book.getAvailableCopies());
        return dto;
    }
}
