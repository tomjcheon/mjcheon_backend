package com.mjcheon.sample.repositories.entity

data class ProductDto(
  val categoryId: String,
  val brandId: String,
  val price: Long,
  val productId: String,
)

data class ServiceProductDto(
  val productId: String,
  val categoryName: String,
  val brandId: String,
  val brandName: String,
  val price: Long
)

data class ProductByCategoryDto(
  val categoryName: String,
  val brandName: String,
  val price: Long
)

data class CategoryProductDto(
  val category: String,
  val price: Long
)

data class BrandProductDto(
  val brand: String,
  val price: Long
)
