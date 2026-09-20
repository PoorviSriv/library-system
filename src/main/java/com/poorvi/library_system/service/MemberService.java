package com.poorvi.library_system.service;
import com.poorvi.library_system.entity.Member;
import com.poorvi.library_system.dto.MemberRequestDTO;
import com.poorvi.library_system.dto.MemberResponseDTO;
import com.poorvi.library_system.repository.MemberRepository;
import com.poorvi.library_system.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class MemberService
{
    @Autowired private MemberRepository memberRepository;
    public MemberResponseDTO createMember(MemberRequestDTO request)
    {
        Member member = new Member();
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setRole(request.getRole());
        return toResponseDTO(memberRepository.save(member));
    }
    public MemberResponseDTO getMemberByID(String id)
    {
       Member member =  memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id = "+id));
       return toResponseDTO(member);
    }
    public List<MemberResponseDTO> getAllMembers()
    {
        return memberRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
    public MemberResponseDTO updateMember(String id, MemberRequestDTO request)
    {
        Member member =  memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id = "+id));
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setRole(request.getRole());
        return toResponseDTO(memberRepository.save(member));
    }
    public void deleteMember(String id)
    {
        memberRepository.deleteById(id);
    }
    private MemberResponseDTO toResponseDTO(Member member)
    {
        MemberResponseDTO response = new MemberResponseDTO();
        response.setId(member.getId());
        response.setName(member.getName());
        response.setEmail(member.getEmail());
        response.setRole(member.getRole());
        return response;
    }
}
