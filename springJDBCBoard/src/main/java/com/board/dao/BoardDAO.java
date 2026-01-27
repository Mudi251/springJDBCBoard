package com.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        String query ="INSERT INTO JDBCBOARD VALUES(JDBCBOARD_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";

        int count = jdbcTemplate.update(query, board.getTitle(), board.getContent(), board.getWriter());

        return count;
    }

    public List<Board> boardList() {
        String query = "select * from jdbcBoard where board_no > 0 order by board_no desc,reg_date desc";

        List<Board> boardList = jdbcTemplate.query(query, new RowMapper<Board>() {
            @Override
            public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                Board board = new Board();

                board.setNo(rs.getInt("BOARD_NO"));
                board.setTitle(rs.getString("TITLE"));
                board.setContent(rs.getString("CONTENT"));
                board.setWriter(rs.getString("WRITER"));
                board.setRegDate(rs.getDate("REG_DATE"));
                return board;
            }
        });

        return boardList;
    }

	public Board selectByNo(Board board) {
		String query = "select * from jdbcBoard where board_no = ?";
		
		 List<Board> boardList = jdbcTemplate.query(query, new RowMapper<Board>() {
	            @Override
	            public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
	                Board board = new Board();

	                board.setNo(rs.getInt("BOARD_NO"));
	                board.setTitle(rs.getString("TITLE"));
	                board.setContent(rs.getString("CONTENT"));
	                board.setWriter(rs.getString("WRITER"));
	                board.setRegDate(rs.getDate("REG_DATE"));
	                return board;
	            }
	        }, board.getNo());
		 
		return boardList.isEmpty() ? null : boardList.get(0);
	}

}