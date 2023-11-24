## MUSINSA Kotlin Backend
- Based: Java 17, Kotlin Spring Boot 3
- Author: tomjcheon@gmail.com

## 구현 범위에 대한 설명
- 구현 1) 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
  - GET /service/category/all-low-price
- 구현 2) 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을
  조회하는 API
  - GET /service/brand/lowest-price
- 구현 3) 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
  - GET /service/category/brand-price/{categoryName}
    - PathParams: categoryName (카테고리명)
- 구현 4) 브랜드 및 상품을 추가 / 업데이트 / 삭제하는 API
  - POST /admin/brand (브랜드 추가/업데이트)
    - body { "brandId": "", "brandName": "" }
  - DELETE /admin/brand/{brandId} (삭제)
    - PathParams: brandId
    - 제약사항: 해당 브랜드의 상품이 존재하지 않을 경우에만 제거 가능
  - POST /admin/product (상품 추가/업데이트)
    - body { "productId": "", "brandId": "", "categoryId": "", "price": 0 }
  - DELETE /admin/product/{productId} (삭제)
    - PathParams: productId

## 코드 빌드, 테스트, 실행 방법
- Environment Setup:
  - OpenJDK 17, Kotlin
- Gradle Build:
  - command: ./gradlew build
- Application Launch
  - command: java -jar build/libs/app.jar
  - port: 48080

## 기타 추가 정보
- 추가 구현) 카테고리 추가 / 업데이트 / 삭제하는 API
  - POST /admin/category (브랜드 추가/업데이트)
    - body { "categoryId": "", "categoryName": "" }
  - DELETE /admin/category/{categoryId} (삭제)
    - PathParams: categoryId
    - 제약사항: 해당 카테고리의 상품이 존재하지 않을 경우에만 제거 가능