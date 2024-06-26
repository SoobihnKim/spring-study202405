<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    
    <%@ include file = "../include/static-head.jsp" %>

    <!-- ck editor -->
    <script src="https://cdn.ckeditor.com/4.17.2/standard/ckeditor.js"></script>
    <style>


        .form-container {
            width: 500px;
            margin: auto;
            padding: 20px;
            background-image: linear-gradient(135deg, #a1c4fd, #fbc2eb);
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 4px;
            font-size: 18px;
        }
        .form-container h1 {
            font-size: 40px;
            font-weight: 700;
            letter-spacing: 10px;
            text-align: center;
            margin-bottom: 20px;
            color: #ffffff;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-size: 20px;
        }
        input[type="text"],
        textarea {
            font-size: 18px;
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 2px solid #ffffff;
            border-radius: 8px;
            margin-bottom: 10px;
            background-color: rgba(255, 255, 255, 0.8);
        }
        textarea {
            resize: none;
            height: 200px;
        }
        .buttons {
            display: flex;
            justify-content: flex-end;
            margin-top: 20px;
        }
        button {
            font-size: 20px;
            padding: 10px 20px;
            border: none;
            margin-right: 10px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
            border-radius: 4px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s;
        }
        button.list-btn {
            background: #e61e8c;
        }
        button:hover {
            background-color: #3d8b40;
        }
        button.list-btn:hover {
            background: #e61e8c93;
        }
    </style>
</head>
<body>
    <%@ include file = "../include/header.jsp" %>
    
<div id="wrap" class="form-container">
    <h1>꾸러기 게시판 글쓰기</h1>
    <form action="/board/write" method="post">
        <label for="title">작성자</label>
        <input type="text" id="writer" name="writer" value="${login.nickName}" readonly>
        <label for="title">제목</label>
        <input type="text" id="title" name="title" required>
        <label for="content">내용</label>
        <textarea id="content" name="content" maxlength="200" required></textarea>
        <div class="buttons">
            <button class="list-btn" type="button" onclick="window.location.href='/board/list'">목록</button>
            <button type="submit">글쓰기</button>
        </div>
    </form>
</div>
<script>
    CKEDITOR.replace('content');
</script>
</body>
</html>


</body>
</html>