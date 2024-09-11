package com.yonyk.talaria.resources.common.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.yonyk.talaria.resources.controller.request.ProductDTO;
import com.yonyk.talaria.resources.controller.request.ProductPageDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product", description = "제품 관련 API입니다. 기본적으로 ADMIN 권한이 없다면 요청이 거절됩니다.")
public interface ProductControllerSwagger {

  @Operation(
      summary = "제품 등록",
      description = "제품 이름, 제품의 타입, 제품 무게, 제품 수량, 제품 가격을 받아서 제품을 등록하는 API 입니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "제품 등록이 성공적으로 완료되었습니다.",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "400",
            description = "취급하는 제품이 아닐 때 예외가 반환됩니다.(입력값이 올바르지 않습니다.)",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "403",
            description = "요청권한이 없습니다.",
            content = @Content(mediaType = "application/json"))
      })
  public ResponseEntity<String> registration(@RequestBody ProductDTO productDTO);

  @Operation(summary = "제품 조회(단건)", description = "제품의 ID를 받아서 조회(단건)하는 API 입니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "제품의 상세 정보가 ProductDTO 객체에 담겨 반환됩니다.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductDTO.class))),
        @ApiResponse(
            responseCode = "400",
            description = "제품이 존재하지 않습니다.",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "403",
            description = "요청권한이 없습니다.",
            content = @Content(mediaType = "application/json"))
      })
  @Parameter(name = "productId", description = "조회할 제품의 ID입니다.", example = "123", required = true)
  public ResponseEntity<ProductDTO> getProduct(@PathVariable Long productId);

  @Operation(
      summary = "제품 조회(목록)",
      description = "페이지당 항목 수, 페이지 번호, 제품의 타입을 받아서 조회(목록)하는 API 입니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "조건에 해당하는 제품의 목록이 List<ProductDTO> 객체에 담겨 반환됩니다.",
            content =
                @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)))),
        @ApiResponse(
            responseCode = "403",
            description = "요청권한이 없습니다.",
            content = @Content(mediaType = "application/json"))
      })
  public ResponseEntity<List<ProductDTO>> getProductList(
      @RequestBody ProductPageDTO productPageDTO);

  @Operation(summary = "제품 수정", description = "제품의 전체 정보를 받아서 수정하는 API 입니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "제품 수정이 성공적으로 완료되었습니다.",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "400",
            description = "제품이 존재하지 않습니다.",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "403",
            description = "요청권한이 없습니다.",
            content = @Content(mediaType = "application/json"))
      })
  public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO);

  @Operation(summary = "제품 삭제", description = "제품의 ID를 받아서 제품을 삭제하는 API 입니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "제품 삭제가 성공적으로 완료되었습니다.",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "400",
            description = "제품이 존재하지 않습니다.",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "403",
            description = "요청권한이 없습니다.",
            content = @Content(mediaType = "application/json"))
      })
  @Parameter(name = "productId", description = "삭제할 제품의 ID입니다.", example = "123", required = true)
  public ResponseEntity<String> deleteProduct(@PathVariable Long productId);
}
