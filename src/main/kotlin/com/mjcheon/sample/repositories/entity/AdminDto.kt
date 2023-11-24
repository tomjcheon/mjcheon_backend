package com.mjcheon.sample.repositories.entity

data class AdminProductDto(
  val categoryId: String,
  val brandId: String,
  val productId: String,
  val price: Long,
  val categoryName: String,
  val brandName: String,
)

data class AdminCategoryDto(
  val categoryId: String,
  val categoryName: String,
)

data class AdminBrandDto(
  val brandId: String,
  val brandName: String,
)

data class AdminCategoryBrandPriceDto(
  val categoryId: String,
  val brandId: String,
  val productId: String,
  val price: Long
)
