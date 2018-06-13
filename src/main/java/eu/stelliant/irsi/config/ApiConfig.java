package eu.stelliant.irsi.config;

import com.darva.irsi.api.client.ApiClient;
import com.darva.irsi.api.client.DefaultApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ApiProperties.class})
public class ApiConfig {

  @Autowired
  ApiProperties properties;

  public ApiProperties getProperties() {
    return properties;
  }

  @Bean
  public DefaultApi defaultApi() {
    ApiClient apiClient = new ApiClient();
    if (getProperties().getApi().getDebugging()) {
      apiClient.setDebugging(true);
    }
    apiClient.setBasePath(getProperties().getApi().getBaseUrl());
    apiClient.setUsername(getProperties().getApi().getUsername());
    apiClient.setPassword(getProperties().getApi().getPassword());
    return new DefaultApi(apiClient);
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper().findAndRegisterModules();
  }
}