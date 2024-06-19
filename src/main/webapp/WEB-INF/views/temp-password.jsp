<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en" data-bs-theme="auto">
<script>
    document.getElementById('myForm').addEventListener('submit', function(event) {
        event.preventDefault();
        alert('발급되었습니다.');
    });
</script>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/sign-in/">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
    <script src="../assets/js/color-modes.js"></script>
    <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/login.css" rel="stylesheet">
</head>
<jsp:include page="header.jsp"/>
<main class="form-signin w-100 m-auto">
    <form action="/temp-password" method="post" id="myForm" >
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <h1 class="h3 mb-3 fw-normal">이메일을 입력해주세요</h1>
        <div class="form-floating mb-3">
            <input class="form-control" name="email" id="Email" placeholder="Email"/>
            <label for="Email">Email</label>
        </div>
        <button class="btn btn-primary w-80 py-2" type="submit">임시비밀번호발급</button>
    </form>
</main>
<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
