package com.poorvi.library_system.repository;
import com.poorvi.library_system.entity.Book;
import com.poorvi.library_system.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MemberRepository extends JpaRepository<Member, String>
{
}
