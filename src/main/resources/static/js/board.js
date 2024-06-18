/*!
* Start Bootstrap - Small Business v5.0.6 (https://startbootstrap.com/template/small-business)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-small-business/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project
var container = document.getElementById('container'), // 지도와 로드뷰를 감싸고 있는 div 입니다
    mapWrapper = document.getElementById('mapWrapper'), // 지도를 감싸고 있는 div 입니다
    btnRoadview = document.getElementById('btnRoadview'), // 지도 위의 로드뷰 버튼, 클릭하면 지도는 감춰지고 로드뷰가 보입니다
    btnMap = document.getElementById('btnMap'), // 로드뷰 위의 지도 버튼, 클릭하면 로드뷰는 감춰지고 지도가 보입니다
    rvContainer = document.getElementById('roadview'), // 로드뷰를 표시할 div 입니다
    mapContainer = document.getElementById('map'); // 지도를 표시할 div 입니다

// 지도와 로드뷰를 표시할 변수들
var map, roadview, mapMarker, rvMarker;

// 함수로 특정 장소의 좌표를 설정합니다
function setPlacePosition(lat, lng) {
    var placePosition = new kakao.maps.LatLng(lat, lng);

    // 지도 옵션입니다
    var mapOption = {
        center: placePosition, // 지도의 중심좌표
        level: 4 // 지도의 확대 레벨
    };

    // 지도를 표시할 div와 지도 옵션으로 지도를 생성합니다
    map = new kakao.maps.Map(mapContainer, mapOption);

    // 로드뷰 객체를 생성합니다
    roadview = new kakao.maps.Roadview(rvContainer);
    var roadviewClient = new kakao.maps.RoadviewClient();
    roadviewClient.getNearestPanoId(placePosition, 50, function(panoId) {
        roadview.setPanoId(panoId, placePosition); //panoId와 중심좌표를 통해 로드뷰 실행
    });
    // 로드뷰의 위치를 특정 장소를 포함하는 파노라마 ID로 설정합니다
    // 로드뷰의 파노라마 ID는 Wizard를 사용하면 쉽게 얻을수 있습니다

    // 특정 장소가 잘보이도록 로드뷰의 적절한 시점(ViewPoint)을 설정합니다
    // Wizard를 사용하면 적절한 로드뷰 시점(ViewPoint)값을 쉽게 확인할 수 있습니다
    roadview.setViewpoint({
        pan: 321,
        tilt: 0,
        zoom: 0
    });

    // 지도 중심을 표시할 마커를 생성하고 특정 장소 위에 표시합니다
    mapMarker = new kakao.maps.Marker({
        position: placePosition,
        map: map
    });

    // 로드뷰 초기화가 완료되면
    kakao.maps.event.addListener(roadview, 'init', function() {

        // 로드뷰에 특정 장소를 표시할 마커를 생성하고 로드뷰 위에 표시합니다
        rvMarker = new kakao.maps.Marker({
            position: placePosition,
            map: roadview
        });
    });
}

// URL 파라미터에서 위도와 경도를 가져오는 함수입니다
function getUrlParams() {
    const params = new URLSearchParams(window.location.search);
    const lat = parseFloat(params.get('lat'));
    const lng = parseFloat(params.get('lng'));
    return { lat, lng };
}

// 지도와 로드뷰를 감싸고 있는 div의 class를 변경하여 지도를 숨기거나 보이게 하는 함수입니다
function toggleMap(active) {
    if (active) {
        // 지도가 보이도록 지도와 로드뷰를 감싸고 있는 div의 class를 변경합니다
        container.className = "view_map"
    } else {
        // 지도가 숨겨지도록 지도와 로드뷰를 감싸고 있는 div의 class를 변경합니다
        container.className = "view_roadview"
    }
}

// URL 파라미터로부터 초기 위치를 설정합니다
const { lat, lng } = getUrlParams();
if (!isNaN(lat) && !isNaN(lng)) {
    setPlacePosition(lat, lng);
} else {
    // URL에 파라미터가 없거나 잘못된 경우 기본 위치를 설정합니다
    setPlacePosition(33.450701, 126.570667);
}
// URL에서 title 파라미터 값을 가져오는 함수
function getTitleFromUrl() {
    const params = new URLSearchParams(window.location.search);
    return params.get('title');
}

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주어진 위도와 경도로 주소를 반환하는 함수
function getAddressFromCoords(lat, lng, callback) {
    var coords = new kakao.maps.LatLng(lat, lng);
    geocoder.coord2Address(coords.getLng(), coords.getLat(), function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            var detailAddr = !!result[0].road_address ? '도로명주소 : ' + result[0].road_address.address_name : '';
            detailAddr += ' 지번 주소 : ' + result[0].address.address_name;
            callback(detailAddr);
        } else {
            callback('주소를 찾을 수 없습니다.');
        }
    });
}

document.getElementById('submitComment').addEventListener('click', function() {
    var comment = document.getElementById('commentInput').value;

    if (comment.trim() === "") {
        alert("댓글을 입력하세요.");
        return;
    }

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/api/comments', true);
    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // 성공적으로 댓글이 저장되었을 때의 처리
            alert('댓글이 성공적으로 저장되었습니다.');
            document.getElementById('commentInput').value = ""; // 입력 필드 초기화
        } else if (xhr.readyState === 4) {
            // 오류가 발생했을 때의 처리
            alert('댓글 저장에 실패했습니다.');
        }
    };

    var data = JSON.stringify({ comment: comment });
    xhr.send(data);
});


// 페이지 로드 시 실행되는 함수
window.onload = function() {
    // URL에서 title을 가져옵니다
    const title = getTitleFromUrl();

    // 첫 번째 p 요소를 찾습니다
    const paragraphs = document.querySelectorAll('.card-body p');
    const paragraph1 = paragraphs[0];
    const paragraph2 = paragraphs[1];

    // title이 있다면 첫 번째 p 요소에 추가합니다
    if (title) {
        paragraph1.textContent = title;
    }

    // 위도와 경도를 URL 파라미터에서 가져옵니다
    const params = new URLSearchParams(window.location.search);
    const lat = parseFloat(params.get('lat'));
    const lng = parseFloat(params.get('lng'));

    // 위도와 경도가 유효한 경우 주소를 가져와 두 번째 p 요소에 추가합니다
    if (!isNaN(lat) && !isNaN(lng)) {
        getAddressFromCoords(lat, lng, function(address) {
            paragraph2.textContent = address;
        });
    } else {
        paragraph2.textContent = '유효한 주소를 찾을 수 없습니다.';
    }
};

