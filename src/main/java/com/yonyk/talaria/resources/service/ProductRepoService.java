package com.yonyk.talaria.resources.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yonyk.talaria.resources.controller.request.OrderDTO;
import com.yonyk.talaria.resources.controller.request.ProductDTO;
import com.yonyk.talaria.resources.entity.Product;
import com.yonyk.talaria.resources.entity.enums.ProductType;
import com.yonyk.talaria.resources.exception.CustomException;
import com.yonyk.talaria.resources.exception.enums.ProductExceptionType;
import com.yonyk.talaria.resources.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductRepoService {

  private final ProductRepository productRepository;

  // 제품 저장 및 업데이트
  public void saveProduct(Product product) {
    productRepository.save(product);
  }

  // 제품 삭제
  public void softDeleteProduct(long productId) {
    productRepository.deleteById(productId);
  }

  // 제품 존재하는 지 확인
  public void isExists(long productId) {
    boolean isExists = productRepository.existsById(productId);
    if (!isExists) throw new CustomException(ProductExceptionType.PRODUCT_NOT_FOUND);
  }

  // productId 로 제품 찾아오기
  public Product getProduct(long productId) {
    return productRepository
        .findById(productId)
        .orElseThrow(() -> new CustomException(ProductExceptionType.PRODUCT_NOT_FOUND));
  }

  // ProductType 을 이용해서 제품 목록을 가져오는데 Pageable로 페이지네이션 해서 가져오기
  public List<ProductDTO> getProductList(Pageable pageable, ProductType productType) {
    return productRepository.findByProductType(pageable, productType).stream()
        .map(ProductDTO::toProductDTO)
        .toList();
  }

  // 수량 체크
  public void checkQuantity(OrderDTO orderDTO) {
    boolean isStockAvailable =
        productRepository.isStockAvailable(orderDTO.productId(), orderDTO.quantity());
    if (!isStockAvailable) throw new CustomException(ProductExceptionType.OUT_OF_STOCK);
  }

  // 주문 제품 수량 수량 조절
  public void manageQunatity(long productId, int quantity, boolean calculate) {
    Product findProduct = getProduct(productId);
    // 주문할 제품의 수량 조절
    // true 면 수량 추가, flase 면 수량 감소
    if (calculate) findProduct.setQuantity(findProduct.getQuantity() + quantity);
    else findProduct.setQuantity(findProduct.getQuantity() - quantity);
    // 수량 업데이트
    productRepository.save(findProduct);
  }
}
