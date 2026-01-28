package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.domain.Board;
import com.board.domain.Member;
import com.board.service.MemberService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;




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
	@GetMapping("/memberList")
	public String memberList(Model model) {
		try {
			List<Member> memberList = memberService.memberList();
			model.addAttribute("memberList", memberList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "member/memberList";
	}
	@GetMapping("/updateForm")
	public String memberUpdateForm(Model model, Member m) {
		try {
			Member member = memberService.selectByNo(m);
			if(member == null) {
				model.addAttribute("message", "정보가 없습니다.");
				return "member/failed";
			}
			model.addAttribute("member", member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "member/updateForm";
	}
	@PostMapping("/update")
	public String memberUpdate(Model model, Member member) {
		try {
			int count = memberService.updateMember(member);
			if(count > 0) {
				model.addAttribute("message", "수정완료되었습니다.");
				return "member/success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "member/failed";
	}
	@GetMapping("/delete")
	public String memberdelete(Model model, Member member) {
		try {
		int	count = memberService.deleteMember(member);
			if(count > 0) {
				model.addAttribute("message", "삭제되었습니다.".formatted(member.getName()));
				return "member/success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "다시요청해주세요.");
		return "member/failed";
	}
	@GetMapping("/search")
	public String memberSearch(Member member, Model model) {
		log.info("memberSearch board="+member.toString());
		try {
			List<Member> memberList = memberService.memberSearch(member);
			model.addAttribute("memberList", memberList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "member/memberList";
	}
}
