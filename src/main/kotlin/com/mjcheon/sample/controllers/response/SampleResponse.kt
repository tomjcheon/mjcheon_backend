package com.mjcheon.sample.controllers.response

import com.mjcheon.sample.repositories.entity.BrandProductDto
import com.mjcheon.sample.repositories.entity.CategoryProductDto
import com.mjcheon.sample.repositories.entity.ProductByCategoryDto

data class AllLowPriceByCategoryResponse(
  val products: List<ProductByCategoryDto>? = listOf(),
  val totalPrice: Long
)

data class AllLowPriceByBrandResponse(
  val brand: String,
  val categories: List<CategoryProductDto>? = listOf(),
  val totalPrice: Long
)

data class CategoryBrandPriceResponse(
  val categoryName: String,
  val lowPrice: BrandProductDto,
  val highPrice: BrandProductDto,
)


