package com.poorvi.library_system.controller;
import com.poorvi.library_system.dto.MemberRequestDTO;
import com.poorvi.library_system.dto.MemberResponseDTO;
import com.poorvi.library_system.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/members")
public class MemberController
{
    @Autowired MemberService memberService;
    @PostMapping
    public ResponseEntity<MemberResponseDTO> createMember(@Valid @RequestBody MemberRequestDTO request)
    {
        return ResponseEntity.ok(memberService.createMember(request));
    }
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> updateMember(@Valid @RequestBody MemberRequestDTO request,
                                                          @Valid @PathVariable String id)
    {
        return ResponseEntity.ok(memberService.updateMember(id, request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> getMemberByID(@Valid @PathVariable String id)
    {
        return ResponseEntity.ok(memberService.getMemberByID(id));
    }
    @GetMapping
    public ResponseEntity<List<MemberResponseDTO>> getAllMembers()
    {
        return ResponseEntity.ok(memberService.getAllMembers());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@Valid @PathVariable String id)
    {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
