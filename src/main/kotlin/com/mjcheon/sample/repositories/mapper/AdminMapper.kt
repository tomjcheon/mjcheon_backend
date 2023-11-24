package com.mjcheon.sample.repositories.mapper

import com.mjcheon.sample.repositories.entity.AdminBrandDto
import com.mjcheon.sample.repositories.entity.AdminCategoryBrandPriceDto
import com.mjcheon.sample.repositories.entity.AdminCategoryDto
import com.mjcheon.sample.repositories.entity.AdminProductDto
import org.apache.ibatis.annotations.*

@Mapper
interface AdminMapper {

  @Select(
    """
        SELECT
            p.category_id,
            p.brand_id,
            p.product_id,
            p.price,
            c.category_name,
            b.brand_name
        FROM
            `PRODUCT` p
        JOIN `CATEGORY` c
          ON p.category_id = c.category_id
        JOIN `BRAND` b
          ON p.brand_id = b.brand_id
    """
  )
  fun getAllProducts(): List<AdminProductDto>

  @Select(
    """
        SELECT
            p.product_id,
            p.category_id,
            p.brand_id,
            p.price
        FROM
            `PRODUCT` p
        JOIN `CATEGORY` c
          ON p.category_id = c.category_id
        JOIN `BRAND` b
          ON p.brand_id = b.brand_id
        WHERE p.category_id = #{categoryId}
    """
  )
  fun getSimpleProductsByCategoryIdAndBrandId(
    @Param("categoryId") categoryId: String,
  ): List<AdminProductDto>

  @Select(
    """
        SELECT
            category_id,
            category_name
        FROM
            `CATEGORY`
    """
  )
  fun getAllCategories(): List<AdminCategoryDto>

  @Select(
    """
        SELECT
            brand_id,
            brand_name
        FROM
            `BRAND`
    """
  )
  fun getAllBrands(): List<AdminBrandDto>


  @Insert(
    """
      INSERT INTO `CATEGORY`
        VALUES (#{categoryId}, #{categoryName})
      ON DUPLICATE KEY UPDATE
        category_name = #{categoryName}
    """
  )
  fun upsertCategory(
    @Param("categoryId") categoryId: String,
    @Param("categoryName") categoryName: String,
  ): Int

  @Insert(
    """
      INSERT INTO `BRAND`
        VALUES (#{brandId}, #{brandName})
      ON DUPLICATE KEY UPDATE
        brand_name = #{brandName}
    """
  )
  fun upsertBrand(
    @Param("brandId") brandId: String,
    @Param("brandName") brandName: String,
  ): Int

  @Insert(
    """
      INSERT INTO `PRODUCT`
        VALUES (#{productId}, #{categoryId}, #{brandId}, #{price})
      ON DUPLICATE KEY UPDATE
        category_id = #{categoryId},
        brand_id = #{brandId},
        price = #{price}
    """
  )
  fun upsertProduct(
    @Param("productId") productId: String,
    @Param("categoryId") categoryId: String,
    @Param("brandId") brandId: String,
    @Param("price") price: Long,
  ): Int


  @Insert(
    """
      INSERT INTO `BRAND_CATEGORY_MAX_PRICE`
        VALUES (#{categoryId}, #{brandId}, #{productId}, #{price})
      ON DUPLICATE KEY UPDATE
        product_id = #{productId},
        price = #{price}
    """
  )
  fun upsertBrandCategoryMaxPrice(
    @Param("categoryId") categoryId: String,
    @Param("brandId") brandId: String,
    @Param("productId") productId: String,
    @Param("price") price: Long,
  ): Int

  @Insert(
    """
      INSERT INTO `BRAND_CATEGORY_MIN_PRICE`
        VALUES (#{categoryId},#{brandId},#{productId},#{price})
      ON DUPLICATE KEY UPDATE
        product_id = #{productId},
        price = #{price}
    """
  )
  fun upsertBrandCategoryMinPrice(
    @Param("categoryId") categoryId: String,
    @Param("brandId") brandId: String,
    @Param("productId") productId: String,
    @Param("price") price: Long,
  ): Int

  @Select(
    """
        SELECT
            category_id,
            brand_id,
            product_id,
            price
        FROM
            `BRAND_CATEGORY_MAX_PRICE`
        WHERE
            category_id = #{categoryId} OR brand_id = #{brandId} OR product_id = #{productId}
    """
  )
  fun getBrandCategoryMax(
    @Param("productId") productId: String,
    @Param("categoryId") categoryId: String,
    @Param("brandId") brandId: String,
  ): List<AdminCategoryBrandPriceDto>

  @Select(
    """
        SELECT
            category_id,
            brand_id,
            product_id,
            price
        FROM
            `BRAND_CATEGORY_MIN_PRICE`
        WHERE
            category_id = #{categoryId} OR brand_id = #{brandId} OR product_id = #{productId}
    """
  )
  fun getBrandCategoryMin(
    @Param("productId") productId: String,
    @Param("categoryId") categoryId: String,
    @Param("brandId") brandId: String,
  ): List<AdminCategoryBrandPriceDto>

  @Delete(
    """
      DELETE FROM `CATEGORY`
      WHERE category_id = #{categoryId}
    """
  )
  fun deleteCategory(
    @Param("categoryId") categoryId: String,
  ): Int

  @Delete(
    """
      DELETE FROM `BRAND`
      WHERE brand_id = #{brandId}
    """
  )
  fun deleteBrand(
    @Param("brandId") brandId: String,
  ): Int

  @Delete(
    """
      DELETE FROM `PRODUCT`
      WHERE product_id = #{productId}
    """
  )
  fun deleteProductByProductId(
    @Param("productId") productId: String,
  ): Int

  @Delete(
    """
      DELETE FROM `PRODUCT`
      WHERE brand_id = #{brandId}
    """
  )
  fun deleteProductByBrandId(
    @Param("brandId") brandId: String,
  ): Int

  @Delete(
    """
      DELETE FROM `PRODUCT`
      WHERE category_id = #{categoryId}
    """
  )
  fun deleteProductByCategoryId(
    @Param("categoryId") categoryId: String,
  ): Int
}
