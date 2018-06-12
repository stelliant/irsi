package eu.stelliant.irsi.config;

import com.darva.irsi.api.client.ApiClient;
import com.darva.irsi.api.client.DefaultApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

  @Value("${irsi.api.baseUrl}")
  String baseUrl;

  @Value("${irsi.api.username}")
  String username;

  @Value("${irsi.api.password}")
  String password;

  @Bean
  public DefaultApi defaultApi() {
    ApiClient apiClient = new ApiClient();
    apiClient.setBasePath(baseUrl);
    apiClient.setUsername(username);
    apiClient.setPassword(password);
    return new DefaultApi(apiClient);
  }

}