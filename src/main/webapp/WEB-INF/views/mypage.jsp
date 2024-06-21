<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Page</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-4">
            <div class="card">
                <div class="card-body text-center">
                    <h5 class="card-title">Email</h5>
                    <p class="card-text">${member.email}</p>
                </div>
            </div>
        </div>
        <div class="col-md-8">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="overview-tab" data-bs-toggle="tab" data-bs-target="#overview" type="button" role="tab" aria-controls="overview" aria-selected="true">Info</button>
                </li>

            </ul>
            <div class="tab-content mt-3" id="myTabContent">
                <div class="tab-pane fade show active" id="overview" role="tabpanel" aria-labelledby="overview-tab">
                    <div class="mb-3">
                        <label for="pno" class="form-label">Nickname</label>
                        <input type="text" class="form-control" id="nickname"  readonly placeholder="${member.nickname}">
                    </div>
                    <div class="mb-3">
                            <label for="pno" class="form-label">Phone-Number</label>
                            <input type="tel" class="form-control" id="pno"  readonly placeholder="${member.pno}">
                        </div>
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <button type="button" class="btn btn-secondary"><a style="color: white; text-decoration-line: none" class="btn-primary" href="/mypage/${member.memberId}">정보수정</a></button>
                            <button type="button" class="btn btn-primary" ><a style="color: white; text-decoration-line: none" class="btn-primary" href="/mypage/password-update">비밀번호 변경</a></button>
                        </div>
                        <button type="button" class="btn btn-danger" onclick="confirmDeletion()">회원 탈퇴</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<hr>
<div class="container px-4 px-lg-5">
    <div class="row gx-4 gx-lg-5">
        <c:forEach var="comment" items="${commentList}">
            <div class="col-md-4 mb-2">
                <div class="card h-100 text-center">
                    <div class="card-body d-flex flex-column justify-content-center align-items-center">
                        <a href="/board?title=${comment.placeName}&lat=${comment.lat}&lng=${comment.lng}" style="text-decoration-line: none; color: black">
                            <h2 class="card-title">${comment.placeName}</h2>
                        </a>
                        <p>  별점 : ${comment.scope}</p>
                        <p class="card-text">${comment.text}</p>
                        <p>${comment.writtenDate}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
    <script>
    function confirmDeletion() {
        if (confirm("회원탈퇴 하시겠습니까?")) {
            window.location.href = "/mypage/delete";
        }
    }
</script>
</body>
</html>
