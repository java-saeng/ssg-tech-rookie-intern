-- 회원

insert into account
values (1, now(), now(), 'gusdn@ssg.com');

insert into account
values (2, now(), now(), 'wldnjs@ssg.com');

insert into account
values (3, now(), now(), 'dPqls@ssg.com');

insert into account
values (4, now(), now(), 'gurwns@ssg.com');

insert into account
values (5, now(), now(), 'tkdqur@ssg.com');

insert into account
values (6, now(), now(), 'tjgus@ssg.com');

insert into account
values (7, now(), now(), 'alfla@ssg.com');

-- 상품

insert into product
values (1, now(), now(), '맛있는 돼지고기',
        'https://sitem.ssgcdn.com/99/43/13/item/1000337134399_i1_1100.jpg', 10000, 4.0, 10);

insert into product
values (2, now(), now(), '맛있는 소고기',
        'https://sitem.ssgcdn.com/78/31/05/item/1000020053178_i1_1100.jpg', 20000, 4.1, 20);

insert into product
values (3, now(), now(), '맛있는 닭고기',
        'https://sitem.ssgcdn.com/30/11/65/item/1000018651130_i1_1100.jpg', 5420, 4.2, 30);

insert into product
values (4, now(), now(), '싱싱한 새우',
        'https://sitem.ssgcdn.com/85/29/37/item/1000432372985_i1_550.jpg', 3390, 4.3, 40);

insert into product
values (5, now(), now(), '제주에서 막 잡은 전복',
        'https://sitem.ssgcdn.com/30/18/63/item/1000057631830_i1_1100.jpg', 34610, 4.4, 50);

insert into product
values (6, now(), now(), '방금 막 딴 샤인머스켓',
        'https://sitem.ssgcdn.com/92/13/26/item/2097001261392_i1_1100.jpg', 19950, 4.5, 40);

insert into product
values (7, now(), now(), '논산 딸기',
        'https://sitem.ssgcdn.com/81/55/89/item/2097000895581_i1_1100.jpg', 13860, 4.6, 30);

insert into product
values (8, now(), now(), '등푸른 고등어',
        'https://sitem.ssgcdn.com/65/85/06/item/2097000068565_i1_1100.jpg', 33950, 4.7, 20);

insert into product
values (9, now(), now(), '방금 잡은 오징어',
        'https://sitem.ssgcdn.com/18/42/61/item/0000010614218_i1_1100.jpg', 19500, 4.8, 10);

insert into product
values (10, now(), now(), '마장동 한우 육회',
        'https://sitem.ssgcdn.com/69/83/39/item/1000065398369_i1_1100.jpg', 57900, 4.9, 5);

-- 스페셜 리뷰

insert into special_review
values (1, now(), now(), 'EASY', 'SOLO', 'TEN', '참기름을 둘러주세요',
        'https://succ.ssgcdn.com/uphoto/202212/20221217181937_1188238994_2_2.jpg', 2, 1, 3.5);
insert into special_review
values (2, now(), now(), 'MEDIUM', 'COUPLE', 'TWENTY', '달달 볶아주세요',
        'https://succ.ssgcdn.com/uphoto/202212/20221214202412_1188070189_3_2.jpg', 2, 1, 3.6);
insert into special_review
values (3, now(), now(), 'HARD', 'FAMILY', 'THIRTY', '고춧가루 팍팍',
        'https://succ.ssgcdn.com/uphoto/202212/20221203102228_1187313220_3_1.jpg', 2, 1, 3.7);
insert into special_review
values (4, now(), now(), 'EASY', 'PARTY', 'ONE_HOUR', '구워먹으면 맛있어용',
        'https://succ.ssgcdn.com/uphoto/202209/20220927203329_1182361564_3_1.jpg', 2, 1, 3.8);
insert into special_review
values (5, now(), now(), 'MEDIUM', 'FESTIVAL', 'TWO_HOURS', '최고의 레시피',
        'https://succ.ssgcdn.com/uphoto/202301/20230107122916_1189667038_3_1.jpg', 3, 2, 3.9);

insert into special_review
values (6, now(), now(), 'HARD', 'SOLO', 'TEN', '소고기는 이렇게 드셔보세요',
        'https://succ.ssgcdn.com/uphoto/202211/20221103164133_1185167240_3_1.jpeg', 3, 2, 4.0);
insert into special_review
values (7, now(), now(), 'EASY', 'COUPLE', 'TWENTY', '닭가슴살도 퍽퍽하지 않아!',
        'https://succ.ssgcdn.com/uphoto/202212/20221224163419_1188633964_3_1.jpeg', 4, 3, 4.1);
insert into special_review
values (8, now(), now(), 'MEDIUM', 'FAMILY', 'THIRTY', '새우가 빨개지면 드세요',
        'https://succ.ssgcdn.com/uphoto/202301/20230105160939_1189546552_3_1.jpg', 4, 4, 4.2);
insert into special_review
values (9, now(), now(), 'HARD', 'PARTY', 'ONE_HOUR', '이렇게 하면 우리 아이도 새우를 잘 먹어요',
        'https://succ.ssgcdn.com/uphoto/202301/20230103195103_1189393996_3_1.jpg', 4, 4, 4.3);
insert into special_review
values (10, now(), now(), 'EASY', 'FESTIVAL', 'TWO_HOURS', '새우로 사랑도 잡으새우',
        'https://succ.ssgcdn.com/uphoto/202211/20221115125456_1186049795_3_1.jpg', 1, 4, 4.4);

insert into special_review
values (11, now(), now(), 'MEDIUM', 'SOLO', 'TEN', '몸보신엔 전복이쥬',
        'https://succ.ssgcdn.com/uphoto/202212/20221223225631_1188605472_0_1.jpg', 1, 5, 4.5);

insert into special_review
values (12, now(), now(), 'HARD', 'COUPLE', 'TWENTY', '싱싱한 제철 전복, 이렇게 드셔보세요!',
        'https://succ.ssgcdn.com/uphoto/202107/20210727164712_1140217068_1.jpg', 5, 5, 4.6);

insert into special_review
values (13, now(), now(), 'EASY', 'FAMILY', 'THIRTY', '샤인머스켓 그냥 먹어도 꿀맛이지롱',
        'https://succ.ssgcdn.com/uphoto/202301/20230103193806_1189392950_0_1.jpg', 5, 6, 4.7);

insert into special_review
values (14, now(), now(), 'MEDIUM', 'PARTY', 'ONE_HOUR', '노릇노릇하게 고등어~!',
        'https://succ.ssgcdn.com/uphoto/202204/20220404203214_1167121259_1.jpg', 6, 8, 4.8);

insert into special_review
values (15, now(), now(), 'HARD', 'FESTIVAL', 'TWO_HOURS', '고등어야~ 고마워~',
        'https://sitem.ssgcdn.com/02/02/08/item/1000037080202_i3_550.jpg', 6, 8, 4.9);

insert into special_review
values (16, now(), now(), 'EASY', 'SOLO', 'TEN', '오징어랑 오징어먹기',
        'https://succ.ssgcdn.com/uphoto/202211/20221105080508_1185305559_3_1.jpg', 6, 9, 5.0);

insert into special_review
values (17, now(), now(), 'MEDIUM', 'COUPLE', 'TWENTY', '최고의 술안주는 오징어지요',
        'https://succ.ssgcdn.com/uphoto/202210/20221022160912_1184246126_0_1.jpg', 7, 9, 4.9);

insert into special_review
values (18, now(), now(), 'HARD', 'FAMILY', 'THIRTY', '오징어는 이렇게 드세요',
        'https://succ.ssgcdn.com/uphoto/202210/20221029082911_1184649840_0_1.jpg', 7, 9, 4.8);

insert into special_review
values (19, now(), now(), 'EASY', 'PARTY', 'ONE_HOUR', '육회 그냥 먹어도 맛있어',
        'https://sitem.ssgcdn.com/69/83/39/item/1000065398369_i1_1100.jpg', 7, 10, 4.7);

insert into special_review
values (20, now(), now(), 'MEDIUM', 'FESTIVAL', 'TWO_HOURS', '사랑아 육회해',
        'https://sitem.ssgcdn.com/99/68/03/item/1000057036899_i1_1100.jpg', 7, 10, 4.6);

-- feed 테이블

insert into feed
values (1, now(), now(), 0, 0, 0, 1);

insert into feed
values (2, now(), now(), 0, 0, 0, 2);

insert into feed
values (3, now(), now(), 0, 0, 0, 3);

insert into feed
values (4, now(), now(), 0, 0, 0, 4);

insert into feed
values (5, now(), now(), 0, 0, 0, 5);

insert into feed
values (6, now(), now(), 0, 0, 0, 6);

insert into feed
values (7, now(), now(), 0, 0, 0, 7);

insert into feed
values (8, now(), now(), 0, 0, 0, 8);

insert into feed
values (9, now(), now(), 0, 0, 0, 9);

insert into feed
values (10, now(), now(), 0, 0, 0, 10);

insert into feed
values (11, now(), now(), 0, 0, 0, 11);

insert into feed
values (12, now(), now(), 0, 0, 0, 12);

insert into feed
values (13, now(), now(), 0, 0, 0, 13);

insert into feed
values (14, now(), now(), 0, 0, 0, 14);

insert into feed
values (15, now(), now(), 0, 0, 0, 15);

insert into feed
values (16, now(), now(), 0, 0, 0, 16);

insert into feed
values (17, now(), now(), 0, 0, 0, 17);

insert into feed
values (18, now(), now(), 0, 0, 0, 18);

insert into feed
values (19, now(), now(), 0, 0, 0, 19);

insert into feed
values (20, now(), now(), 1, 0, 0, 20);

-- 해시 태그

insert into hash_tag
values (1, now(), now(), '몸보신', 1);

insert into hash_tag
values (2, now(), now(), '좋아', 1);

insert into hash_tag
values (3, now(), now(), '맛있어', 2);

insert into hash_tag
values (4, now(), now(), '멋있어', 3);

insert into hash_tag
values (5, now(), now(), '1인요리', 4);

insert into hash_tag
values (6, now(), now(), '가족요리', 5);

insert into hash_tag
values (7, now(), now(), '모임요리', 6);

insert into hash_tag
values (8, now(), now(), '취미', 7);

insert into hash_tag
values (9, now(), now(), '특기', 8);

insert into hash_tag
values (10, now(), now(), '맛있어', 9);

insert into hash_tag
values (11, now(), now(), '쉬워', 10);

insert into hash_tag
values (12, now(), now(), '어려워', 11);

insert into hash_tag
values (13, now(), now(), '화려해', 12);

insert into hash_tag
values (14, now(), now(), 'SNS용', 13);

insert into hash_tag
values (15, now(), now(), '맛도리', 14);

insert into hash_tag
values (16, now(), now(), '백종원', 15);

insert into hash_tag
values (17, now(), now(), '레시피', 16);

insert into hash_tag
values (18, now(), now(), '이연복', 17);

insert into hash_tag
values (19, now(), now(), '쓱쉐프', 18);

insert into hash_tag
values (20, now(), now(), '방구석', 19);

insert into comment
values (1, now(), now(), 1, '댓글1', 0, 20);

insert into comment
values (2, now(), now(), 2, '댓글2', 0, 20);

insert into comment
values (3, now(), now(), 2, '너무 맛있겠네요~', 0, 11);

insert into hash_tag
values (21, now(), now(), '전복', 11);