package com.example.member;

import com.example.member.DTO.MemberDTO;
import com.example.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

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
    @Transactional
    @Rollback(value = true)
    public void memberSaveTest() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("testEmail5");
        memberDTO.setMemberPassword("testPassword");
        memberDTO.setMemberName("testName");
        memberDTO.setMemberAge(25);
        memberDTO.setMemberPhone("010-2525-2525");

        Long saveId = memberService.save(memberDTO);
        MemberDTO savedMember = memberService.findById(saveId);

        assertThat(memberDTO.getMemberEmail()).isEqualTo(savedMember.getMemberEmail());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("로그인 테스트")
    public void loginTest() {

        String loginEmail = "Vain";
        String loginPassword = "pw";


        // 1. 회원가입(테스트용)
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail(loginEmail);
        memberDTO.setMemberPassword(loginPassword);
        memberDTO.setMemberName("베인");
        memberDTO.setMemberAge(26);
        memberDTO.setMemberPhone("010-2525-2525");

        memberService.save(memberDTO);

        // 2. 로그인 수행
        MemberDTO loginDTO = new MemberDTO();
        loginDTO.setMemberEmail(loginEmail);
        loginDTO.setMemberPassword(loginPassword);

        MemberDTO loginResult = memberService.login(loginDTO);

        // 3. 로그인 결과가 null 이 아니면 테스트 통과
        assertThat(loginResult).isNotNull();
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("정보수정 테스트")
    public void updateTest() {

        MemberDTO memberDTO = newMember();
        Long savedId = memberService.save(memberDTO);

        // 수정용 MemberDTO
        memberDTO.setId(savedId); // id값 세팅
        memberDTO.setMemberName("에러");

        // 수정처리
        memberService.update(memberDTO);

        // DB에서 조회한 이름이 수정할 때 사용한 이름과 같은지 확인
        MemberDTO memberByDB = memberService.findById(savedId);
        assertThat(memberByDB.getMemberName()).isEqualTo(memberDTO.getMemberName());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("삭제 테스트")
    public void deleteTest() {
        MemberDTO memberDTO = newMember();
        Long savedID = memberService.save(memberDTO);
        memberService.delete(savedID);

        assertThat(memberService.findById(savedID)).isNull();
    }




    public MemberDTO newMember() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("unknown");
        memberDTO.setMemberPassword("123");
        memberDTO.setMemberName("언노운");
        memberDTO.setMemberAge(26);
        memberDTO.setMemberPhone("010-2525-2525");
        return memberDTO;
    }
}
