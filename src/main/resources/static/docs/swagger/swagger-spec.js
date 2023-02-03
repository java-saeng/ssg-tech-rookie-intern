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
    "/api/feeds/{feed-id}/comments" : {
      "get" : {
        "tags" : [ "api" ],
        "operationId" : "comment_api_test/get_comments_test/",
        "parameters" : [ {
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
                  "$ref" : "#/components/schemas/api-feeds-feed-id-comments1073967637"
                },
                "examples" : {
                  "comment_api_test/get_comments_test/" : {
                    "value" : "{\n  \"commentCount\" : 0,\n  \"comments\" : [ ]\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/test" : {
      "post" : {
        "tags" : [ "test" ],
        "operationId" : "sample_controller_test/text_success_test/",
        "requestBody" : {
          "content" : {
            "application/json" : {
              "schema" : {
                "$ref" : "#/components/schemas/test1151360606"
              },
              "examples" : {
                "sample_controller_test/text_success_test/" : {
                  "value" : "{\n  \"text\" : \"This is test\"\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/test1151360606"
                },
                "examples" : {
                  "sample_controller_test/text_success_test/" : {
                    "value" : "{\n  \"text\" : \"This is test\"\n}"
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
      "api-feeds-feed-id-comments1073967637" : {
        "type" : "object",
        "properties" : {
          "comments" : {
            "type" : "array",
            "description" : "댓글 목록",
            "items" : {
              "oneOf" : [ {
                "type" : "object"
              }, {
                "type" : "boolean"
              }, {
                "type" : "string"
              }, {
                "type" : "number"
              } ]
            }
          },
          "commentCount" : {
            "type" : "number",
            "description" : "댓글 개수"
          }
        }
      },
      "test1151360606" : {
        "type" : "object",
        "properties" : {
          "text" : {
            "type" : "string",
            "description" : "텍스트"
          }
        }
      }
    }
  }
}