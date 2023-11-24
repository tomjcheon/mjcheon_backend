package com.mjcheon.sample.services

import com.mjcheon.sample.common.exception.SampleException
import com.mjcheon.sample.common.exception.error.Errors
import com.mjcheon.sample.controllers.response.AllAdminDataResponse
import com.mjcheon.sample.repositories.entity.AdminBrandDto
import com.mjcheon.sample.repositories.entity.AdminCategoryDto
import com.mjcheon.sample.repositories.entity.ProductDto
import com.mjcheon.sample.repositories.mapper.AdminMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminService(
  private val cacheControlService: CacheControlService,
  private val adminMapper: AdminMapper,
) {

  fun getAllData(): AllAdminDataResponse {
    return AllAdminDataResponse(
      products = adminMapper.getAllProducts(),
      categories = adminMapper.getAllCategories(),
      brands = adminMapper.getAllBrands(),
    )
  }

  fun upsertBrand(brand: AdminBrandDto): AdminBrandDto {
    if (brand.brandId.isBlank() || brand.brandName.isBlank()) {
      throw SampleException(Errors.INVALID_PARAMS)
    }

    adminMapper.upsertBrand(brandId = brand.brandId, brandName = brand.brandName)
    cacheControlService.clearAllCategoryBrandPriceCache()
    return brand
  }

  fun upsertCategory(category: AdminCategoryDto): AdminCategoryDto {
    if (category.categoryId.isBlank() || category.categoryName.isBlank()) {
      throw SampleException(Errors.INVALID_PARAMS)
    }

    adminMapper.upsertCategory(categoryId = category.categoryId, categoryName = category.categoryName)
    cacheRefreshByCategoryId(categoryId = category.categoryId)
    return category
  }

  fun upsertProduct(product: ProductDto): ProductDto {
    if (product.categoryId.isBlank() || product.brandId.isBlank()) {
      throw SampleException(Errors.INVALID_PARAMS)
    }

    adminMapper.upsertProduct(
      productId = product.productId,
      categoryId = product.categoryId,
      brandId = product.brandId,
      price = product.price,
    )

    cacheRefreshByCategoryId(categoryId = product.categoryId)
    return product
  }

  @Transactional
  fun deleteCategory(categoryId: String) {
    if (categoryId.isBlank()) {
      throw SampleException(Errors.INVALID_PARAMS)
    }

    adminMapper.deleteCategory(categoryId).let {
      if (it == 0) {
        throw SampleException(Errors.INVALID_PARAMS, "Category Not Found")
      }
    }
    adminMapper.deleteProductByCategoryId(categoryId)
    cacheControlService.clearAllCache()
  }

  @Transactional
  fun deleteBrand(brandId: String) {
    if (brandId.isBlank()) {
      throw SampleException(Errors.INVALID_PARAMS)
    }

    adminMapper.deleteBrand(brandId).let {
      if (it == 0) {
        throw SampleException(Errors.INVALID_PARAMS, "Brand Not Found")
      }
    }
    adminMapper.deleteProductByBrandId(brandId)
    cacheControlService.clearAllCache()
  }

  @Transactional
  fun deleteProduct(productId: String) {
    if (productId.isBlank()) {
      throw SampleException(Errors.INVALID_PARAMS)
    }

    adminMapper.deleteProductByProductId(productId).let {
      if (it == 0) {
        throw SampleException(Errors.INVALID_PARAMS, "Product Not Found")
      }
    }
    cacheControlService.clearAllCache()
  }


  private fun cacheRefreshByCategoryId(categoryId: String) {
    cacheControlService.clearLowCategoryCache(categoryId = categoryId)
    cacheControlService.clearHighCategoryCache(categoryId = categoryId)
    cacheControlService.clearAllCategoryCache()
    cacheControlService.clearAllCategoryBrandPriceCache()
  }
}

