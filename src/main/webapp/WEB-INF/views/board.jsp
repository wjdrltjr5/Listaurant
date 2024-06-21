<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Listaurant 페이지</title>
    <link rel="stylesheet" type="text/css" href="/css/board.css">
    <style>
        #container {overflow:hidden;height:300px;position:relative;}
        #btnRoadview,  #btnMap {position:absolute;top:5px;left:5px;padding:7px 12px;font-size:14px;border: 1px solid #dbdbdb;background-color: #fff;border-radius: 2px;box-shadow: 0 1px 1px rgba(0,0,0,.04);z-index:1;cursor:pointer;}
        #btnRoadview:hover,  #btnMap:hover{background-color: #fcfcfc;border: 1px solid #c1c1c1;}
        #container.view_map #mapWrapper {z-index: 10;}
        #container.view_map #btnMap {display: none;}
        #container.view_roadview #mapWrapper {z-index: 0;}
        #container.view_roadview #btnRoadview {display: none;}
        .comments-container {
            display: flex;
            flex-direction: column;
        }
        .comment {
            flex: 1; /* 모든 comment 요소의 높이를 균등하게 설정 */
        }
    </style>
</head>
<body>
<!-- Responsive navbar-->
<jsp:include page="header.jsp"/>
<!-- Page Content-->
<div class="container px-4 px-lg-5">
    <!-- Heading Row-->
    <div class="row gx-4 gx-lg-5 align-items-center my-5">
        <div id="container" class="view_map">
            <div id="mapWrapper" style="width:100%;height:300px;position:relative;">
                <div id="map" style="width:100%;height:100%"></div>
                <input type="button" id="btnRoadview" onclick="toggleMap(false)" title="로드뷰 보기" value="로드뷰">
            </div>
            <div id="rvWrapper" style="width:100%;height:300px;position:absolute;top:0;left:0;">
                <div id="roadview" style="height:100%"></div>
                <input type="button" id="btnMap" onclick="toggleMap(true)" title="지도 보기" value="지도">
            </div>
        </div>
    </div>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4248f25bd845e2975d9354631e11d18c&libraries=services,clusterer,drawing,Marker"></script>

    <!-- Call to Action-->
    <div class="card text-white bg-secondary my-5 py-4 text-center">
        <div class="card-body"><p class="text-white m-0"></p><hr><p class="text-white m-0-2"></p></div>
    </div>
    <!-- Content Row-->
    <div class="row gx-4 gx-lg-5">
        <div class="col-md-4 mb-2">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title">평균 별점</h2><hr>
                    <p class="card-text">${avgScope}</p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-2">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title">인기 댓글</h2><hr>
                    <p class="card-text" id="most-popular">${pop.text}</p>
                    <small>${pop.nickname} - ${pop.writtenDate}</small>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-2">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title">최신 댓글</h2><hr>
                    <p class="card-text"  id="most-recent">${recent.text}</p>
                    <small>${recent.nickname} - ${recent.writtenDate}</small>
                </div>
            </div>
        </div>
        <div class="card text-white bg-secondary my-5 py-4 text-center" style="margin-bottom: 100px">
            <form id="commentForm" action="/board/comment" method="post">
                <div class="form-floating mb-3">
                    <input type="hidden" name="placeName" value="${title}"/>
                    <input type="hidden" name="lat" value="${lat}"/>
                    <input type="hidden" name="lng" value="${lng}"/>
                    <input type="text" class="form-control" id="Place-Comment" name="text" placeholder="Place-Comments" required/>
                    <label for="Place-Comment">후기를 남겨주세요.</label>
                </div>

                <!-- 별점 입력 필드 추가 -->
                <div class="form-group mb-3">
                    <label for="rating">별점을 매겨주세요:</label>
                    <div id="rating" class="d-flex justify-content-center">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="scope" id="rating1" value="1" required>
                            <label class="form-check-label" for="rating1">1</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="scope" id="rating2" value="2">
                            <label class="form-check-label" for="rating2">2</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="scope" id="rating3" value="3">
                            <label class="form-check-label" for="rating3">3</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="scope" id="rating4" value="4">
                            <label class="form-check-label" for="rating4">4</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="scope" id="rating5" value="5">
                            <label class="form-check-label" for="rating5">5</label>
                        </div>
                    </div>
                </div>

                <button class="btn btn-primary w-100 py-2" type="submit">댓글달기</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>

        <!-- 댓글 리스트 추가 부분 -->
        <div class="card my-5">
            <div class="card-header">
                <h2>댓글 리스트 (${countComments})</h2>
            </div>
            <div class="card-body">
                <div class="comments-container">
                <c:forEach var="comment" items="${comments}">
                    <div class="comment">
                        <p>${comment.text}</p>
                        <small>${comment.nickname} - ${comment.writtenDate} - ${comment.scope}</small>
                        <script>
                            console.log(${memberId});
                        </script>
                        <c:if test="${comment.memberId eq memberId}">
                            <form action="/board/delete" method="post">
                                <input type="hidden" name="commentId" value="${comment.txtId}" /> <!-- txtId로 수정 -->
                                <button type="submit" class="btn btn-danger btn-sm">삭제</button>
                            </form>
                            <form action="/board/edit" method="get">
                                <input type="hidden" name="commentId" value="${comment.txtId}" /> <!-- txtId로 수정 -->
                                <button type="submit" class="btn btn-secondary btn-sm">수정</button>
                            </form>
                        </c:if>
                        <small class="d-flex justify-content-between align-items-center">
                             <span>
                                ${comment.nickname} - ${comment.writtenDate} - ${comment.scope}
                             </span>
                            <span class="d-flex align-items-center">
                                <a href="/recommend?txtId=${comment.txtId}&memberId=${memberId}}"><img id="recommend" src="images/reicon.png" alt="" width="20" height="20" class="me-1"/></a>
                                ${comment.recommend}
                            </span>
                        </small>
                        <hr>
                    </div>
                </c:forEach>
                </div>
            </div>


        </div>
        <!-- 댓글 리스트 추가 끝 -->
    </div>
</div>
<!-- Footer-->
<!-- Core theme JS-->
<script src="/js/board.js"></script>

<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>