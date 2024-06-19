<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en" data-bs-theme="auto">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/sign-in/">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
    <script src="../assets/js/color-modes.js"></script>
    <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/login.css" rel="stylesheet">
    <script>
        function validateForm() {
            const password = document.getElementById("Password").value;
            const confirmPassword = document.getElementById("check-floatingPassword").value;
            const errorElement = document.getElementById("passwordCheckError");
            console.log(password, confirmPassword, errorElement)
            if (password !== confirmPassword) {
                errorElement.textContent = "비밀번호가 일치하지 않습니다.";
                return false;
            } else {
                return true;
            }
        }
    </script>
</head>
<jsp:include page="header.jsp"/>
<main class="form-signin w-100 m-auto">
    <form:form onsubmit="return validateForm()" action="/mypage/password-update" method="post" modelAttribute="pwdUpdateRequest">

        <h1 class="h3 mb-3 fw-normal">비밀번호와 비밀번호확인을 입력해주세요</h1>

        <div class="form-floating mb-3">
            <form:password path="passwd" class="form-control" id="Password" placeholder="Password"/>
            <label for="Password">Password</label>
            <form:errors path="passwd" cssStyle=" color : red"/>
        </div>
        <div class="form-floating mb-3">
            <input type="password" class="form-control" id="check-floatingPassword" placeholder="Password"/>
            <label for="check-floatingPassword">Password-Check</label>
            <div id="passwordCheckError" style="color: red;"></div>
        </div>
        <button class="btn btn-primary w-100 py-2" type="submit">변경하기</button>
        <p class="mt-5 mb-3 text-body-secondary">&copy; 2017–2024</p>
    </form:form>
</main>
<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>|
</body>
</html>
