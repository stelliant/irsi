package eu.stelliant.irsi.web.dto;

import com.darva.irsi.api.client.model.Demandedepot;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;

public class DemandeDepotDTO {

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

  @JsonProperty("modeTest")
  private Boolean modeTest;

  @JsonProperty("demandeDepot")
  private Demandedepot demandeDepot;

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

  public Boolean getModeTest() {
    return modeTest;
  }

  public void setModeTest(Boolean modeTest) {
    this.modeTest = modeTest;
  }

  public Demandedepot getDemandeDepot() {
    return demandeDepot;
  }

  public void setDemandeDepot(Demandedepot demandeDepot) {
    this.demandeDepot = demandeDepot;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DemandeDepotDTO demandeDepotDTO = (DemandeDepotDTO) o;
    return Objects.equals(this.codeAssureur, demandeDepotDTO.codeAssureur) &&
        Objects.equals(this.base, demandeDepotDTO.base) &&
        Objects.equals(this.modeTest, demandeDepotDTO.modeTest) &&
        Objects.equals(this.demandeDepot, demandeDepotDTO.demandeDepot);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codeAssureur, base, modeTest, demandeDepot);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DemandeDepotDTO {\n");

    sb.append("    codeAssureur: ").append(toIndentedString(codeAssureur)).append("\n");
    sb.append("    base: ").append(toIndentedString(base)).append("\n");
    sb.append("    modeTest: ").append(toIndentedString(modeTest)).append("\n");
    sb.append("    demandeDepot ").append(toIndentedString(demandeDepot)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first
   * line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
