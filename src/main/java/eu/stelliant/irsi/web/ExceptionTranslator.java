package eu.stelliant.irsi.web;


import com.darva.irsi.api.client.model.InlineResponse200;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class ExceptionTranslator {

  private final ObjectMapper objectMapper;

  @Autowired
  public ExceptionTranslator(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @ExceptionHandler(HttpClientErrorException.class)
  @ResponseBody
  public ResponseEntity client(HttpClientErrorException ex) throws IOException {
    InlineResponse200 rapportErreurWarnings = objectMapper
        .readValue(ex.getResponseBodyAsString(), InlineResponse200.class);
    return new ResponseEntity(rapportErreurWarnings, ex.getStatusCode());
  }

  @ExceptionHandler(HttpServerErrorException.class)
  @ResponseBody
  public ResponseEntity client(HttpServerErrorException ex) throws IOException {
    InlineResponse200 rapportErreurWarnings = objectMapper
        .readValue(ex.getResponseBodyAsString(), InlineResponse200.class);
    return new ResponseEntity(rapportErreurWarnings, ex.getStatusCode());
  }
}
