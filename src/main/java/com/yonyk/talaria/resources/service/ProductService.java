package com.yonyk.talaria.resources.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yonyk.talaria.resources.controller.request.ProductDTO;
import com.yonyk.talaria.resources.controller.request.ProductPageDTO;
import com.yonyk.talaria.resources.entity.Product;
import com.yonyk.talaria.resources.exception.CustomException;
import com.yonyk.talaria.resources.exception.enums.ProductExceptionType;
import com.yonyk.talaria.resources.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  // 제품 등록
  public void registration(ProductDTO productDTO) {
    // ProductDTO를 Product 엔티티로 변환
    Product product = productDTO.toProduct();
    productRepository.save(product);
  }

  // 제품 조회(단건)
  public ProductDTO getProduct(long id) {
    // Id로 검색 후 없다면 예외 발생 있다면 Product 엔티티를 ProductDTO로 변환
    Product findProduct =
        productRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(ProductExceptionType.PRODUCT_NOT_FOUND));
    return ProductDTO.toProductDTO(findProduct);
  }

  // 제폼 조회(목록)
  public List<ProductDTO> getProductList(ProductPageDTO productPageDTO) {
    // 페이지 객체 생성
    Pageable pageable = PageRequest.of(productPageDTO.page(), productPageDTO.size());
    // 페이지 객체와 타입을 이용해 list를 가져오고 ProductDTO List로 변환
    List<ProductDTO> productDTOList =
        productRepository.findByProductType(pageable, productPageDTO.productType()).stream()
            .map(ProductDTO::toProductDTO)
            .toList();
    return productDTOList;
  }

  // 제품 수정
  public void UpdateProduct(ProductDTO productDTO) {
    // id에 해당하는 제품 존재하는지 확인
    boolean isExists = productRepository.existsById(productDTO.productId());
    if (!isExists) throw new CustomException(ProductExceptionType.PRODUCT_NOT_FOUND);
    // 확인 후 업데이트
    productRepository.save(productDTO.toProduct());
  }

  // 제품 삭제
  public void DeleteProduct(long id) {
    // id에 해당하는 제품 존재하는지 확인
    boolean isExists = productRepository.existsById(id);
    if (!isExists) throw new CustomException(ProductExceptionType.PRODUCT_NOT_FOUND);
    // 확인 후 삭제
    productRepository.deleteById(id);
  }
}
