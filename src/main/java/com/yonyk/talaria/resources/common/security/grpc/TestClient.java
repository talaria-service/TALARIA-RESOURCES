package com.yonyk.talaria.resources.common.security.grpc;

import com.yonyk.talaria.resources.grpc.AuthorizationProto.AuthRequest;
import com.yonyk.talaria.resources.grpc.AuthorizationProto.AuthResponse;
import com.yonyk.talaria.resources.grpc.AuthorizationServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestClient {
  public static void main(String[] args) {

    String accessTokenHeader = "Authorization";
    String accessToken =
        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaW5zdW5nMTIiLCJhdXRob3JpdGllcyI6IlJPTEVfVVNFUiIsImV4cCI6MTcyNTg5MTgwNn0._7SjDg1lbUF91ejyHyG6oXIOaIIs9UF3mWdJUZtUdWg";
    // 인터셉터 생성
    AuthenticationClientInterceptor interceptor = new AuthenticationClientInterceptor();
    interceptor.setInterceptor(accessTokenHeader, accessToken);

    // GRPC 서버와의 채널 생성
    ManagedChannel channel =
        ManagedChannelBuilder.forAddress("localhost", 50051)
            .usePlaintext()
            .intercept(interceptor)
            .build();

    // GRPC 스텁 생성
    AuthorizationServiceGrpc.AuthorizationServiceBlockingStub stub =
        AuthorizationServiceGrpc.newBlockingStub(channel);

    // 요청 생성
    AuthRequest request = AuthRequest.newBuilder().build();

    try {
      // 요청 전송 및 응답 수신
      AuthResponse response = stub.getAuthorization(request);
      System.out.println("이름: " + response.getMemberName());
      System.out.println("권한: " + response.getMemberRoleList());
    } catch (StatusRuntimeException e) {
      log.error(e.getMessage());
    }
    // 채널 종료
    channel.shutdown();
  }
}
