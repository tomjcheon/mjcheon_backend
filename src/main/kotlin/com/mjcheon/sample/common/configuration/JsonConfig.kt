package com.mjcheon.sample.common.configuration

import kotlinx.serialization.json.Json

object JsonConfig {

    @JvmStatic
    val jsonParser = Json { ignoreUnknownKeys = true }

    @JvmStatic
    val jsonRetrofit = Json {
        isLenient = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
}