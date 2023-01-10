drop table if exists account;
drop table if exists comment;
drop table if exists feed;
drop table if exists hash_tag;
drop table if exists product;
drop table if exists reaction;
drop table if exists special_review;

create table account
(
    id         bigint       not null auto_increment,
    created_at datetime,
    updated_at datetime,
    email      varchar(255) not null,
    primary key (id)
);

create table comment
(
    id           bigint       not null auto_increment,
    created_at   datetime,
    updated_at   datetime,
    account_id   bigint,
    content      varchar(255) not null,
    report_count integer      not null,
    feed_id      bigint       not null,
    primary key (id)
);

create table feed
(
    id                 bigint not null auto_increment,
    created_at         datetime,
    updated_at         datetime,
    is_comment_blocked TINYINT(1),
    bookmark_count     bigint not null,
    recommend_count    bigint not null,
    review_id          bigint not null,
    primary key (id)
);

create table hash_tag
(
    id                bigint       not null auto_increment,
    created_at        datetime,
    updated_at        datetime,
    name              varchar(255) not null,
    special_review_id bigint       not null,
    primary key (id)
);

create table product
(
    id               bigint       not null auto_increment,
    created_at       datetime,
    updated_at       datetime,
--     description      varchar(255) not null,
    name             varchar(255) not null,
    image_url        varchar(255) not null,
    price            integer      not null,
    star_score       float        not null,
    discount_percent integer      not null,
    primary key (id)
);

create table recommend
(
    id             bigint not null auto_increment,
    created_at     datetime,
    updated_at     datetime,
    account_id     bigint,
    is_recommended TINYINT(1),
    feed_id        bigint not null,
    primary key (id)
);

create table bookmark
(
    id            bigint not null auto_increment,
    created_at    datetime,
    updated_at    datetime,
    account_id    bigint,
    is_bookmarked TINYINT(1),
    feed_id       bigint not null,
    primary key (id)
);

create table special_review
(
    special_review_id bigint       not null auto_increment,
    created_at        datetime,
    updated_at        datetime,
    cook_level        varchar(255) not null,
    cook_quantity     varchar(255) not null,
    cook_time         varchar(255) not null,
    description       varchar(255) not null,
    image_url         varchar(255) not null,
    account_id        bigint       not null,
    product_id        bigint       not null,
    star_score        float        not null,
    primary key (special_review_id)
);