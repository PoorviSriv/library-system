package com.poorvi.library_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poorvi.library_system.dto.BookRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createBook_validRequest_returns200AndBookData() throws Exception {
        BookRequestDTO request = new BookRequestDTO();
        request.setTitle("Clean Code");
        request.setIsbn("9780132350884");
        request.setTotalCopies(3);

        mockMvc.perform(post("/api/books")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Clean Code"))
                .andExpect(jsonPath("$.availableCopies").value(3));
    }

    @Test
    void createBook_missingIsbn_returns400WithValidationMessage() throws Exception {
        BookRequestDTO request = new BookRequestDTO();
        request.setTitle("Clean Code");
        request.setTotalCopies(3);
        // isbn deliberately left null

        mockMvc.perform(post("/api/books")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.isbn").value("ISBN is required"));
    }

    @Test
    void getBookById_nonExistentId_returns404() throws Exception {
        mockMvc.perform(get("/api/books/does-not-exist"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }
}