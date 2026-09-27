package com.poorvi.library_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poorvi.library_system.dto.MemberRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createMember_validRequest_returns200AndMemberData() throws Exception {
        MemberRequestDTO request = new MemberRequestDTO();
        request.setName("Jane Doe");
        request.setEmail("jane.doe@example.com");
        request.setRole("MEMBER");

        mockMvc.perform(post("/api/members")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"));
    }

    @Test
    void createMember_missingEmail_returns400WithValidationMessage() throws Exception {
        MemberRequestDTO request = new MemberRequestDTO();
        request.setName("Jane Doe");
        request.setRole("MEMBER");
        // email deliberately left null

        mockMvc.perform(post("/api/members")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email").value("Email ID is required"));
    }

    @Test
    void getMemberById_nonExistentId_returns404() throws Exception {
        mockMvc.perform(get("/api/members/does-not-exist"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }
}