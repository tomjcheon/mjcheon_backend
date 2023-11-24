package com.mjcheon.sample.controllers.response

import com.mjcheon.sample.repositories.entity.AdminBrandDto
import com.mjcheon.sample.repositories.entity.AdminCategoryDto
import com.mjcheon.sample.repositories.entity.AdminProductDto

data class AllAdminDataResponse(
  val products: List<AdminProductDto>,
  val categories: List<AdminCategoryDto>,
  val brands: List<AdminBrandDto>,
)
