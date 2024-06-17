<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Listaurant</title>
    <link rel="stylesheet" type="text/css" href="css/kakaomap.css">
</head>
<body>
<div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
    <a href="javascript:" class="btn_comm btn_resetMap" onclick="setCurrentPosition()"><span class="screen_out">현재 위치</span></a>
    <div id="menu_wrap" class="bg_white">
        <div class="option">
            <div>
                <form onsubmit="searchPlaces(); return false;">
                    키워드 : <input type="text" value="맛집" id="keyword" size="15">
                    <button type="submit">검색하기</button>
                </form>
            </div>
        </div>
        <hr>
        <ul id="placesList"></ul>
        <div id="pagination"></div>
    </div>
</div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4248f25bd845e2975d9354631e11d18c&libraries=services,clusterer,drawing,Marker"></script>
<script src="js/kakaomap.js"></script>
</body>
</html>
