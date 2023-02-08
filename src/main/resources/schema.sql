drop table if exists account;
drop table if exists feed;
drop table if exists hash_tag;
drop table if exists product;
drop table if exists bookmark;
drop table if exists special_review;
drop table if exists recommend;
drop table if exists image;

create table account
(
    id         bigint       not null auto_increment,
    created_at datetime,
    updated_at datetime,
    email      varchar(255) not null,
    primary key (id)
);

create table feed
(
    id                bigint not null auto_increment,
    created_at        datetime,
    updated_at        datetime,
    bookmark_count    bigint not null,
    recommend_count   bigint not null,
    special_review_id bigint not null,
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
    name             varchar(255) not null,
    image_url        varchar(255) not null,
    price            integer      not null,
    star_score       float        not null,
    discount_percent integer      not null,
    product_info_url varchar(255) not null,
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
    special_review_id      bigint       not null auto_increment,
    created_at             datetime,
    updated_at             datetime,
    cook_level             varchar(255) not null,
    cook_quantity          varchar(255) not null,
    cook_time              varchar(255) not null,
    description_ingredient varchar(255),
    description_process    varchar(255),
    description_complete   varchar(255),
    account_id             bigint       not null,
    product_id             bigint       not null,
    star_score             float        not null,
    primary key (special_review_id)
);

create table image
(
    image_id          bigint       not null auto_increment,
    image_url         varchar(255) not null,
    special_review_id bigint       not null,
    cook_step         enum ('INGREDIENT', 'PROCESS', 'COMPLETE'),
    primary key (image_id)
);