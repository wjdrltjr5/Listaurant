<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Listaurant 페이지</title>
    <style>
        body {
            background-color: #f5f5f5; /* 밝은 회색 배경 */
        }
        .navbar {
            background-color: #2c3e50; /* 어두운 블루/그레이 */
            border-bottom: 2px solid #ecf0f1; /* 밝은 회색 테두리 */
            justify-content: space-between;
            padding: 1rem 2rem;
        }
        .navbar-brand {
            color: #ecf0f1 !important; /* 밝은 회색 텍스트 */
            font-size: 1.5rem;
        }
        .navbar-toggler {
            color: #ecf0f1; /* 밝은 회색 */
            border: none;
            font-size: 1.25rem;
            background-color: transparent;
            cursor: pointer;
        }
        .navbar-toggler-icon {
            width: 1.5rem;
            height: 1.5rem;
            display: block;
            background-image: url("data:image/svg+xml;charset=utf8,%3Csvg viewBox='0 0 30 30' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath stroke='%23ecf0f1' stroke-width='2' stroke-linecap='round' stroke-miterlimit='10' d='M4 7h22M4 15h22M4 23h22'/%3E%3C/svg%3E");
            background-size: cover;
        }
        .navbar-nav {
            display: flex; /* 변경: 메뉴가 보이도록 수정 */
            flex-direction: column;
        }
        .offcanvas {
            background-color: #2c3e50; /* 어두운 블루/그레이 */
            color: #ecf0f1; /* 밝은 회색 텍스트 */
        }
        .offcanvas-header {
            border-bottom: 1px solid #ecf0f1;
        }
        @media (max-width: 768px) {
            .form-control {
                width: 80%;
            }
        }
    </style>
<body>
<nav class="navbar">
    <a class="navbar-brand" href="/">
        <img src="assets/brand/logo.png" alt="" width="72" height="57" style="border-radius: 50%">
        Listaurant
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
</nav>
<div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel" >
    <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasNavbarLabel">메뉴</h5>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
        <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
            <sec:authorize access="isAnonymous()">
            <li class="nav-item">
                <a class="nav-link" href="/login">로그인</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/sign-up">회원가입</a>
            </li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li class="nav-item">
                    <a class="nav-link" href="/mypage">내 정보</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">로그아웃</a>
                </li>
            </sec:authorize>

        </ul>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>