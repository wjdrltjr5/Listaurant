// 마커를 담을 배열입니다
let markers = [];

let mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 4// 지도의 확대 레벨
    };

// 지도를 생성합니다
let map = new kakao.maps.Map(mapContainer, mapOption);

// 장소 검색 객체를 생성합니다
let ps = new kakao.maps.services.Places();

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
let infowindow = new kakao.maps.InfoWindow({zIndex:1});
let c = null;
setCurrentPosition();
function setCurrentPosition(){
    // 현재 위치를 가져와서 지도를 해당 위치로 이동합니다
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            let lat = position.coords.latitude, // 위도
                lon = position.coords.longitude; // 경도

            let locPosition = new kakao.maps.LatLng(lat, lon);

            // 마커와 인포윈도우를 표시합니다
            displayMarker(locPosition, null);

            if(c != null){
                c.setMap(null);
            }
            // 지도에 표시할 원을 생성합니다
            c = new kakao.maps.Circle({
                center : new kakao.maps.LatLng(lat, lon),  // 원의 중심좌표 입니다
                radius: 300, // 미터 단위의 원의 반지름입니다
                strokeWeight: 5, // 선의 두께입니다
                strokeColor: '#75B8FA', // 선의 색깔입니다
                strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                strokeStyle: 'dashed', // 선의 스타일 입니다
                fillColor: '#CFE7FF', // 채우기 색깔입니다
                fillOpacity: 0.5  // 채우기 불투명도 입니다
            });

            c.setMap(map);

            ps.keywordSearch('맛집', placesSearchCB, {
                radius : 300,
                location: new kakao.maps.LatLng(lat, lon)
            });

            // 지도 중심좌표를 접속위치로 변경합니다
            map.setCenter(locPosition);
        });
    } else {
        alert('위치 정보를 사용할 수 없습니다.');
    }
}

// 지도에 마커와 인포윈도우를 표시하는 함수입니다
function displayMarker(locPosition, message) {
    let marker = new kakao.maps.Marker({
        map: map,
        position: locPosition
    });

    let iwContent = message, // 인포윈도우에 표시할 내용
        iwRemoveable = true;

    // 인포윈도우를 생성합니다
    let infowindow = new kakao.maps.InfoWindow({
        content: iwContent,
        removable: iwRemoveable
    });

    // 인포윈도우를 마커위에 표시합니다
    //infowindow.open(map, marker);

    // 지도 중심좌표를 접속위치로 변경합니다
    map.setCenter(locPosition);
}

// 키워드 검색을 요청하는 함수입니다
function searchPlaces() {
    let keyword = document.getElementById('keyword').value;

    if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요!');
        return false;
    }

    ps.keywordSearch(keyword, placesSearchCB);
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        displayPlaces(data);
        displayPagination(pagination);

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다.');
        return;

    } else if (status === kakao.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다.');
        return;

    }
}
var selectedmarker = null;
// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {
    let listEl = document.getElementById('placesList'),
        menuEl = document.getElementById('menu_wrap'),
        fragment = document.createDocumentFragment(),
        bounds = new kakao.maps.LatLngBounds(),
        listStr = '';

    removeAllChildNods(listEl);
    removeMarker();

    for (let i=0; i<places.length; i++) {

        let placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
            marker = addMarker(placePosition, i),
            itemEl = getListItem(i, places[i]);

        bounds.extend(placePosition);

        (function(marker, title) {
            kakao.maps.event.addListener(marker, 'mouseover', function() {
                displayInfowindow(marker, title);
            });

            //클릭했을 때 게시판 페이지로 이동
            kakao.maps.event.addListener(marker, 'onclick', function() {
                if (selectedmarker == null && selectedmarker !== marker) {
                    selectedmarker = marker;
                }
                displayInfowindow(marker, title);
            });

            /*kakao.maps.event.addListener(marker, 'mouseout', function() {
                infowindow.close();
            });*/

            itemEl.onclick = function () {
                map.setCenter(placePosition);
            };
        })(marker, places[i].place_name);

        fragment.appendChild(itemEl);
    }

    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    map.setBounds(bounds);
}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {
    let el = document.createElement('li'),
        itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
            '<div class="info">' +
            '   <h5>' + places.place_name + '</h5>';

    if (places.road_address_name) {
        itemStr += '    <span>' + places.road_address_name + '</span>' +
            '   <span class="jibun gray">' + places.address_name + '</span>';
    } else {
        itemStr += '    <span>' + places.address_name + '</span>';
    }

    itemStr += '  <span class="tel">' + places.phone + '</span>' +
        '</div>';

    el.innerHTML = itemStr;
    el.className = 'item';

    return el;
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
    let imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png',
        imageSize = new kakao.maps.Size(36, 37),
        imgOptions =  {
            spriteSize : new kakao.maps.Size(36, 691),
            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10),
            offset: new kakao.maps.Point(13, 37)
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
        marker = new kakao.maps.Marker({
            position: position,
            image: markerImage
        });

    marker.setMap(map);
    markers.push(marker);

    return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
    for (let i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
    let paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i;

    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild (paginationEl.lastChild);
    }

    for (i=1; i<=pagination.last; i++) {
        let el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i===pagination.current) {
            el.className = 'on';
        } else {
            el.onclick = (function(i) {
                return function() {
                    pagination.gotoPage(i);
                }
            })(i);
        }

        fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
function displayInfowindow(marker, title) {
    // marker의 위치를 가져옵니다.
    let position = marker.getPosition();
    let lat = position.getLat();
    let lng = position.getLng();

    // title과 marker의 위치를 URL 파라미터로 포함한 URL을 생성합니다.
    let url = `/board?title=${encodeURIComponent(title)}&lat=${lat}&lng=${lng}`;

    // 인포윈도우에 들어갈 HTML 콘텐츠를 생성합니다.
    let content = `
        <form action="/board" method="post">
            <div style="padding:5px;z-index:1;" onclick="location.href='${url}'">
                ${title}
            </div>
        </form>`;

    // 인포윈도우에 콘텐츠를 설정하고, 지도 위에 마커와 함께 표시합니다.
    infowindow.setContent(content);
    infowindow.open(map, marker);
}


// 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {
    while (el.hasChildNodes()) {
        el.removeChild (el.lastChild);
    }
}
