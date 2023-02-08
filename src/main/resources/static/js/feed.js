$(document).ready(function () {
    $(".recommend").click(function () {
        let currentImage = $(this).attr("src");
        let feedId = $(this).data("feed-id");
        let recommendCount = parseInt($(this).siblings(".recommend-count").text());
        let recommendFlag = $(this).data("flag");
        let $recommend = $(this);

        if (currentImage === "/assets/hand-thumbs-up.svg") {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/api/feeds/" + feedId + "/recommends",
                headers: {'Authorization': '1'}
            })
                .done(function (result) {
                    $recommend.siblings(".recommend-count").text(recommendCount + 1);
                    $recommend.attr("src", "/assets/hand-thumbs-up-fill.svg");
                });
        } else if (currentImage === "/assets/hand-thumbs-up-fill.svg") {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/api/feeds/" + feedId + "/recommends",
                headers: {'Authorization': '1'}
            })
                .done(function (result) {
                    $recommend.siblings(".recommend-count").text(recommendCount - 1);
                    $recommend.attr("src", "/assets/hand-thumbs-up.svg");
                });
        }
    });
});

$(document).ready(function () {
    $(".bookmark").click(function () {
        let currentImage = $(this).attr("src");
        let feedId = $(this).data("feed-id");
        let bookmarkCount = parseInt($(this).siblings(".bookmark-count").text());
        let bookmarkFlag = $(this).data("flag");
        let $bookmark = $(this);

        if (currentImage === "/assets/bookmark.svg") {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/api/feeds/" + feedId + "/bookmarks",
                headers: {'Authorization': '1'}
            })
                .done(function (result) {
                    $bookmark.siblings(".bookmark-count").text(bookmarkCount + 1);
                    $bookmark.attr("src", "/assets/bookmark-fill.svg");
                });
        } else if (currentImage === "/assets/bookmark-fill.svg") {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/api/feeds/" + feedId + "/bookmarks",
                headers: {'Authorization': '1'}
            })
                .done(function (result) {
                    $bookmark.siblings(".bookmark-count").text(bookmarkCount - 1);
                    $bookmark.attr("src", "/assets/bookmark.svg");
                });
        }
    });
});

let dropdownInput = document.getElementById("dropdown-input");
let dropdownMenu = document.getElementById("dropdown-menu");
dropdownInput.addEventListener("click", function () {
    if (dropdownMenu.style.display === "none") {
        dropdownMenu.style.display = "block";
    } else {
        dropdownMenu.style.display = "none";
    }
});


function setValue(li) {
    dropdownInput.value = li.innerText;
}


function submitSearch(event) {
    event.preventDefault();
    const input = document.getElementById("dropdown-input");

    let url = 'http://localhost:8080/feeds?';

    if (input.value === "해시태그 입력") {
        input.placeholder = '해시태그 입력';
    }

    if (input.value !== "" && input.value !== "해시태그 입력") {
        url += 'hashTag=' + input.value;
    }

    window.location.href = url;
}

function submitSearchSSG(event) {
    event.preventDefault();
    const input = document.getElementById("ssg-search");

    let url = 'https://m.ssg.com/search.ssg?query=';

    if (input.value !== "") {
        url += input.value;
    }

    window.location.href = url;
}

function sorting(event) {
    event.preventDefault();

    let currentURL = window.location.href;
    const paramsMap = getParamsAsMap(new URL(currentURL));

    let sortingValue = event.target.innerText;
    let sortingCondition = sortingValue === '최신순' ? 'NEWER' : 'RECOMMENDATION';

    paramsMap.set("sort", sortingCondition);

    let newURL = currentURL.split("?")[0] + "?";

    for (const [key, value] of paramsMap) {
        newURL += key + "=" + value + "&";
    }
    newURL = newURL.substring(0, newURL.length - 1);

    window.location.href = newURL;
}

function getParamsAsMap(url) {
    const params = new URLSearchParams(url.search);
    const paramsMap = new Map();
    for (const [key, value] of params) {
        paramsMap.set(key, value);
    }
    return paramsMap;
}

const cookMap = new Map();

cookMap.set('10분 미만', 'TEN');
cookMap.set('10분~20분', 'TWENTY');
cookMap.set('30분', 'THIRTY');
cookMap.set('1시간', 'ONE_HOUR');
cookMap.set('2시간 이상', 'TWO_HOURS');

cookMap.set('쉬워요', 'EASY');
cookMap.set('보통예요', 'MEDIUM');
cookMap.set('어려워요', 'HARD');

cookMap.set('1인', 'SOLO');
cookMap.set('2~3인', 'COUPLE');
cookMap.set('3~4인', 'FAMILY');
cookMap.set('5~10인', 'PARTY');
cookMap.set('10인 이상', 'FESTIVAL');

const cookInfo = {};

document.getElementById("cookQuantity").addEventListener(
    "click", e => {

        const chk = e.target.style.color === 'red' ? true : false;

        let ss = document.getElementsByClassName("cookQuantity-li");
        for (let i = 0; i < ss.length; i++) {
            ss[i].style.color = 'black';
        }

        if (chk === false) {
            e.target.style.color = 'red';
        }

        let value = '';
        if (e.target.text === '1인') {
            value = 'SOLO';
        } else if (e.target.text === '2~3인') {
            value = 'COUPLE';
        } else if (e.target.text === '3~4인') {
            value = 'FAMILY';
        } else if (e.target.text === '5~10인') {
            value = 'PARTY';
        } else if (e.target.text === '10인 이상') {
            value = 'FESTIVAL';
        }

        if (e.target.style.color === 'red') {
            cookInfo["cookQuantity"] = value;
        } else {
            delete cookInfo["cookQuantity"];
        }

    }
);

document.getElementById("cookLevel").addEventListener(
    "click", e => {

        const chk = e.target.style.color === 'red' ? true : false;

        let ss = document.getElementsByClassName("cookLevel-li");
        for (let i = 0; i < ss.length; i++) {
            ss[i].style.color = 'black';
        }

        if (chk === false) {
            e.target.style.color = 'red';
        }

        let value = '';

        if (e.target.text === '쉬워요') {
            value = 'EASY';
        } else if (e.target.text === '보통예요') {
            value = 'MEDIUM';
        } else if (e.target.text === '어려워요') {
            value = 'HARD';
        }

        if (e.target.style.color === 'red') {
            cookInfo["cookLevel"] = value;
        } else {
            delete cookInfo["cookLevel"];
        }

    }
);

document.getElementById("cookTime").addEventListener(
    "click", e => {

        const chk = e.target.style.color === 'red' ? true : false;

        let ss = document.getElementsByClassName("cookTime-li");
        for (let i = 0; i < ss.length; i++) {
            ss[i].style.color = 'black';
        }

        if (chk === false) {
            e.target.style.color = 'red';
        }

        let value = '';
        if (e.target.text === '10분 미만') {
            value = 'TEN';
        } else if (e.target.text === '10분~20분') {
            value = 'TWENTY';
        } else if (e.target.text === '30분') {
            value = 'THIRTY';
        } else if (e.target.text === '1시간') {
            value = 'ONE_HOUR';
        } else if (e.target.text === '2시간 이상') {
            value = 'TWO_HOURS';
        }

        if (e.target.style.color === 'red') {
            cookInfo["cookTime"] = value;
        } else {
            delete cookInfo["cookTime"];
        }

    }
);


document.getElementById("filter").addEventListener(
    "click", e => {

        let currentURL = window.location.href;
        const paramsMap = getParamsAsMap(new URL(currentURL));

        for (const [key, value] of paramsMap) {

            if (key === 'cookLevel') {
                let cookLevelCandidate = document.getElementsByClassName("cookLevel-li");

                for (let i = 0; i < cookLevelCandidate.length; i++) {

                    if (cookMap.get(cookLevelCandidate[i].text) === value) {

                        cookLevelCandidate[i].style.color = 'red';
                        cookInfo['cookLevel'] = value;

                    }
                }

            } else if (key === 'cookTime') {

                let cookTimeCandidate = document.getElementsByClassName("cookTime-li");

                for (let i = 0; i < cookTimeCandidate.length; i++) {

                    if (cookMap.get(cookTimeCandidate[i].text) === value) {
                        cookTimeCandidate[i].style.color = 'red';
                        cookInfo['cookTime'] = value;
                    }
                }

            } else if (key === 'cookQuantity') {

                let cookQuantityCandidate = document.getElementsByClassName("cookQuantity-li");

                for (let i = 0; i < cookQuantityCandidate.length; i++) {

                    if (cookMap.get(cookQuantityCandidate[i].text) === value) {
                        cookQuantityCandidate[i].style.color = 'red';
                        cookInfo['cookQuantity'] = value;
                    }
                }
            }
        }
    }
);


document.getElementById("filterButton").addEventListener(
    "click", e => {

        const input = document.getElementById("dropdown-input");

        if (input.value === "해시태그 입력") {
            input.placeholder = '해시태그 입력';
        }

        if (input.value !== "" && input.value !== "해시태그 입력") {
            cookInfo["hashTag"] = input.value;
        }


        let count = 0;
        let queryParameter = '';

        for (const [key, value] of Object.entries(cookInfo)) {
            queryParameter += key + '=' + value;

            if (count < Object.keys(cookInfo).length - 1) {
                queryParameter += '&';
            }
            count++;
        }

        window.location.href = `http://localhost:8080/feeds?${queryParameter}`;
    }
)

let custom_hashtag = document.querySelector("#custom-hashtag");
custom_hashtag.addEventListener("click", function () {
    let target = document.getElementById("dropdown-input");
    target.value = "";
    target.focus();
    target.placeholder = "해시태그를 입력하세요!";
});

$(document).ready(function(){
    $('.bxslider').bxSlider( {
        mode: 'horizontal',// 가로 방향 수평 슬라이드
        speed: 500,        // 이동 속도를 설정
        pager: false,      // 현재 위치 페이징 표시 여부 설정
        moveSlides: 1,     // 슬라이드 이동시 개수
        slideWidth: 100,   // 슬라이드 너비
        minSlides: 4,      // 최소 노출 개수
        maxSlides: 4,      // 최대 노출 개수
        slideMargin: 5,    // 슬라이드간의 간격
        auto: true,        // 자동 실행 여부
        autoHover: true,   // 마우스 호버시 정지 여부
        controls: false    // 이전 다음 버튼 노출 여부
    });
});


const slides = document.querySelector('.slides'); //전체 슬라이드 컨테이너
const slideImg = document.querySelectorAll('.slides li'); //모든 슬라이드들
let currentIdx = 0; //현재 슬라이드 index
const slideCount = slideImg.length; // 슬라이드 개수
const prev = document.querySelector('.prev'); //이전 버튼
const next = document.querySelector('.next'); //다음 버튼
const slideWidth = 300; //한개의 슬라이드 넓이
const slideMargin = 100; //슬라이드간의 margin 값

//전체 슬라이드 컨테이너 넓이 설정
slides.style.width = (slideWidth + slideMargin) * slideCount + 'px';

function moveSlide(num) {
    slides.style.left = -num * 400 + 'px';
    currentIdx = num;
}

prev.addEventListener('click', function () {
    /*첫 번째 슬라이드로 표시 됐을때는
    이전 버튼 눌러도 아무런 반응 없게 하기 위해
    currentIdx !==0일때만 moveSlide 함수 불러옴 */

    if (currentIdx !== 0) moveSlide(currentIdx - 1);
});

next.addEventListener('click', function () {
    /* 마지막 슬라이드로 표시 됐을때는
    다음 버튼 눌러도 아무런 반응 없게 하기 위해
    currentIdx !==slideCount - 1 일때만
    moveSlide 함수 불러옴 */
    if (currentIdx !== slideCount - 1) {
        moveSlide(currentIdx + 1);
    }
});

// 이미지 슬라이드
window.onload = function() {
    const kindWrap =  document.querySelector('.kind_wrap');
    const slider = kindWrap.querySelector('.slider');
    const slideLis = slider.querySelectorAll('li')
    const moveButton = kindWrap.querySelector('.arrow');

    /* ul 넓이 계산해 주기 */
    const liWidth = slideLis[0].clientWidth;
    const sliderWidth = liWidth * slideLis.length;
    slider.style.width = `${sliderWidth}px` ;

    /* 리스너 설치하기 */
    let currentIdx = 0; // 슬라이드 현재 번호
    let translate = 0; // 슬라이드 위치 값
    moveButton.addEventListener('click', moveSlide);

    function moveSlide(event) {
        event.preventDefault();
        if (event.target.className === 'next') {
            if (currentIdx !== slideLis.length -1) {
                translate -= liWidth;
                slider.style.transform = `translateX(${translate}px)`;
                currentIdx += 1;
            }
        } else if (event.target.className === 'prev') {
            if (currentIdx !== 0) {
                translate += liWidth;
                slider.style.transform = `translateX(${translate}px)`;
                currentIdx -= 1;
            }
        }
    }
}