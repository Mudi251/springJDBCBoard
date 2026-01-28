<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	background-color: #f5f6f7;
}

.container {
	width: 1000px;
	margin: 50px auto;
	background-color: #fff;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

h2 {
	margin-bottom: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 12px;
	border-bottom: 1px solid #ddd;
	text-align: center;
}

th {
	background-color: #4a90e2;
	color: white;
}

tr:hover {
	background-color: #f1f1f1;
}

.empty {
	padding: 30px;
	text-align: center;
	color: #777;
}
</style>
</head>
<body>
	<div class="container">
		<h2>회원 목록</h2>
		<a href="/member/login" class="btn">로그인 페이지로 이동</a>
		<form action="/member/search" method="get" class="search-form">
			<select name="searchType" class="search-select">
				<option value="id">ID</option>
				<option value="name">NAME</option>
				<option value="phone">PHONE</option>
			</select> <input type="text" name="keyword" class="search-input"
				placeholder="Search">
			<button type="submit" class="btn-search">SEARCH</button>
		</form>
		<table>
			<thead>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>전화번호</th>
				</tr>
			</thead>

			<tbody>
				<c:choose>
					<c:when test="${empty memberList}">
						<tr>
							<td colspan="7" class="empty">등록된 회원이 없습니다.</td>
						</tr>
					</c:when>

					<c:otherwise>
						<c:forEach var="member" items="${memberList}">
							<tr>
								<td><a href="/member/updateForm?id=${member.id}">${member.id}</a></td>
								<td>${member.name}</td>
								<td>${member.phone}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</body>
</html>