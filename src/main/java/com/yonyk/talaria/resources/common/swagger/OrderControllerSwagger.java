package com.yonyk.talaria.resources.common.swagger;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.yonyk.talaria.resources.common.security.details.PrincipalDetails;
import com.yonyk.talaria.resources.controller.request.OrderDTO;
import com.yonyk.talaria.resources.controller.request.OrderStatusDTO;
import com.yonyk.talaria.resources.controller.response.OrderDetailDTO;
import com.yonyk.talaria.resources.controller.response.OrderListResponseDTO;
import com.yonyk.talaria.resources.entity.enums.OrderType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Order", description = "주문 관련 API입니다. 로그인한 유저가 작성한 주문만 관련 요청이 가능합니다.")
public interface OrderControllerSwagger {

  @Operation(summary = "주문 생성", description = "주문의 타입, 제품의 ID, 주문 수량, 배송지를 받아서 주문을 생성하는 API 입니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "주문이 성공적으로 완료되었습니다.",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "400",
            description = "제품이 존재하지 않습니다. 또는 재고가 부족합니다. 주문 정보를 다시 확인해주십시오.",
            content = @Content(mediaType = "application/json"))
      })
  public ResponseEntity<String> createOrder(
      @AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody OrderDTO orderDTO);

  @Operation(summary = "주문 조회(단건)", description = "주문 ID를 받아서 주문 상세 정보를 조회(단건)하는 API 입니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "주문 상세 정보가 담긴 OrderDetailDTO가 반환됩니다.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = OrderDetailDTO.class))),
        @ApiResponse(
            responseCode = "400",
            description = "주문이 존재하지 않습니다.",
            content = @Content(mediaType = "application/json"))
      })
  @Parameter(name = "orderId", description = "조회할 주문의 ID입니다.", example = "123", required = true)
  public ResponseEntity<OrderDetailDTO> orderDetails(
      @AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long orderId);

  @Operation(
      summary = "주문 조회(목록)",
      description = "주문 날짜, 페이지당 항목 수, 페이지 번호, 주문의 타입을 받아서 주문 조회(목록) 하는 API")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description =
                "조회 성공/실패 여부, 조건에 맞는 주문의 총 건수, 각 주문의 상세 정보, 이전/현재/이후 링크가 담겨있는 orderListResponseDTO 객체가 반환됩니다.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = OrderListResponseDTO.class))),
      })
  @Parameters({
    @Parameter(
        name = "date",
        description = "조회할 주문 날짜 입니다.",
        example = "2024-09-11",
        required = true),
    @Parameter(
        name = "size",
        description = "페이지 당 항목 수 입니다.",
        example = "5/10/20",
        required = true),
    @Parameter(
        name = "page",
        description = "페이지 번호 입니다. 1부터 시작합니다.",
        example = "1,2,3",
        required = true),
    @Parameter(
        name = "orderType",
        description = "주문의 타입입니다.",
        example = "BUY/SELL",
        required = true)
  })
  public ResponseEntity<OrderListResponseDTO> orderList(
      @AuthenticationPrincipal PrincipalDetails principalDetails,
      @RequestParam("date") LocalDate date,
      @RequestParam("size") int size,
      @RequestParam("page") int page,
      @RequestParam("orderType") OrderType orderType);

  @Operation(summary = "주문 상태 변경", description = "주문 ID, 주문 상태를 받아서 주문 상태를 변경하는 API 입니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "주문상태 수정이 성공적으로 완료되었습니다.",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "400",
            description = "주문이 존재하지 않습니다. 또는 상태를 변경할 수 없습니다.",
            content = @Content(mediaType = "application/json"))
      })
  public ResponseEntity<String> updateOrderStatus(
      @AuthenticationPrincipal PrincipalDetails principalDetails,
      @RequestBody OrderStatusDTO orderStatusDTO);

  @Operation(summary = "주문취소", description = "주문 ID를 받아서 주문을 취소하는 API 입니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "주문취소가 성공적으로 완료되었습니다.",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "400",
            description = "주문이 존재하지 않습니다. 또는 상태를 변경할 수 없습니다.",
            content = @Content(mediaType = "application/json"))
      })
  @Parameter(name = "orderId", description = "삭제할 주문의 ID입니다.", example = "123", required = true)
  public ResponseEntity<String> deleteOrder(
      @AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long orderId);
}
