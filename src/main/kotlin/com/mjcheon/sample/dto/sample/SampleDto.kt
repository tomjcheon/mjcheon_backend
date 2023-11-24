package com.mjcheon.sample.dto.sample

import kotlinx.serialization.Serializable

@Serializable
data class SampleDto(
    var addressId: Long,
    val addressName: String,
    val recipient: String,
    val phoneNumber: String,
    val zipCode: String,
    val address1: String,
    val address2: String,
    var isDefault: Boolean,
    val pccc: String,
)