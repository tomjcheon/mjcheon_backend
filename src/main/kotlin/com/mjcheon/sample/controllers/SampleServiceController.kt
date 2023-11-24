package com.mjcheon.sample.controllers

import com.mjcheon.sample.dto.ApiResponse
import com.mjcheon.sample.services.SampleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/service")
class SampleServiceController(
  private val sampleService: SampleService,
) {
  // 1. 카테고리 별 최저가격 브랜드와 상품가격, 총액을 조회한 API
  @GetMapping("/category/all-low-price")
  fun getCategoryAllLowPrice(): Map<String, Any?> {
    return ApiResponse.Success +
      mapOf("result" to sampleService.getAllLowPriceProductListByCategory())
  }

  // 2. 단일 브랜드로 모든카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 요구하는 API
  @GetMapping("/brand/lowest-price")
  fun getBrandLowestPrice(): Map<String, Any?> {
    return ApiResponse.Success +
      mapOf("result" to sampleService.getAllLowPriceProductListByBrand())
  }

  // 3. 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
  @GetMapping("/category/brand-price/{categoryName}")
  fun getCategoryBrandPrice(
    @PathVariable categoryName: String
  ): Map<String, Any?> {

    return ApiResponse.Success +
      mapOf("result" to sampleService.getAllLowPriceProductListByBrand(categoryName))
  }
}