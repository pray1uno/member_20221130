package com.example.member.repository;

import com.example.member.DTO.MemberDTO;
import com.example.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // memberEmail 로 조회
    // select * from member_table where memberEmail = ?
    // 메서드도 이름 짓는 규칙이 있음... 규칙에 따라야 함
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
