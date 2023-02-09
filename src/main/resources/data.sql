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

-- feed 테이블

insert into feed
values (1, now(), now(), 0, 0, 1);

insert into feed
values (2, now(), now(), 0, 0, 2);

insert into feed
values (3, now(), now(), 0, 0, 3);

insert into feed
values (4, now(), now(), 0, 0, 4);

insert into feed
values (5, now(), now(), 0, 0, 5);

insert into feed
values (6, now(), now(), 0, 0, 6);

insert into feed
values (7, now(), now(), 0, 0, 7);

insert into feed
values (8, now(), now(), 0, 0, 8);

insert into feed
values (9, now(), now(), 0, 0, 9);

insert into feed
values (10, now(), now(), 0, 0, 10);

insert into feed
values (11, now(), now(), 0, 0, 11);

insert into feed
values (12, now(), now(), 0, 0, 12);

insert into feed
values (13, now(), now(), 0, 0, 13);

insert into feed
values (14, now(), now(), 0, 0, 14);

insert into feed
values (15, now(), now(), 0, 0, 15);

insert into feed
values (16, now(), now(), 0, 0, 16);

insert into feed
values (17, now(), now(), 0, 0, 17);

insert into feed
values (18, now(), now(), 0, 0, 18);

insert into feed
values (19, now(), now(), 0, 0, 19);

insert into feed
values (20, now(), now(), 0, 0, 20);

-- 해시 태그

insert into hash_tag
values (1, now(), now(), '간편요리', 1);

insert into hash_tag
values (2, now(), now(), 'SNS용', 1);

insert into hash_tag
values (3, now(), now(), '1인요리', 1);

insert into hash_tag
values (4, now(), now(), '매일매일', 2);

insert into hash_tag
values (5, now(), now(), '몸보신용', 4);

insert into hash_tag
values (6, now(), now(), '가족최애요리', 5);

insert into hash_tag
values (7, now(), now(), '인생요리', 6);

insert into hash_tag
values (8, now(), now(), '주말스페셜', 1);

insert into hash_tag
values (9, now(), now(), '손님대접용', 1);

insert into hash_tag
values (10, now(), now(), '생일파티용', 9);

insert into hash_tag
values (11, now(), now(), '간편요리', 2);

insert into hash_tag
values (12, now(), now(), '주말스페셜', 2);

insert into hash_tag
values (13, now(), now(), 'SNS용', 2);

insert into hash_tag
values (14, now(), now(), '1인요리', 2);

insert into hash_tag
values (15, now(), now(), '간편요리', 3);

insert into hash_tag
values (16, now(), now(), '인생요리', 4);

insert into hash_tag
values (17, now(), now(), '가족최애요리', 4);

insert into hash_tag
values (18, now(), now(), '주말스페셜', 4);

insert into hash_tag
values (19, now(), now(), '손님대접용', 4);


insert into hash_tag
values (20, now(), now(), '인생요리', 5);

insert into hash_tag
values (21, now(), now(), '간편요리', 5);

insert into hash_tag
values (22, now(), now(), '1인요리', 5);

insert into hash_tag
values (23, now(), now(), '주말스페셜', 5);

insert into hash_tag
values (24, now(), now(), '매일매일', 5);

insert into hash_tag
values (25, now(), now(), 'SNS용', 5);



-- 상품

insert into product
values (1, now(), now(), '[하림] 냉장 닭다리(북채) (500g)',
        'https://sitem.ssgcdn.com/14/83/80/item/2097000808314_i1_1100.jpg', 6675, 4.8, 25, 'https://www.ssg.com/item/itemView.ssg?itemId=2097000808314&siteNo=6001&salestrNo=2439');

insert into product
values (2, now(), now(), '[노브랜드]한입쏙쏙비엔나 550g',
        'https://sitem.ssgcdn.com/67/17/83/item/1000033831767_i1_1100.jpg', 5880, 4.9, 3, 'https://www.ssg.com/item/itemView.ssg?itemId=1000033831767&siteNo=6001&salestrNo=2439');

insert into product
values (3, now(), now(), '[노브랜드] 숯불 데리야끼 닭꼬치 800g',
        'https://sitem.ssgcdn.com/06/55/52/item/1000034525506_i1_550.jpg', 14980, 4.8, 0, 'ssg.com/item/itemView.ssg?itemId=1000034525506&siteNo=6001&salestrNo=2439');


-- 스페셜 리뷰

insert into special_review
values (1, now(), now(), 'MEDIUM', 'COUPLE', 'TWENTY',
        '닭다리 300g, 굴소스 3스푼, 올리고당 2스푼, 설탕 2스푼, 다진마늘 1스푼, 소금, 후추',
        '양념장을 만들어서 소금, 후추로 미리 염지한 닭다리에 고루 묻혀 에어프라이어에 구워주세요 (170도 15분, 15분)',
        '소금 후추로 미리 염지를 해둬요 양념장을 만들어서 골고루 발라줘요 에어프라이어에 구우면 완성 예쁘게 플레이팅하면 더 좋아요 맥주안주로 굿',
        1, 1, 5);

insert into special_review
values (2, now(), now(), 'EASY', 'SOLO', 'TWENTY',
        '비엔나 10개 스파게티면 1인분 올리브유 2T 다진마늘, 맛소금, 후추, 양파 반개 생크림 100ml(없으면 우유로) 우유 100ml 치킨스톡 1T',
        '1. 비엔나 1개에 스파게티를 4~5줄을 꽂은 후, 소금과 올리유를 넣고 8분간 삶는다. 2. 후라이팬에 다진마늘과 양파 반개를 볶다가, 1를 넣고 볶는다. (매콤한맛을 원하면 고추가루나 베트남고추를 첨가) (브로콜리, 새우, 버섯등을 기호에 맞게 넣어도 된다.) 3. 2에 치킨스톡 1T, 우유 200ml를 넣고 5분간 끓인다. 간은 맛소금으로 한다.',
        '아이들이 놀이도 하고 맛있게 먹은 면을 품은 비엔나 크림 스파게티로 직접 만들었다는 생각이여서 인지 더 맛있게 즐겁게 먹었답니다. 한번 도전해 보세요~',
        1, 2, 5);

insert into special_review
values (3, now(), now(), 'EASY', 'COUPLE', 'TWENTY',
        '밥도그 요리 재료: 비엔나소세지, 다진 당근, 다진 양파, 다진애호박, 계란 1알, 빵가루',
        '1. 비엔나 소세지를 먼저 구워주세요 2. 준비해 둔 다진 야채들을 볶아주세요(양파, 당근, 애호박) 3. 밥과 볶은 야채들을 섞어주세요 4. 가운에 비엔나 소제지를 넣고 동글게 말아주세요 5. 풀어 둔 계란 물을 묻힌 후 빵가루를 묻혀 주세요 6. 에어프라이어기에 14분 구워주면 완성',
        '# 간을 따로 하지 않아도 소세지 자체가 맛있어서 맛나요 애용하던 소세지에서 갈아 타야 겠어요 # 간식으로 먹기 좋아요',
        2, 2, 5);

insert into special_review
values (4, now(), now(), 'MEDIUM', 'COUPLE', 'THIRTY',
        '비엔나 소세지 15개, 마늘 3개, 양파 1/2개, 스파게티면 2인분, 스파게티소스 300g, 모짜렐라 치즈 200g, 파슬리가루 약간',
        '1. 스파게티면을 삶아줍니다. 2. 후라이팬에 기름을 두르고 마늘과 양파, 소세지를 넣고 볶아주세요. 3. 볶아놓은 재료에 삶은면과 소스를넣고 1분간 볶아줍니다. 4. 오븐용기에 스파게티를 담고 모짜렐라 치즈를 올려 오븐에서 익혀주세요.',
        '치즈가 노릇하게 구워지면 완성~ 연말 파티음식으로도 좋고 손님초대요리로도 좋은 오븐치즈파스타 입니다.',
        3, 2, 5);

insert into special_review
values (5, now(), now(), 'EASY', 'SOLO', 'THIRTY',
        '오야코동) 닭꼬치4 개,대파 한줌,마요네즈 적당량,노른자1알,후추,파슬리 ,쪽파 먹고싶은만큼',
        '1.밥먼저해두고요 2.팬에 대파가 수분이다날아갈때까지 볶아요 3.전자레인지에 돌린 닭꼬치를 젓가락으로 빼서 팬에 대파랑같이 볶아요',
        '밥을담고 그위에 볶은대파닭꼬치 가쓰오부시 계란노른자 마요네즈를 올려 취향껏 실파송송까지 해주면 완성!',
        3, 3, 5);

# insert into special_review
# values (4, now(), now(), 'MEDIUM', 'COUPLE', 'THIRTY',
#         '',
#         '',
#         '',
#         3, 2, 5);




-- image

insert into image
values (1, 'https://succ.ssgcdn.com/uphoto/202208/20220805101605_1178408979_1_1.jpg', 1, 'INGREDIENT');

insert into image
values (2, 'https://succ.ssgcdn.com/uphoto/202208/20220805101605_1178408979_2_1.jpg', 1, 'PROCESS');

insert into image
values (3, 'https://succ.ssgcdn.com/uphoto/202208/20220805101605_1178408979_3_1.jpg', 1, 'COMPLETE');


insert into image
values (4, 'https://succ.ssgcdn.com/uphoto/202212/20221224230723_1188648537_1_1.jpg', 2, 'INGREDIENT');

insert into image
values (5, 'https://succ.ssgcdn.com/uphoto/202212/20221224230723_1188648537_2_1.jpg', 2, 'PROCESS');

insert into image
values (6, 'https://succ.ssgcdn.com/uphoto/202212/20221224230723_1188648537_3_1.jpg', 2, 'COMPLETE');


insert into image
values (7, 'https://succ.ssgcdn.com/uphoto/202301/20230103160328_1189376325_1_1.jpg', 3, 'INGREDIENT');

insert into image
values (8, 'https://succ.ssgcdn.com/uphoto/202301/20230103160328_1189376325_2_1.jpg', 3, 'PROCESS');

insert into image
values (9, 'https://succ.ssgcdn.com/uphoto/202301/20230103160328_1189376325_3_1.jpg', 3, 'COMPLETE');


insert into image
values (10, 'https://succ.ssgcdn.com/uphoto/202212/20221205123532_1187457601_1_1.jpg', 4, 'INGREDIENT');

insert into image
values (11, 'https://succ.ssgcdn.com/uphoto/202212/20221205123532_1187457601_2_1.jpg', 4, 'PROCESS');

insert into image
values (12, 'https://succ.ssgcdn.com/uphoto/202212/20221205123532_1187457601_3_1.jpg', 4, 'COMPLETE');


insert into image
values (13, 'https://succ.ssgcdn.com/uphoto/202301/20230115160112_1190180944_1_1.jpg', 5, 'INGREDIENT');

insert into image
values (14, 'https://succ.ssgcdn.com/uphoto/202301/20230115160112_1190180944_2_3.jpg', 5, 'PROCESS');

insert into image
values (15, 'https://succ.ssgcdn.com/uphoto/202301/20230115160112_1190180944_3_1.jpg', 5, 'COMPLETE');

