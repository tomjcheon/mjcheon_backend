package com.mjcheon.sample.repositories.mapper

import com.mjcheon.sample.repositories.entity.AdminProductDto
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.springframework.cache.annotation.Cacheable

@Mapper
interface SampleMapper {

  @Cacheable("dbCache", key = "'LOW_CATEGORY'+#categoryId")
  @Select(
    """
        SELECT
            c.category_id,
            b.brand_id,
            MIN(p.price) as price,
            p.product_id,
            c.category_name,
            b.brand_name
        FROM
            `PRODUCT` p
        JOIN `CATEGORY` c
          ON p.category_id = c.category_id
        JOIN `BRAND` b
          ON p.brand_id = b.brand_id
        WHERE p.category_id = #{categoryId}
        GROUP BY 1,2
    """
  )
  fun getLowPriceProductByCategoryId(categoryId: String): List<AdminProductDto>

  @Cacheable("dbCache", key = "'HIGH_CATEGORY'+#categoryId")
  @Select(
    """
       SELECT
            c.category_id,
            b.brand_id,
            MAX(p.price) as price,
            p.product_id,
            c.category_name,
            b.brand_name
        FROM
            `PRODUCT` p
        JOIN `CATEGORY` c
          ON p.category_id = c.category_id
        JOIN `BRAND` b
          ON p.brand_id = b.brand_id
        WHERE p.category_id = #{categoryId}
        GROUP BY 1,2
    """
  )
  fun getHighPriceProductByCategoryId(categoryId: String): List<AdminProductDto>

  @Cacheable("dbCache", key = "'ALL_CATEGORY_BRAND_PRICE'")
  @Select(
    """
        SELECT
            c.category_id,
            b.brand_id,
            MIN(p.price) as price,
            p.product_id,
            c.category_name,
            b.brand_name
        FROM
            `PRODUCT` p
        JOIN `CATEGORY` c
          ON p.category_id = c.category_id
        JOIN `BRAND` b
          ON p.brand_id = b.brand_id
        GROUP BY 1,2
    """
  )
  fun getAllLowPriceProduct(): List<AdminProductDto>

  @Select(
    """
        SELECT
            category_id
        FROM
            `CATEGORY`
        WHERE
            category_name = #{categoryName}
    """
  )
  fun getCategoryIdByCategoryName(categoryName: String): String?


}
