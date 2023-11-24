package com.mjcheon.sample.common.configuration

import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CacheConfig {

  @Bean
  fun cacheManager(): CacheManager? {
    val cacheManager = SimpleCacheManager()
    val caches = mutableListOf<Cache>()
    caches.add(ConcurrentMapCache("dbCache"))
    cacheManager.setCaches(caches)
    return cacheManager
  }
}