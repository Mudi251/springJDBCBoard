package com.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.board.domain.Board;
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

	public List<Member> memberList() {
		
		String query = "select * from jdbcMember";
	    List<Member> memberList = jdbcTemplate.query(query, new RowMapper<>() {
		@Override
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException { //1회성 한번 값을 셋팅하고 사라짐
			Member member = new Member();
			member.setId(rs.getString("id"));
			member.setName(rs.getString("name"));
			member.setPhone(rs.getString("phone"));
			return member;
		 }
	  });
		return memberList;
	}
	public int updateMember(Member member) {
		String query = "update jdbcMember set pw = ? ,phone = ? where id = ?";
		int count = jdbcTemplate.update(query, member.getPw(), member.getPhone(), member.getId());
		return count;
	}

	public Member selectByNo(Member member) {
		String query = "select * from jdbcMember where id = ?";
		List<Member> memberList = jdbcTemplate.query(query, new RowMapper<>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException { //1회성 한번 값을 셋팅하고 사라짐
				Member member = new Member();
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				return member;
			 }
		  },member.getId());
		return memberList.isEmpty()? null: memberList.get(0);
	}

	public int deleteMember(Member member) {
		String query = "delete from jdbcMember where id = ?";
		int count = jdbcTemplate.update(query, member.getId());
		return count;
	}
	public List<Member> memberSearch(Member member) {

        String searchItem = member.getSearchType();
        List<String> searchList = Arrays.asList("title","content","writer");
        //검색타입이 존재하지 않으면 기본검색은 title로 하게된다. 
        if(!searchList.contains(member.getSearchType())) {
            searchItem = "id";
        }

        String query =  "select * from jdbcMember where "+searchItem+" like '%"+member.getKeyword()+"%'";

        List<Member> memberList = jdbcTemplate.query(query, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setId(rs.getString("id"));
                member.setPw(rs.getString("pw"));
                member.setName(rs.getString("name"));
                member.setPhone(rs.getString("phone"));
                return member;
            }
        });

        return memberList;
    }
}