package eu.stelliant.irsi.config;

import com.darva.irsi.api.client.ApiClient;
import com.darva.irsi.api.client.DefaultApi;
import javax.net.ssl.SSLContext;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties({ApiProperties.class})
public class ApiConfig {

  @Autowired
  ApiProperties properties;

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception {

    SSLContext sslContext = new SSLContextBuilder()
        .create()
        .loadTrustMaterial(
            ResourceUtils.getURL(properties.getApi().getTruststore()),
            properties.getApi().getStorepass().toCharArray()
        ).build();

    HttpClient client = HttpClients.custom()
        .setSSLContext(sslContext)
        .build();

    return builder
        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(client))
        .build();
  }

  @Bean
  public DefaultApi defaultApi(RestTemplate restTemplate) {

    if (properties.getApi().getDebugging()) {
      restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(restTemplate.getRequestFactory()));
    }
    ApiClient apiClient = new ApiClient(restTemplate);
    if (properties.getApi().getDebugging()) {
      apiClient.setDebugging(true);
    }
    apiClient.setBasePath(properties.getApi().getBaseUrl());
    apiClient.setUsername(properties.getApi().getUsername());
    apiClient.setPassword(properties.getApi().getPassword());
    return new DefaultApi(apiClient);
  }

}