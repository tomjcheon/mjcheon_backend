package com.mjcheon.sample.common.exception.handler

import com.mjcheon.sample.common.exception.SampleException
import com.mjcheon.sample.common.exception.error.Errors
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.BadSqlGrammarException
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.sql.SQLSyntaxErrorException

@ControllerAdvice
class ExceptionHandler {

  data class ErrorResponseBody(
    val resultCode: String,
    val resultMessage: String
  )

  @ExceptionHandler(Exception::class)
  fun handleException(e: Exception): ResponseEntity<ErrorResponseBody> {

    val body: ErrorResponseBody
    val httpStatus: HttpStatus

    e.printStackTrace()

    val headers: MultiValueMap<String, String> = LinkedMultiValueMap()

    when (e) {
      is SampleException -> {
        body = ErrorResponseBody(e.errorCode, e.message ?: "No message")
        httpStatus = e.httpStatus
        headers["resultMessage"] = e.message
        headers["resultCode"] = e.errorCode
      }

      is HttpRequestMethodNotSupportedException -> {
        body = ErrorResponseBody(Errors.BAD_REQUEST.errorCode, e.message ?: "No message")
        httpStatus = Errors.BAD_REQUEST.httpStatus
        headers["resultMessage"] = e.message
        headers["resultCode"] = Errors.BAD_REQUEST.errorCode
      }

      is MethodArgumentTypeMismatchException -> {
        body = ErrorResponseBody(Errors.BAD_REQUEST.errorCode, e.message ?: "No message")
        httpStatus = Errors.BAD_REQUEST.httpStatus
        headers["resultMessage"] = e.message
        headers["resultCode"] = Errors.BAD_REQUEST.errorCode
      }

      is MethodArgumentNotValidException -> {
        body = ErrorResponseBody(Errors.BAD_REQUEST.errorCode, e.message ?: "No message")
        httpStatus = Errors.BAD_REQUEST.httpStatus
        headers["resultMessage"] = e.message
        headers["resultCode"] = Errors.BAD_REQUEST.errorCode
      }

      is SQLSyntaxErrorException, is BadSqlGrammarException -> {
        body = ErrorResponseBody(Errors.UNKNOWN_ERROR.errorCode, "SQLSyntaxErrorException")
        httpStatus = Errors.UNKNOWN_ERROR.httpStatus
        headers["resultMessage"] = "SQLSyntaxErrorException"
        headers["resultCode"] = Errors.UNKNOWN_ERROR.errorCode
      }
      is MissingRequestHeaderException -> {
        body = ErrorResponseBody(Errors.REQUEST_HEADER_NOT_PRESENT.errorCode, e.message ?: "No message")
        httpStatus = Errors.REQUEST_HEADER_NOT_PRESENT.httpStatus
        headers["resultMessage"] = e.message
        headers["resultCode"] = Errors.REQUEST_HEADER_NOT_PRESENT.errorCode
      }

      else -> {
        body = ErrorResponseBody(Errors.INTERNAL_SERVER_ERROR.errorCode, e.message ?: e.toString())
        httpStatus = Errors.INTERNAL_SERVER_ERROR.httpStatus
      }
    }
    return ResponseEntity(body, headers, httpStatus)
  }
}