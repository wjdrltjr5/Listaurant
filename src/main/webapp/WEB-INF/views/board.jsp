<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Small Business - Start Bootstrap Template</title>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/board.css">
    <style>
        #container {overflow:hidden;height:300px;position:relative;}
        #btnRoadview,  #btnMap {position:absolute;top:5px;left:5px;padding:7px 12px;font-size:14px;border: 1px solid #dbdbdb;background-color: #fff;border-radius: 2px;box-shadow: 0 1px 1px rgba(0,0,0,.04);z-index:1;cursor:pointer;}
        #btnRoadview:hover,  #btnMap:hover{background-color: #fcfcfc;border: 1px solid #c1c1c1;}
        #container.view_map #mapWrapper {z-index: 10;}
        #container.view_map #btnMap {display: none;}
        #container.view_roadview #mapWrapper {z-index: 0;}
        #container.view_roadview #btnRoadview {display: none;}
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
                <div id="map" style="width:100%;height:100%"></div> <!-- 지도를 표시할 div 입니다 -->
                <input type="button" id="btnRoadview" onclick="toggleMap(false)" title="로드뷰 보기" value="로드뷰">
            </div>
            <div id="rvWrapper" style="width:100%;height:300px;position:absolute;top:0;left:0;">
                <div id="roadview" style="height:100%"></div> <!-- 로드뷰를 표시할 div 입니다 -->
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
        <div class="col-md-4 mb-5">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title">평균 별점</h2><hr>
                    <p class="card-text"></p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-5">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title">인기 댓글</h2><hr>
                    <p class="card-text"></p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-5">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title">최신 댓글</h2><hr>
                    <p class="card-text">ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ</p>
                </div>
            </div>
        </div>

        <div class="card text-white bg-secondary my-5 py-4 text-center" style="margin-bottom: 100px">
            <div>
                <input type="text" id="commentInput" name="txt" class="form-control" style="height: 40px;">
            </div>
            <button class="btn btn-primary w-100 py-2" id="submitComment" type="button">댓글 달기</button>
        </div>


    </div>
</div>
<!-- Footer-->
<!-- Core theme JS-->
<script src="/js/board.js"></script>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>