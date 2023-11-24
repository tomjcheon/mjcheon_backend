package com.mjcheon.sample.services

import com.mjcheon.sample.common.exception.SampleException
import com.mjcheon.sample.common.exception.error.Errors
import com.mjcheon.sample.controllers.response.AllLowPriceByBrandResponse
import com.mjcheon.sample.controllers.response.AllLowPriceByCategoryResponse
import com.mjcheon.sample.controllers.response.CategoryBrandPriceResponse
import com.mjcheon.sample.repositories.entity.BrandProductDto
import com.mjcheon.sample.repositories.entity.CategoryProductDto
import com.mjcheon.sample.repositories.entity.ProductByCategoryDto
import com.mjcheon.sample.repositories.mapper.SampleMapper
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class SampleService(
  private val sampleMapper: SampleMapper,
) {

  @PostConstruct
  fun init() {
    sampleMapper.getAllLowPriceProduct()
  }

  fun getAllLowPriceProductListByCategory(): AllLowPriceByCategoryResponse {
    val productDtoList = mutableListOf<ProductByCategoryDto>()

    sampleMapper.getAllLowPriceProduct().groupBy { it.categoryId }.forEach { entry ->
      entry.value.minByOrNull { it.price }?.let { result ->
        productDtoList.add(
          ProductByCategoryDto(
            categoryName = result.categoryName,
            brandName = result.brandName,
            price = result.price,
          )
        )
      }
    }

    return AllLowPriceByCategoryResponse(
      products = productDtoList.sortedByDescending { it.price },
      totalPrice = productDtoList.sumOf { it.price }
    )
  }

  fun getAllLowPriceProductListByBrand(): AllLowPriceByBrandResponse? {

    val productsPrice = sampleMapper.getAllLowPriceProduct()
    val brandId = productsPrice.minByOrNull { it.price }?.brandId ?: throw SampleException(
      Errors.BAD_REQUEST,
      "Product Not Found."
    )
    val productDtoList = productsPrice.filter { it.brandId == brandId }

    return AllLowPriceByBrandResponse(
      brand = productDtoList.first().brandName,
      categories = productDtoList.map {
        CategoryProductDto(
          category = it.categoryName,
          price = it.price,
        )
      }, totalPrice = productDtoList.sumOf { it.price }
    )
  }

  fun getAllLowPriceProductListByBrand(categoryName: String): CategoryBrandPriceResponse? {

    val categoryId = sampleMapper.getCategoryIdByCategoryName(categoryName) ?: throw SampleException(
      Errors.BAD_REQUEST, "Category Information Not Founded."
    )

    val lowPrice = sampleMapper.getLowPriceProductByCategoryId(categoryId).minByOrNull { it.price } ?: throw SampleException(
      Errors.BAD_REQUEST, "Low Price Product Not Founded."
    )
    val highPrice = sampleMapper.getHighPriceProductByCategoryId(categoryId).maxByOrNull { it.price } ?: throw SampleException(
      Errors.BAD_REQUEST, "High Price Product Not Founded."
    )

    return CategoryBrandPriceResponse(
      categoryName = categoryName,
      lowPrice = lowPrice.let {
        BrandProductDto(
          brand = it.brandName, price = it.price
        )
      },
      highPrice = highPrice.let {
        BrandProductDto(
          brand = it.brandName, price = it.price
        )
      },
    )
  }

}

