package com.poorvi.library_system.controller;

import com.poorvi.library_system.dto.LoanRequestDTO;
import com.poorvi.library_system.dto.LoanResponseDTO;
import com.poorvi.library_system.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class LoanController
{
    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanResponseDTO> checkoutBook(@Valid @RequestBody LoanRequestDTO request)
    {
        return ResponseEntity.ok(loanService.checkoutBook(request));
    }
    @PutMapping("/{id}/return")
    public ResponseEntity<LoanResponseDTO> returnBook(@PathVariable String id)
    {
        return ResponseEntity.ok(loanService.returnBook(id));
    }
}
