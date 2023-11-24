package com.mjcheon.sample.common.exception

import com.mjcheon.sample.common.exception.error.Errors
import org.springframework.http.HttpStatus

class SampleException : RuntimeException {

    val errorCode: String
    val httpStatus: HttpStatus

    constructor(errors: Errors) : super(errors.defaultMessage) {
        this.errorCode = errors.errorCode
        this.httpStatus = errors.httpStatus
    }

    constructor(errors: Errors, cause: Throwable): super("${errors.defaultMessage}. cause=${cause.message}", cause) {
        this.errorCode = errors.errorCode
        this.httpStatus = errors.httpStatus
    }

    constructor(errors: Errors, additionalMessage: String) : super("$additionalMessage") {
        println(additionalMessage)
        this.errorCode = errors.errorCode
        this.httpStatus = errors.httpStatus
    }

    constructor(errors: Errors, additionalMessage: String, cause: Throwable) : super("${errors.defaultMessage}, $additionalMessage. cause=${cause.message}", cause) {
        println(cause.message)
        this.errorCode = errors.errorCode
        this.httpStatus = errors.httpStatus
    }
}