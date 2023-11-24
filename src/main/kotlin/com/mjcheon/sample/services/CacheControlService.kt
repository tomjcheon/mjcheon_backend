package com.mjcheon.sample.services

import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class CacheControlService() {

  private val log = LoggerFactory.getLogger(javaClass)
  private val DEFAULT_DATETIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")

  @CacheEvict(value = ["dbCache"], allEntries = true)
  fun clearAllCache() {
    log.info("[Cache Clean]-[dbCache][clearAllCache]-{}", LocalDateTime.now().format(DEFAULT_DATETIME_FORMATTER))
  }
  @CacheEvict(value = ["dbCache"], key = "'LOW_CATEGORY'+#categoryId")
  fun clearLowCategoryCache(categoryId: String) {
    log.info("[Cache Clean]-[dbCache][clearLowCategoryCache]-{}", LocalDateTime.now().format(DEFAULT_DATETIME_FORMATTER))
  }

  @CacheEvict(value = ["dbCache"], key = "'HIGH_CATEGORY'+#categoryId")
  fun clearHighCategoryCache(categoryId: String) {
    log.info("[Cache Clean]-[dbCache][clearHighCategoryCache]-{}", LocalDateTime.now().format(DEFAULT_DATETIME_FORMATTER))
  }

  @CacheEvict(value = ["dbCache"], key = "'ALL_CATEGORY'")
  fun clearAllCategoryCache() {
    log.info("[Cache Clean]-[dbCache][clearAllCategoryCache]-{}", LocalDateTime.now().format(DEFAULT_DATETIME_FORMATTER))
  }

  @CacheEvict(value = ["dbCache"], key = "'ALL_CATEGORY_BRAND_PRICE'")
  fun clearAllCategoryBrandPriceCache() {
    log.info("[Cache Clean]-[dbCache][clearAllCategoryBrandPriceCache]-{}", LocalDateTime.now().format(DEFAULT_DATETIME_FORMATTER))
  }

}

