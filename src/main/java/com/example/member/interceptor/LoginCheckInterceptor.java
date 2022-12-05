package com.example.member.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// implements = 인터페이스 구현
// 로그인 여부 확인
// 로그인 하지 않은 상태라면 로그인 페이지로 보내고 로그인을 수행하면 직전에
// 요청한 주소로 보내줌
// 로그인 상태라면 넘어감
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override // 메소드 재정의
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {
        // 서블릿리퀘스트 = 요청 객체, 서블릿리스펀스 = 응답 객체 / (톰캣 서버가 있어야만 사용 가능한 인터페이스)
        String requestURL = request.getRequestURI(); // 요청한 주소값을 가져올 수 있음
        System.out.println("requestURL = " + requestURL);
        // 메서드 재정의 시 이름과 매개변수는 건들 수 없음, request 객체로 부터 session을 얻어낼 수 있음
        HttpSession session = request.getSession();
        if (session.getAttribute("login") == null) {
            // 로그인이 끝나면 돌아갈 주소값을 같이 보냄
            response.sendRedirect("/member/login?redirectURL=" + requestURL);
            return false;
        }
        return true;
    }
}
