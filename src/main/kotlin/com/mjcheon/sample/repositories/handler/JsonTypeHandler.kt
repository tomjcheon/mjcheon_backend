package com.mjcheon.sample.repositories.handler

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class JsonTypeHandler<T : Any?>(clazz: Class<T>?) : BaseTypeHandler<T>() {
    private val clazz: Class<T>

    @Throws(SQLException::class)
    override fun setNonNullParameter(ps: PreparedStatement, i: Int, parameter: T, jdbcType: JdbcType) {
        ps.setString(i, toJson(parameter))
    }

    @Throws(SQLException::class)
    override fun getNullableResult(rs: ResultSet, columnName: String): T? {
        return toObject(rs.getString(columnName), clazz)
    }

    @Throws(SQLException::class)
    override fun getNullableResult(rs: ResultSet, columnIndex: Int): T? {
        return toObject(rs.getString(columnIndex), clazz)
    }

    @Throws(SQLException::class)
    override fun getNullableResult(cs: CallableStatement, columnIndex: Int): T? {
        return toObject(cs.getString(columnIndex), clazz)
    }

    private fun toJson(`object`: T): String {
        return try {
            mapper.writeValueAsString(`object`)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun toObject(content: String?, clazz: Class<*>): T? {
        return if (content != null && !content.isEmpty()) {
            try {
                mapper.readValue(content, clazz) as T
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        } else {
            null
        }
    }

    init {
        requireNotNull(clazz) { "Type argument cannot be null" }
        this.clazz = clazz
    }

    companion object {
        private val mapper = ObjectMapper()

        init {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        }
    }
}