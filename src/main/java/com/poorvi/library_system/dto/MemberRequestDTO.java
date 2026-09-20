package com.poorvi.library_system.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public class MemberRequestDTO
{
    @NotBlank(message = "Name is required") private String name;
    @NotBlank(message = "Email ID is required") @Email(message = "Email is not valid") private String email;
    @NotBlank(message = "Role is required") private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
