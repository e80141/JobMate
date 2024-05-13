package com.job.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.job.dto.CompanyDto;
import com.job.dto.UserDto;
import com.job.mapper.CompanyMapper;
import com.job.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CompanyController {
	
	@Autowired 
	UserMapper userMapper;
	
	@Autowired
	CompanyMapper companyMapper;
	//기업로그인을 한다
	@PostMapping("/companylogin")
	public String companylogin(HttpServletRequest request, UserDto userDto,
	                           HttpServletResponse response) throws IOException {

			String userId = request.getParameter("userId");
			String userPw = request.getParameter("userPw");
			System.out.println(userId);
			System.out.println(userPw);
			userDto = userMapper.login(userId, userPw);

			if (userDto != null) {// 아이디와 암호 일치시 수행
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(60 * 60); // 60분동안 로그인 유지
				session.setAttribute("login", userDto);
				session.setAttribute("isLoggedIn", true);
				  return "redirect:/";

			} else {// 로그인 실패시
				PrintWriter out = response.getWriter();
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8;");
				out.println("<script> alert('Please check your ID password');");
				out.println("history.go(-1); </script>");
				out.close();
				
				return "/companylogin";
			}
		}
		// 기업 회원가입을 한다
		
			@PostMapping("/companyjoin")
			@Transactional
			public ModelAndView write(CompanyDto companyDto,UserDto userDto) {
				//저장
				userMapper.insertUser(userDto);
				companyMapper.insertCompany(companyDto);
				

				
				//데이터를 가지고 이동한다
				ModelAndView mv = new ModelAndView();
				
				mv.setViewName("redirect:/");
				return mv;
			}
			
			
}
