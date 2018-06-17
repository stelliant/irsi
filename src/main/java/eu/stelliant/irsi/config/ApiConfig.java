package eu.stelliant.irsi.config;

import com.darva.irsi.api.client.ApiClient;
import com.darva.irsi.api.client.DefaultApi;
import javax.net.ssl.SSLContext;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiConfig {

  private final ApiProperties properties;

  @Autowired
  public ApiConfig(ApiProperties properties) {
    this.properties = properties;
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception {

    SSLContext sslContext = new SSLContextBuilder()
        .create()
        .loadTrustMaterial(
            ResourceUtils.getURL(properties.getApi().getSsl().getTruststore()),
            properties.getApi().getSsl().getStorepass().toCharArray()
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
    apiClient.setBasePath(properties.getApi().getHost());
    apiClient.setUsername(properties.getApi().getAuth().getUsername());
    apiClient.setPassword(properties.getApi().getAuth().getPassword());

    return new DefaultApi(apiClient);
  }

}