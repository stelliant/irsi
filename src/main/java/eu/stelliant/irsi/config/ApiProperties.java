package eu.stelliant.irsi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "irsi")
public class ApiProperties {

  private Api api;

  public Api getApi() {
    return api;
  }

  public void setApi(Api api) {
    this.api = api;
  }

  public static class Api {

    private String truststore;
    private String storepass;
    private String baseUrl;
    private String username;
    private String password;
    private Boolean debugging;

    public String getTruststore() {
      return truststore;
    }

    public void setTruststore(String truststore) {
      this.truststore = truststore;
    }

    public String getStorepass() {
      return storepass;
    }

    public void setStorepass(String storepass) {
      this.storepass = storepass;
    }

    public String getBaseUrl() {
      return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public Boolean getDebugging() {
      return debugging;
    }

    public void setDebugging(Boolean debugging) {
      this.debugging = debugging;
    }
  }
}
