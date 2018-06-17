package eu.stelliant.irsi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "irsi")
@Getter
@Setter
public class ApiProperties {

  private Api api;

  @Getter
  @Setter
  public static class Api {

    private String host;
    private Ssl ssl;
    private Auth auth;
    private Boolean debugging;

    @Getter
    @Setter
    public static class Ssl {

      private String truststore;
      private String storepass;
    }

    @Getter
    @Setter
    public static class Auth {

      private String username;
      private String password;
    }
  }
}
