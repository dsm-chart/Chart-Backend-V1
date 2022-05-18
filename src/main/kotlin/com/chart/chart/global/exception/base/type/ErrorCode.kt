package com.chart.chart.global.exception.base.type

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String
) {
    USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "User Already Exists in Database!"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User Not Found!"),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Post Not Found!"),
    IS_NOT_POST_WRITER(HttpStatus.BAD_REQUEST, "You are not Post Writer"),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Comment Not Found!"),
    ANY_COMMENT_NOT_EXISTS(HttpStatus.BAD_REQUEST, "Any Comment Not Exists"),
    MEAL_EXCEPTION(HttpStatus.BAD_REQUEST, "Meal Exception"),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "Expired Token!"),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "Invalid Token!"),
    NEED_TOKEN(HttpStatus.NOT_FOUND, "Token Not Found"),

}