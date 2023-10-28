package com.dashboard.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // JpaAuditing관련 bean등록
public class JpaAuditingConfiguration {

}
