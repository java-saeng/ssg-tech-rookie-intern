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
    "/api/{account-id}/feeds" : {
      "get" : {
        "tags" : [ "api" ],
        "operationId" : "search_feed_api_test/test_plus_recommend_status_ok/",
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
                  "$ref" : "#/components/schemas/api-account-id-feeds-feed-id486549215"
                },
                "examples" : {
                  "search_feed_api_test/test_plus_recommend_status_ok/" : {
                    "value" : "[ ]"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/{account-id}/feeds/{feed-id}" : {
      "get" : {
        "tags" : [ "api" ],
        "operationId" : "search_single_feed_api_test/test_search_one_feed_status_ok/",
        "parameters" : [ {
          "name" : "account-id",
          "in" : "path",
          "description" : "account id",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "feed-id",
          "in" : "path",
          "description" : "feed id",
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
                  "$ref" : "#/components/schemas/api-account-id-feeds-feed-id486549215"
                },
                "examples" : {
                  "search_single_feed_api_test/test_search_one_feed_status_ok/" : {
                    "value" : "{\n  \"feedReactionProfile\" : {\n    \"bookmarkCount\" : 0,\n    \"recommendCount\" : 0,\n    \"feedId\" : 1,\n    \"recommended\" : false,\n    \"bookmarked\" : false\n  },\n  \"productProfile\" : {\n    \"name\" : \"[냉장/호주산] 블랙타이 블랙앵거스 윗등심 스테이크 250g\",\n    \"imageUrl\" : \"https://sitem.ssgcdn.com/93/59/10/item/2097001105993_i1_1100.jpg\",\n    \"price\" : 9730,\n    \"starScore\" : 4.7,\n    \"discountPercent\" : 30,\n    \"productInfoUrl\" : \"https://www.ssg.com/item/itemView.ssg?itemId=2097001105993&siteNo=6001&salestrNo=2439\"\n  },\n  \"specialReviewProfile\" : {\n    \"createdAt\" : \"2023-02-09T22:14:14\",\n    \"cookLevel\" : \"쉬워요\",\n    \"cookQuantity\" : \"1인\",\n    \"cookTime\" : \"10분 미만\",\n    \"descriptionIngredient\" : \"닭다리 300g, 굴소스 3스푼, 올리고당 2스푼, 설탕 2스푼, 다진마늘 1스푼, 소금, 후추\",\n    \"descriptionProcess\" : \"양념장을 만들어서 소금, 후추로 미리 염지한 닭다리에 고루 묻혀 에어프라이어에 구워주세요 (170도 15분, 15분)\",\n    \"descriptionComplete\" : \"소금 후추로 미리 염지를 해둬요 양념장을 만들어서 골고루 발라줘요 에어프라이어에 구우면 완성 예쁘게 플레이팅하면 더 좋아요 맥주안주로 굿\",\n    \"imageInfos\" : [ {\n      \"imageId\" : 1,\n      \"imageUrl\" : \"https://succ.ssgcdn.com/uphoto/202212/20221214221652_1188077699_1_1.jpg\",\n      \"cookStep\" : \"INGREDIENT\"\n    }, {\n      \"imageId\" : 2,\n      \"imageUrl\" : \"https://succ.ssgcdn.com/uphoto/202212/20221214221652_1188077699_2_2.jpg\",\n      \"cookStep\" : \"PROCESS\"\n    }, {\n      \"imageId\" : 3,\n      \"imageUrl\" : \"https://succ.ssgcdn.com/uphoto/202212/20221214221652_1188077699_3_2.jpg\",\n      \"cookStep\" : \"COMPLETE\"\n    } ],\n    \"starScore\" : 3.5,\n    \"author\" : \"gus*******\",\n    \"specialReviewId\" : 1\n  },\n  \"hashTagProfile\" : {\n    \"hashtags\" : [ \"몸보신\", \"좋아\" ]\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/{account-id}/feeds/{feed-id}/bookmarks" : {
      "post" : {
        "tags" : [ "{account-id}" ],
        "operationId" : "add_bookmark_controller_test/test_plus_bookmark_status_ok/",
        "parameters" : [ {
          "name" : "account-id",
          "in" : "path",
          "description" : "account id",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "feed-id",
          "in" : "path",
          "description" : "feed id",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200"
          }
        }
      }
    },
    "/{account-id}/feeds/{feed-id}/recommends" : {
      "post" : {
        "tags" : [ "{account-id}" ],
        "operationId" : "add_recommend_controller_test/test_plus_recommend_status_ok/",
        "parameters" : [ {
          "name" : "account-id",
          "in" : "path",
          "description" : "account id",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "feed-id",
          "in" : "path",
          "description" : "feed id",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200"
          }
        }
      }
    }
  },
  "components" : {
    "schemas" : {
      "api-account-id-feeds-feed-id486549215" : {
        "type" : "object"
      }
    }
  }
}