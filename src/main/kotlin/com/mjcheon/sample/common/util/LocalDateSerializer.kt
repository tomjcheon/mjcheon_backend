package com.mjcheon.sample.common.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateSerializer: KSerializer<LocalDate?> {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("LocalDate?", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalDate? {
        val text = decoder.decodeString()
        if(text.isEmpty()){
            return null
        }

        return text.let { LocalDate.parse(it, formatter) }
    }

    override fun serialize(encoder: Encoder, value: LocalDate?) {
        value?.format(formatter)?.let { encoder.encodeString(it) }
    }
}