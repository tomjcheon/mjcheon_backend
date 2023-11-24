package com.mjcheon.sample.dto

object ApiResponse {
    val Success = mapOf<String, Any>("resultCode" to "0", "resultMessage" to "SUCCESS")
    val IamPortVerificationFail = mapOf<String, Any>("resultCode" to "CUS4000001", "resultMessage" to "fail iamport user verification ")
    val CreateReviewFailed = mapOf<String, Any>("resultCode" to "REV20202", "resultMessage" to "Creating review is failed: Unvalid URL")
    val FailUserRegister = mapOf<String, Any>("resultCode" to "CUS4000001", "resultMessage" to "Fail User Register")
    val FailUserLogin = mapOf<String, Any>("resultCode" to "CUS4000002", "resultMessage" to "Fail User Login")
}
