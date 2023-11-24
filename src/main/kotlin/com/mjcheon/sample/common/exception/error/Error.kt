package com.mjcheon.sample.common.exception.error

import org.springframework.http.HttpStatus

enum class Errors(val errorCode: String, val httpStatus: HttpStatus, val defaultMessage: String) {

  BAD_REQUEST("ERR10000", HttpStatus.BAD_REQUEST, "BAD_REQUEST"),
  REQUEST_HEADER_NOT_PRESENT("ERR10001", HttpStatus.BAD_REQUEST, "REQUEST_HEADER_NOT_PRESENT"),

  INVALID_PARAMS("ERR10002", HttpStatus.BAD_REQUEST, "Invalid Params"),

  UNKNOWN_ERROR("ERR59999", HttpStatus.INTERNAL_SERVER_ERROR, "UNKNOWN ERROR"),
  INTERNAL_SERVER_ERROR("ERR9998", HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR"),
}