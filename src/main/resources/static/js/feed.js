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

    console.log(input.value);

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

const cookInfo = {};

document.getElementById("cookQuantity").addEventListener(
    "click", e => {

        const chk = e.target.style.color==='red' ? true : false;

        let ss = document.getElementsByClassName("cookQuantity-li");
        for (let i = 0; i < ss.length; i++) {
            ss[i].style.color = 'black';
        }

        if(chk===false) {
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
        cookInfo["cookQuantity"] = value;

        console.log(cookInfo);

    }
);

document.getElementById("cookLevel").addEventListener(
    "click", e => {

        const chk = e.target.style.color==='red' ? true : false;

        let ss = document.getElementsByClassName("cookLevel-li");
        for (let i = 0; i < ss.length; i++) {
            ss[i].style.color = 'black';
        }

        if(chk===false) {
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

        cookInfo["cookLevel"] = value;
        console.log(cookInfo);

    }
);

document.getElementById("cookTime").addEventListener(
    "click", e => {

        const chk = e.target.style.color==='red' ? true : false;

        let ss = document.getElementsByClassName("cookTime-li");
        for (let i = 0; i < ss.length; i++) {
            ss[i].style.color = 'black';
        }

        if(chk===false) {
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

        cookInfo["cookTime"] = value;
        console.log(cookInfo);

    }
);


document.getElementById("filterButton").addEventListener(
    "click", e => {

        const input = document.getElementById("dropdown-input");


        if (input.value !== "") {
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
