package com.yonyk.talaria.resources.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yonyk.talaria.resources.common.swagger.ProductControllerSwagger;
import com.yonyk.talaria.resources.controller.request.ProductDTO;
import com.yonyk.talaria.resources.controller.request.ProductPageDTO;
import com.yonyk.talaria.resources.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController implements ProductControllerSwagger {

  private final ProductService productService;

  // 제품 등록
  @PostMapping
  public ResponseEntity<String> registration(@RequestBody ProductDTO productDTO) {
    productService.registration(productDTO);
    return ResponseEntity.ok("제품 등록이 성공적으로 완료되었습니다.");
  }

  // 제품 조회(단건)
  @GetMapping("/{productId}")
  public ResponseEntity<ProductDTO> getProduct(@PathVariable Long productId) {
    ProductDTO productDTO = productService.getProduct(productId);
    return ResponseEntity.ok(productDTO);
  }

  // 제품 목록 조회
  @GetMapping
  public ResponseEntity<List<ProductDTO>> getProductList(
      @RequestBody ProductPageDTO productPageDTO) {
    List<ProductDTO> productDTOList = productService.getProductList(productPageDTO);
    return ResponseEntity.ok(productDTOList);
  }

  // 제품 수정
  @PutMapping
  public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO) {
    productService.UpdateProduct(productDTO);
    return ResponseEntity.ok("제품 수정이 성공적으로 완료되었습니다.");
  }

  // 제품 삭제
  @DeleteMapping("/{productId}")
  public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
    productService.softDelete(productId);
    return ResponseEntity.ok("제품 삭제가 성공적으로 완료되었습니다.");
  }
}
