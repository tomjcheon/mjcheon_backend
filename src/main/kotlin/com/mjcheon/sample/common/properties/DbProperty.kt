package com.mjcheon.sample.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.datasource")
data class DbProperty(
    val url: String,
    val username: String,
    val driverClassName: String
)