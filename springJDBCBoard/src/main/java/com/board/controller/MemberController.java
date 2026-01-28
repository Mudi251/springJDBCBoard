package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.Member;
import com.board.service.MemberService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@Slf4j
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/insertForm")
	public String memberInsertForm() {
		return "member/insertForm";
	}
	
	@PostMapping("/insert")
	public String memberInsert(Model model, Member member) {
		try {
		int	count = memberService.insertMember(member);
			if(count > 0) {
				model.addAttribute("message", "%s님 가입축하합니다.".formatted(member.getName()));
				return "member/success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "다시작성해주세요.");
		return "member/failed";
	}
}
