package eu.stelliant.irsi.web.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;

public class DemandeStatutDTO {

  @JsonProperty("codeAssureur")
  private String codeAssureur;

  public enum BaseEnum {
    REELLE("REELLE"),

    TEST("TEST");

    private String value;

    BaseEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static BaseEnum fromValue(String text) {
      for (BaseEnum b : BaseEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("base")
  private BaseEnum base;

  public enum EdiEnum {
    XML3("XML3"),

    PDF("PDF"),

    XML3_ET_PDF("XML3_ET_PDF");

    private String value;

    EdiEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static EdiEnum fromValue(String text) {
      for (EdiEnum b : EdiEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("formatMessageEdi")
  private EdiEnum formatMessageEdi;

  @JsonProperty("noSinistre")
  private String noSinistre;

  @JsonProperty("noMission")
  private String noMission;

  @JsonProperty("noAbonneEmetteur")
  private String noAbonneEmetteur;

  @JsonProperty("idMessageEmetteur")
  private String idMessageEmetteur;

  public String getCodeAssureur() {
    return codeAssureur;
  }

  public void setCodeAssureur(String codeAssureur) {
    this.codeAssureur = codeAssureur;
  }

  public BaseEnum getBase() {
    return base;
  }

  public void setBase(BaseEnum base) {
    this.base = base;
  }

  public EdiEnum getFormatMessageEdi() {
    return formatMessageEdi;
  }

  public void setFormatMessageEdi(EdiEnum formatMessageEdi) {
    this.formatMessageEdi = formatMessageEdi;
  }

  public String getNoSinistre() {
    return noSinistre;
  }

  public void setNoSinistre(String noSinistre) {
    this.noSinistre = noSinistre;
  }

  public String getNoMission() {
    return noMission;
  }

  public void setNoMission(String noMission) {
    this.noMission = noMission;
  }

  public String getNoAbonneEmetteur() {
    return noAbonneEmetteur;
  }

  public void setNoAbonneEmetteur(String noAbonneEmetteur) {
    this.noAbonneEmetteur = noAbonneEmetteur;
  }

  public String getIdMessageEmetteur() {
    return idMessageEmetteur;
  }

  public void setIdMessageEmetteur(String idMessageEmetteur) {
    this.idMessageEmetteur = idMessageEmetteur;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DemandeStatutDTO demandeDepotDTO = (DemandeStatutDTO) o;
    return Objects.equals(this.codeAssureur, demandeDepotDTO.codeAssureur) &&
        Objects.equals(this.base, demandeDepotDTO.base) &&
        Objects.equals(this.formatMessageEdi, demandeDepotDTO.formatMessageEdi) &&
        Objects.equals(this.noSinistre, demandeDepotDTO.noSinistre) &&
        Objects.equals(this.noMission, demandeDepotDTO.noMission) &&
        Objects.equals(this.noAbonneEmetteur, demandeDepotDTO.noAbonneEmetteur) &&
        Objects.equals(this.idMessageEmetteur, demandeDepotDTO.idMessageEmetteur);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(codeAssureur, base, formatMessageEdi, noSinistre, noMission, noAbonneEmetteur, idMessageEmetteur);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DemandeStatutDTO {\n");

    sb.append("    codeAssureur: ").append(toIndentedString(codeAssureur)).append("\n");
    sb.append("    base: ").append(toIndentedString(base)).append("\n");
    sb.append("    formatMessageEdi: ").append(toIndentedString(formatMessageEdi)).append("\n");
    sb.append("    noSinistre ").append(toIndentedString(noSinistre)).append("\n");
    sb.append("    noMission: ").append(toIndentedString(noMission)).append("\n");
    sb.append("    noAbonneEmetteur ").append(toIndentedString(noAbonneEmetteur)).append("\n");
    sb.append("    idMessageEmetteur: ").append(toIndentedString(idMessageEmetteur)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first
   * line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
