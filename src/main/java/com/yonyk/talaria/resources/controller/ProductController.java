package com.yonyk.talaria.resources.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yonyk.talaria.resources.controller.request.ProductDTO;
import com.yonyk.talaria.resources.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

  private final ProductService productService;

  // 제품 등록
  @PostMapping
  public ResponseEntity<String> registration(@RequestBody ProductDTO productDTO) {
    productService.registration(productDTO);
    return ResponseEntity.ok("제품 등록이 성공적으로 완료되었습니다.");
  }

  @GetMapping("{productId}")
  public ResponseEntity<ProductDTO> getProduct(@PathVariable Long productId) {
    ProductDTO productDTO = productService.getProduct(productId);
    return ResponseEntity.ok(productDTO);
  }

  @PutMapping
  public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO) {

    return null;
  }

  public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
    return null;
  }
}
