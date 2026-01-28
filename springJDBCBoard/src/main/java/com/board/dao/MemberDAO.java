package com.board.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.board.domain.Member;

@Repository
public class MemberDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insertMember(Member member) {
		
		String query = "insert into jdbcMember values( ?, ?, ?, ?)";
		int count = jdbcTemplate.update(query, member.getId(), member.getPw(), member.getName(), member.getPhone());
		return count;
	}

	
}
