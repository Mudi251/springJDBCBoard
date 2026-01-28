package com.board.domain;

import lombok.Data;

@Data
public class Member {
	
	private String id;
	private int pw;
	private String name;
	private String phone;
	private String email;
}
