$(document).ready(function () {
    $(".recommend").click(function () {
        let currentImage = $(this).attr("src");
        let feedId = $(this).data("feed-id");
        let recommendCount = parseInt($(this).siblings(".recommend-count").text());
        let $recommend = $(this);

        if (currentImage === "/assets/hand-thumbs-up.svg") {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/1/feeds/" + feedId + "/recommends"
            })
                .done(function (result) {
                    $recommend.attr("src", "/assets/hand-thumbs-up-fill.svg");
                    $recommend.siblings(".recommend-count").text(recommendCount + 1);
                });
        } else if (currentImage === "/assets/hand-thumbs-up-fill.svg") {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/1/feeds/" + feedId + "/recommends"
            })
                .done(function (result) {
                    $recommend.attr("src", "/assets/hand-thumbs-up.svg");
                    $recommend.siblings(".recommend-count").text(recommendCount - 1);
                });
        }
    });
});

$(document).ready(function () {
    $(".bookmark").click(function () {
        let currentImage = $(this).attr("src");
        let feedId = $(this).data("feed-id");
        let bookmarkCount = parseInt($(this).siblings(".bookmark-count").text());
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