package com.mjcheon.sample.common.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.json.JsonReadFeature
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Service

@Service
object JsonService {
    private final val mapper = jacksonObjectMapper()
        .registerModule(JavaTimeModule())

    init {
        mapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(),true)
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
    }

    fun toJson(obj: Any): String {
        return mapper.writeValueAsString(obj)
    }

    fun toPrettyJson(obj: Any): String {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj)
    }

    fun toJsonString(obj: Any?): String? {
        return obj?.let{mapper.writeValueAsString(obj)}
    }

    fun toPrettyJsonString(obj: Any?): String? {
        return obj?.let{mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj)}
    }

    fun <T> toObject(json: String?, type: Class<T>): T? {
        return json?.let{ mapper.readValue(json, type)}
    }

    fun toStringList(json: String?): List<String>? {
        return json?.let { mapper.readValue(it) }
    }

    fun toMapList(json: String?): List<Map<String, Any>>?{
        return json?.let{ mapper.readValue(it)}
    }

    fun toMap(json: String?): Map<String, Any>?{
        return json?.let{ mapper.readValue(it)}
    }

}
