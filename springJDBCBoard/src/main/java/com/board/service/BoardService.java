package com.board.service;

import java.util.List;
import com.board.domain.Board;

public interface BoardService {
	public int insertBoard(Board board) throws Exception; 
	public Board selectByNo(Board board) throws Exception; 
	public int updateBoard(Board board) throws Exception; 
	public int deleteBoard(Board board) throws Exception; 
	public List<Board> boardList() throws Exception;
}
