package com.yonyk.talaria.resources.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.devh.boot.grpc.client.autoconfigure.GrpcClientAutoConfiguration;

@Configuration
@Import(GrpcClientAutoConfiguration.class)
public class GrpcSecurityConfig {}
