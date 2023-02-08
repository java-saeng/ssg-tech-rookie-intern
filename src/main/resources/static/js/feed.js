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
                url: "http://localhost:8080/1/feeds/" + feedId + "/recommends"
            })
                .done(function (result) {
                    $recommend.siblings(".recommend-count").text(recommendCount + 1);
                    $recommend.attr("src", "/assets/hand-thumbs-up-fill.svg");
                });
        } else if (currentImage === "/assets/hand-thumbs-up-fill.svg") {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/1/feeds/" + feedId + "/recommends"
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
                url: "http://localhost:8080/1/feeds/" + feedId + "/bookmarks"
            })
                .done(function (result) {
                    $bookmark.siblings(".bookmark-count").text(bookmarkCount + 1);
                    $bookmark.attr("src", "/assets/bookmark-fill.svg");
                });
        } else if (currentImage === "/assets/bookmark-fill.svg") {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/1/feeds/" + feedId + "/bookmarks"
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

    let url = 'http://localhost:8080/1/feeds?';

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

        window.location.href = `http://localhost:8080/1/feeds?${queryParameter}`;
    }
)

let custom_hashtag = document.querySelector("#custom-hashtag");
custom_hashtag.addEventListener("click", function () {
    let target = document.getElementById("dropdown-input");
    target.value = "";
    target.focus();
    target.placeholder = "해시태그를 입력하세요!";
});

function goNext(id) {
    console.log(id);


    $('#description'+ id).carousel('next');
    $('#carousel'+id).carousel('next');
}

function goPrev(id) {
    console.log(id);


    $('#description'+ id).carousel('prev');
    $('#carousel'+id).carousel('prev');
}