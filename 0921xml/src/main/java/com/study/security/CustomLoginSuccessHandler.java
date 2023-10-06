package com.study.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		// 권한에 따라 이동하는 페이지가 다르게 설정할것임
		
		// 권한은 여러개일수있으므로 컬렉션에 하나씩 저장해줌
		List<String> role = new ArrayList<>();
		authentication.getAuthorities().forEach(auth -> {
			role.add(auth.getAuthority());
		});
		
		if(role.contains("ROLE_ADMIN")) {
			response.sendRedirect("/admin");
			return;
		}
		if(role.contains("ROLE_MEMBER")) {
			response.sendRedirect("/member");
			return;
		}
		response.sendRedirect("/");
		
	}
	
	

}
