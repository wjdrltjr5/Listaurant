<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Page</title>
    <link href="bootstrap-5.3.2-dist/css/bootstrap.min.css" rel="stylesheet">
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
                            <label for="pno" class="form-label">Phone-Number</label>
                            <input type="tel" class="form-control" id="pno"  readonly placeholder="${member.pno}">
                        </div>
                    <button type="button" class="btn btn-secondary"><a style="color: white; text-decoration-line: none" class="btn-primary" href="/mypage/${member.memberId}">EDIT</a></button>
                    <button type="button" class="btn btn-danger mt-3" onclick="confirmDeletion()">회원 탈퇴</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="bootstrap-5.3.2-dist/js/bootstrap.bundle.min.js"></script>
<script>
    function confirmDeletion() {
        if (confirm("회원탈퇴 하시겠습니까?")) {
            window.location.href = "/mypage/delete";
        }
    }
</script>
</body>
</html>
