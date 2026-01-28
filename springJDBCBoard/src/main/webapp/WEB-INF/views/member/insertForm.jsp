<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
    /* 간단한 레이아웃 디자인 */
    .form-group { margin-bottom: 15px; }
    label { display: inline-block; width: 80px; }
    input { padding: 5px; }
</style>
</head>
<body>

    <h2>회원가입</h2>
    <form action="/member/insert" method="post">
        
        <div class="form-group">
            <label>아이디</label>
            <input type="text" name="id" required>
        </div>

        <div class="form-group">
            <label>비밀번호</label>
            <input type="password" name="pw" required>
        </div>

        <div class="form-group">
            <label>이름</label>
            <input type="text" name="name" required>
        </div>

        <div class="form-group">
            <label>전화번호</label>
            <input type="tel" name="phone" placeholder="010-0000-0000">
        </div>

        <div class="form-group">
            <label>이메일</label>
            <input type="email" name="email">
        </div>

        <button type="submit">가입하기</button>
        <button type="reset">다시쓰기</button>
    </form>
</body>
</html>