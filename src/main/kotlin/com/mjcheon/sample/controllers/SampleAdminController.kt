package com.mjcheon.sample.controllers

import com.mjcheon.sample.dto.ApiResponse
import com.mjcheon.sample.repositories.entity.AdminBrandDto
import com.mjcheon.sample.repositories.entity.AdminCategoryDto
import com.mjcheon.sample.repositories.entity.ProductDto
import com.mjcheon.sample.services.AdminService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
class SampleAdminController(
  private val adminService: AdminService,
) {

  @GetMapping("/data")
  fun getAllData(): Map<String, Any?> {
    return ApiResponse.Success +
      mapOf("result" to adminService.getAllData())
  }

  @PostMapping("/category")
  fun upsertCategory(
    @RequestBody body: AdminCategoryDto
  ): Map<String, Any?> {
    return ApiResponse.Success +
      mapOf("result" to adminService.upsertCategory(body))
  }

  @PostMapping("/brand")
  fun upsertBrand(
    @RequestBody body: AdminBrandDto
  ): Map<String, Any?> {
    return ApiResponse.Success +
      mapOf("result" to adminService.upsertBrand(body))
  }

  @PostMapping("/product")
  fun upsertProduct(
    @RequestBody body: ProductDto
  ): Map<String, Any?> {
    return ApiResponse.Success +
      mapOf("result" to adminService.upsertProduct(body))
  }

  @DeleteMapping("/category/{categoryId}")
  fun deleteCategory(
    @PathVariable categoryId: String,
  ): Map<String, Any?> {
    adminService.deleteCategory(categoryId)
    return ApiResponse.Success
  }

  @DeleteMapping("/brand/{brandId}")
  fun deleteBrand(
    @PathVariable brandId: String,
  ): Map<String, Any?> {

    adminService.deleteBrand(brandId)
    return ApiResponse.Success
  }

  @DeleteMapping("/product/{productId}")
  fun deleteProduct(
    @PathVariable productId: String,
  ): Map<String, Any?> {
    adminService.deleteProduct(productId)
    return ApiResponse.Success
  }
}