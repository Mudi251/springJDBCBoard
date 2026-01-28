package com.board.domain;

import lombok.Data;

@Data
public class Member {
	
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String email;
	private String searchType;
	private String keyword;
}
