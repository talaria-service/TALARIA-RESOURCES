package com.yonyk.talaria.resources.common.security.grpc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.yonyk.talaria.resources.common.security.util.AuthorizationService;
import com.yonyk.talaria.resources.entity.Member;
import com.yonyk.talaria.resources.exception.CustomException;
import com.yonyk.talaria.resources.exception.enums.SecurityExceptionType;
import com.yonyk.talaria.resources.grpc.AuthorizationProto.AuthRequest;
import com.yonyk.talaria.resources.grpc.AuthorizationProto.AuthResponse;
import com.yonyk.talaria.resources.grpc.AuthorizationServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GrpcClientService {

  @Value("${jwt.access-token-header}")
  String accessTokenHeader;

  private final AuthorizationService authorizationService;

  public Authentication getAuthorization(String accessToken) {
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

    // Member 객체 생성
    Member member = null;

    try {
      // 요청 전송 및 응답 수신
      AuthResponse response = stub.getAuthorization(request);
      // 응답을 Member 객체로 만들기
      member = Member.toMember(response);
    } catch (StatusRuntimeException e) {
      log.error(e.getMessage());
      throw new CustomException(SecurityExceptionType.AUTHRIZATION_FAILED);
    }
    // 채널 종료
    channel.shutdown();
    // 인증객체 리턴
    return authorizationService.getAuthentication(member);
  }
}
