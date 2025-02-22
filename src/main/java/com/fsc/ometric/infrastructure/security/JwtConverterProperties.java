package com.fsc.ometric.infrastructure.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@ConfigurationProperties(prefix = "jwt.auth.converter")
public class JwtConverterProperties {

  @Value("${jwt.auth.converter.resource-id}")
  private String resourceId;

  @Value("${jwt.auth.converter.principal-attribute}")
  private String principalAttribute;

  public String getResourceId() {
    return resourceId;
  }

  public String getPrincipalAttribute() {
    return principalAttribute;
  }
}
