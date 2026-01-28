package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dao.MemberDAO;
import com.board.domain.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;
	@Override
	public Member selectByNo(Member member) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMember(Member member) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(Member member) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Member> memberList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> memberSearch(Member member) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertMember(Member member) throws Exception {
		int count = memberDAO.insertMember(member);
		return count;
	}

}
