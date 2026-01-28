package com.board.service;

import java.util.List;
import com.board.domain.Member;

public interface MemberService {

	public Member selectByNo(Member member) throws Exception;

	public int updateMember(Member member) throws Exception;

	public int deleteMember(Member member) throws Exception;

	public List<Member> memberList() throws Exception;

	public List<Member> memberSearch(Member member) throws Exception;

	public int insertMember(Member member) throws Exception;
}
