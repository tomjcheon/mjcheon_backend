package com.mjcheon.sample

import com.mjcheon.sample.common.properties.DbProperty
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.core.env.Environment
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import java.lang.management.ManagementFactory


@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableAsync
@EnableConfigurationProperties(DbProperty::class)
class SampleApplication

val logger: Logger = LoggerFactory.getLogger(SampleApplication::class.java)

fun main(args: Array<String>) {
  val context = runApplication<SampleApplication>(*args)
  val env = context.getBean(Environment::class.java)
  val memoryBean = ManagementFactory.getMemoryMXBean()
  val xms = memoryBean.heapMemoryUsage.init / 1024 / 1024
  val xmx = memoryBean.heapMemoryUsage.max / 1024 / 1024

  logger.info("Initial Memory (xms) : ${xms}mb")
  logger.info("Max Memory (xmx) : ${xmx}mb")
  logger.info("activeProfiles=${env.activeProfiles?.toList()}")
  logger.info("┌────────────────────────────────────┐")
  logger.info("│   ****    musinsa-sample    ****   │")
  logger.info("│   server successfully started !!   │")
  logger.info("└────────────────────────────────────┘")
}
