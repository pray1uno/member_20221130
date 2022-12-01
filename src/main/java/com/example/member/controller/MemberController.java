package com.example.member.controller;

import com.example.member.DTO.MemberDTO;
import com.example.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/save")
    public String saveForm() {
        return "memberPages/memberSave";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "memberPages/memberLogin";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "memberPages/memberLogin";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO,
                        HttpSession session) {
        MemberDTO result = memberService.login(memberDTO);
        if (result != null) {
            session.setAttribute("login", result.getMemberEmail());
            return "memberPages/memberMain";
        } else {
            return "memberPages/memberLogin";
        }
    }

    @GetMapping("/member/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "memberPages/memberList";
    }

    @GetMapping("/member/main")
    public String mainPage() {
        return "memberPages/memberMain";
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id,
                           Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("findById", memberDTO);
        return "memberPages/memberDetail";
    }

    @GetMapping("/member/delete/{id}")
    public String delete(@PathVariable Long id) {
        memberService.delete(id);
        return "redirect:/member/";

    }

    @GetMapping("/member/update")
    public String updateForm(Model model,
                             HttpSession session) {
        String loginEmail = (String) session.getAttribute("login");
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("update", memberDTO);
        return "memberPages/memberUpdate";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        return "memberPages/memberMain";
    }
}
