package com.example.member;

import com.example.member.DTO.MemberDTO;
import com.example.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberTest {
    // 회원가입 테스트
    // 신규회원 데이터 생성(DTO)
    // 회원가입 기능 실행
    // 가입 성공 후 가져온 id 값으로 DB 조회를 하고
    // 가입시 사용한 email과 DB에서 조회한 email이 일치하는지 판단
    // 기능 1개만 테스트 = 단위(유닛)테스트
    @Autowired // 테스트에서는 필드주입으로 진행함
    private MemberService memberService;

    @Test
    public void memberSaveTest() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("testEmail");
        memberDTO.setMemberPassword("testPassword");
        memberDTO.setMemberName("testName");
        memberDTO.setMemberAge(25);
        memberDTO.setMemberPhone("010-2525-2525");

        Long saveId = memberService.save(memberDTO);

    }
}
