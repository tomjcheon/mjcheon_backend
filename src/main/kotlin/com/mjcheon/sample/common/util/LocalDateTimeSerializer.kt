package com.mjcheon.sample.common.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeSerializer : KSerializer<LocalDateTime?> {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("LocalDateTime?", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalDateTime? {
        val text = decoder.decodeString()
        if(text == null || text.isEmpty()){
            return null
        }
        return text?.let { LocalDateTime.parse(it, formatter) }
    }

    override fun serialize(encoder: Encoder, value: LocalDateTime?) {
        value?.format(formatter)?.let { encoder.encodeString(it) }
    }
}
