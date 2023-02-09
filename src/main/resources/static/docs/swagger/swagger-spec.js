window.swaggerSpec={
  "openapi" : "3.0.1",
  "info" : {
    "title" : "restdocs-swagger API Documentation",
    "description" : "Spring Rest Docs with SwaggerUI",
    "version" : "0.0.1"
  },
  "servers" : [ {
    "url" : "http://localhost:8080"
  } ],
  "tags" : [ ],
  "paths" : {
    "/api/{account-id}/me" : {
      "get" : {
        "tags" : [ "api" ],
        "operationId" : "get_my_feed_api_test/test_get_my_feed_api/",
        "parameters" : [ {
          "name" : "account-id",
          "in" : "path",
          "description" : "account id",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-account-id-me486549215"
                },
                "examples" : {
                  "get_my_feed_api_test/test_get_my_feed_api/" : {
                    "value" : "[ {\n  \"imageUrl\" : \"https://succ.ssgcdn.com/uphoto/202208/20220805101605_1178408979_3_1.jpg\",\n  \"starScore\" : 5.0,\n  \"createdAt\" : \"2023-02-09T21:58:21\",\n  \"description\" : \"소금 후추로 미리 염지를 해둬요 양념장을 만들어서 골고루 발라줘요 에어프라이어에 구우면 완성 예쁘게 플레이팅하면 더 좋아요 맥주안주로 굿\",\n  \"recommendCount\" : 0,\n  \"bookmarkCount\" : 0,\n  \"specialReviewId\" : 1,\n  \"feedId\" : 1\n}, {\n  \"imageUrl\" : \"https://succ.ssgcdn.com/uphoto/202212/20221224230723_1188648537_3_1.jpg\",\n  \"starScore\" : 5.0,\n  \"createdAt\" : \"2023-02-09T21:58:21\",\n  \"description\" : \"아이들이 놀이도 하고 맛있게 먹은 면을 품은 비엔나 크림 스파게티로 직접 만들었다는 생각이여서 인지 더 맛있게 즐겁게 먹었답니다. 한번 도전해 보세요~\",\n  \"recommendCount\" : 0,\n  \"bookmarkCount\" : 0,\n  \"specialReviewId\" : 2,\n  \"feedId\" : 2\n} ]"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/{account-id}/me/thumbnails" : {
      "get" : {
        "tags" : [ "api" ],
        "operationId" : "get_thumbnail_api_test/test_get_thumbnail_api/",
        "parameters" : [ {
          "name" : "account-id",
          "in" : "path",
          "description" : "account id",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-account-id-me486549215"
                },
                "examples" : {
                  "get_thumbnail_api_test/test_get_thumbnail_api/" : {
                    "value" : "[ ]"
                  }
                }
              }
            }
          }
        }
      }
    }
  },
  "components" : {
    "schemas" : {
      "api-account-id-me486549215" : {
        "type" : "object"
      }
    }
  }
}