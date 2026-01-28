<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	background-color: #f5f6f7;
}

.container {
	width: 420px;
	margin: 80px auto;
	background: #fff;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 4px 10px rgba(0,0,0,0.1);
}

h2 {
	text-align: center;
	margin-bottom: 20px;
}

input {
	width: 100%;
	padding: 10px;
	margin-bottom: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

button, .btn {
	width: 100%;
	padding: 10px;
	margin-top: 8px;
	border: none;
	border-radius: 4px;
	font-size: 14px;
	cursor: pointer;
}

.btn-update {
	background-color: #4a90e2;
	color: white;
}

.btn-delete {
	background-color: #e74c3c;
	color: white;
	text-align: center;
	display: block;
	text-decoration: none;
}

.btn-update:hover {
	background-color: #357abd;
}

.btn-delete:hover {
	background-color: #c0392b;
}
</style>
</head>

<body>
	<div class="container">
		<h2>회원 정보 수정</h2>

		<form action="/member/update" method="post">

			<input type="text" name="id" value="${member.id}" readonly>
			<input type="text" name="pw" value="${member.pw}" required>
			<input type="text" name="name" value="${member.name}" readonly>
			<input type="text" name="phone" value="${member.phone}" required>

			<button type="submit" class="btn-update">수정하기</button>
		</form>

		<a href="/member/delete?id=${member.id}"
		   class="btn btn-delete"
		   onclick="return confirm('정말 삭제하시겠습니까?');">
			회원 삭제
		</a>
	</div>
</body>
</html>