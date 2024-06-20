<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Page</title>
    <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="../webjars/jquery/3.6.0/jquery.min.js"></script>
    <script>
        let nickname;
        function validate() {
            if(nickname.result === true){
                alert("닉네임 중복은 불가능 합니다.")
                return false;
            }else{
                return true;
            }
        }

        $(document).ready(function() {
            $("#nickname").keyup(function () {
                console.log("입력되는지 확인");
                $.ajax({
                    url: '/nickname-check',
                    method: 'get',
                    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                    dataType: "json",
                    // dataType: "text",
                    data: {
                        "nickname": $(this).val(),
                        "memberId" : ${member.memberId}
                    },
                    success: function (result, status, xhr) {
                        nickname = result
                        console.log(nickname)
                        if (nickname.result === true) {
                            $("#nickname-check").text(nickname.txt).css('color', 'red')
                        } else if (nickname.result === false) {
                            $("#nickname-check").text(nickname.txt).css('color', 'blue')
                        }

                    },
                    error: function () {
                    }
                });
            });
        });
    </script>
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
                    <form:form action="/mypage/update" method="post" modelAttribute="updateRequest" onsubmit="return validate()">
                        <div class="mb-3">
                            <label for="nickname" class="form-label">Nickname</label>
                            <input type="text" name="nickname" class="form-control" id="nickname" value="${member.nickname}">
                            <form:errors path="nickname" cssStyle=" color: red"/>
                            <p id ="nickname-check"></p>
                        </div>
                        <div class="mb-3">
                            <label for="pno" class="form-label">Phone-Number</label>
                            <input type="tel" name="pno" class="form-control" id="pno" value="${member.pno}">
                            <form:errors path="pno" cssStyle=" color: red"/>
                        </div>
                        <button type="submit" class="btn btn-secondary">수정 완료</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
