package com.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.board.domain.Board;

@Repository
public class BoardDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insertBoard(Board board) {
		String query = "INSERT INTO JDBCBOARD VALUES(JDBCBOARD_SEQ.NEXTVAL, ?, ?, ?,SYSDATE)";
		int count = jdbcTemplate.update(query, board.getTitle(), board.getContent(), board.getWriter());
		return count;
	}

	public List<Board> boardList() {
		String query = "SELECT * FROM JDBCBOARD WHERE NO >0 ORDER BY NO DESC, REGDATE DESC";
		List<Board> boardList = jdbcTemplate.query(query, new RowMapper<Board>() {
			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board();
				board.setNo(rs.getInt("NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setContent(rs.getString("CONTENT"));
				board.setWriter(rs.getString("WRITER"));
				board.setRegDate(rs.getDate("REGDATE"));
				return board;
			}
		});
		return boardList;
	}

	public Board selectByNo(Board board) {
		String query = "SELECT * FROM JDBCBOARD WHERE NO = ?";
		List<Board> boardList = jdbcTemplate.query(query, new RowMapper<Board>() {
			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board();
				board.setNo(rs.getInt("NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setContent(rs.getString("CONTENT"));
				board.setWriter(rs.getString("WRITER"));
				board.setRegDate(rs.getDate("REGDATE"));
				return board;
			}
		}, board.getNo());
		return boardList.isEmpty() ? null : boardList.get(0);
	}

	public int deleteBoard(Board board) {
		String query = "DELETE FROM JDBCBOARD WHERE NO = ?";
		int count = jdbcTemplate.update(query, board.getNo());
		return count;
	}

	public int updateBoard(Board board) {
		String query = "UPDATE JDBCBOARD SET TITLE = ?, CONTENT = ? WHERE NO = ?";
		int count = jdbcTemplate.update(query, board.getTitle(), board.getContent(), board.getNo());
		return count;
	}

	public List<Board> boardSearch(Board board) {

        String searchItem = board.getSearchType(); 
        List<String> searchList = Arrays.asList("title","content","writer");
        //검색타입이 존재하지 않으면 기본검색은 title로 하게된다. 
        if(!searchList.contains(board.getSearchType())) {
            searchItem = "title";
        }

        String query =  "select * from jdbcBoard where "+searchItem+" like '%"+board.getKeyword()+"%'";

        List<Board> boardList = jdbcTemplate.query(query, new RowMapper<Board>() {
            @Override
            public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                Board board = new Board();
                board.setNo(rs.getInt("no"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setWriter(rs.getString("writer"));
                board.setRegDate(rs.getDate("regDate"));
                return board;
            }
        });

        return boardList;
    }
}