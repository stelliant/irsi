package eu.stelliant.irsi.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import eu.stelliant.irsi.web.dto.DemandeDepotDTO;
import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;

public class ApiDataTypesTest {

  private static final ObjectMapper mapper = new ObjectMapper();

  @BeforeClass
  public static void init() {
    mapper.findAndRegisterModules();
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
  }

  @Test
  public void should_fail() throws IOException {

    // Given
    String jsonDataTypes = "{\n"
        + "    \"base\": \"REELLE\",\n"
        + "    \"demandeDepot\": {\n"
        + "        \"formatMessageEdi\": \"XML3\",\n"
        + "        \"indicNormeFuture\": false,\n"
        + "        \"contexte\": \"IRD\",\n"
        + "        \"messages\": {\n"
        + "            \"message\": {\n"
        + "                \"typeMessage\": {\n"
        + "                    \"codeMessage\": \"65\",\n"
        + "                    \"verMessage\": \"01\",\n"
        + "                    \"relMessage\": \"01\"\n"
        + "                }\n"
        + "            },\n"
        + "            \"flux\": \"<ME650101>    <SE0010201>        <DE00380101>E18000018522</DE00380101>        <DE00390101>A92000011743</DE00390101>    </SE0010201>    <SE0020101>        <DE00460101>295</DE00460101>        <DE00470101>20172057676</DE00470101>        <DE00530101>DARVA-295201720576762284156445</DE00530101>        <DE00540101>49308873</DE00540101>    </SE0020101>    <SE2620401>        <DE11870101>128</DE11870101>    </SE2620401>    <SE0690401>        <DE00220201>TX17057649</DE00220201>        <DE05310101>AMIROU Samy</DE05310101>        <DE80980101>samy.amirou@texa.fr</DE80980101>        <DE01870101>01</DE01870101>        <DE01330101>20180517</DE01330101>        <DT94000201 code=\"2\"/>        <DE00650101>20171011</DE00650101>    </SE0690401>    <GR042101>        <SE0700301>            <DT92090101 code=\"2O\"/>            <DE14460101>20171006</DE14460101>            <DE01040101>1200</DE01040101>        </SE0700301>        <SE1540101LIST>            <SE1540101>                <DT92040201 code=\"0\"/>            </SE1540101>        </SE1540101LIST>    </GR042101>    <GR031901LIST>        <GR031901>            <SE2890601>                <DE14430101>01</DE14430101>                <DCC0070101>                    <DE05360101>392 RUAUX CLAIREFONTAINE</DE05360101>                    <DE05390101>88370</DE05390101>                    <DE05410101>PLOMBIERES LES BAINS</DE05410101>                    <DT91090101 code=\"FR\"/>                    <DE05400101>France</DE05400101>                </DCC0070101>                <DT97090101 code=\"07\"/>            </SE2890601>        </GR031901>    </GR031901LIST>    <GR051501LIST>        <GR051501>            <SE0670401>                <DE01390101>1</DE01390101>                <DT90720102 code=\"01\"/>                <DCC2520102>                    <DT97200102 code=\"H\"/>                </DCC2520102>                <DCC0110102>                    <DE05350101>THIEBAUT</DE05350101>                    <DE05360101>392 RUAUX CLAIREFONTAINE</DE05360101>                    <DE05390101>88370</DE05390101>                    <DE05410101>PLOMBIERES LES BAINS</DE05410101>                    <DT91090101 code=\"FR\"/>                    <DE05400101>France</DE05400101>                </DCC0110102>            </SE0670401>        </GR051501>        <GR051501>            <SE0670401>                <DE01390101>2</DE01390101>                <DT90720102 code=\"99\"/>                <DCC0110102>                    <DE05350101> - ALLIANZ</DE05350101>                    <DE05390101>92076</DE05390101>                    <DE05410101>PARIS LA DEFENSE CEDEX</DE05410101>                    <DT91090101 code=\"FR\"/>                    <DE05400101>France</DE05400101>                </DCC0110102>            </SE0670401>        </GR051501>        <GR051501>            <SE0670401>                <DE01390101>3</DE01390101>                <DT90720102 code=\"99\"/>                <DCC2520102>                    <DT97200102 code=\"A\"/>                </DCC2520102>                <DCC0110102>                    <DE05350101>NoName</DE05350101>                    <DE05390101>92076</DE05390101>                    <DE05410101>PARIS LA DEFENSE CEDEX</DE05410101>                    <DT91090101 code=\"FR\"/>                    <DE05400101>France</DE05400101>                </DCC0110102>            </SE0670401>        </GR051501>        <GR051501>            <SE0670401>                <DE01390101>4</DE01390101>                <DT90720102 code=\"06\"/>                <DCC2520102>                    <DT97200102 code=\"H\"/>                </DCC2520102>                <DCC0110102>                    <DE05350101>NoName2</DE05350101>                    <DE05390101>92076</DE05390101>                    <DE05410101>PARIS LA DEFENSE CEDEX</DE05410101>                    <DT91090101 code=\"FR\"/>                    <DE05400101>France</DE05400101>                </DCC0110102>            </SE0670401>        </GR051501>    </GR051501LIST></ME650101>\"\n"
        + "        }\n"
        + "    }\n"
        + "}";

    // When
    Throwable exception = catchThrowable(() -> mapper
        .readValue(jsonDataTypes, DemandeDepotDTO.class));

    // Then
    assertThat(exception).isInstanceOf(UnrecognizedPropertyException.class)
        .hasMessageContaining("Unrecognized field \"flux\"");
  }

  @Test
  public void should_work() throws IOException {

    // Given
    String jsonDataTypes = "{\n"
        + "    \"base\": \"REELLE\",\n"
        + "    \"demandeDepot\": {\n"
        + "        \"formatMessageEdi\": \"XML3\",\n"
        + "        \"indicNormeFuture\": false,\n"
        + "        \"contexte\": \"IRD\",\n"
        + "        \"messages\": {\n"
      + "                \"message\": {\n"
      + "                    \"typeMessage\": {\n"
      + "                        \"codeMessage\": \"65\",\n"
      + "                        \"verMessage\": \"01\",\n"
      + "                        \"relMessage\": \"01\"\n"
      + "                    },\n"
      + "                    \"flux\": \"<ME650101>    <SE0010201>        <DE00380101>E18000018522</DE00380101>        <DE00390101>A92000011743</DE00390101>    </SE0010201>    <SE0020101>        <DE00460101>295</DE00460101>        <DE00470101>20172057676</DE00470101>        <DE00530101>DARVA-295201720576762284156445</DE00530101>        <DE00540101>49308873</DE00540101>    </SE0020101>    <SE2620401>        <DE11870101>128</DE11870101>    </SE2620401>    <SE0690401>        <DE00220201>TX17057649</DE00220201>        <DE05310101>AMIROU Samy</DE05310101>        <DE80980101>samy.amirou@texa.fr</DE80980101>        <DE01870101>01</DE01870101>        <DE01330101>20180517</DE01330101>        <DT94000201 code=\\\"2\\\"/>        <DE00650101>20171011</DE00650101>    </SE0690401>    <GR042101>        <SE0700301>            <DT92090101 code=\\\"2O\\\"/>            <DE14460101>20171006</DE14460101>            <DE01040101>1200</DE01040101>        </SE0700301>        <SE1540101LIST>            <SE1540101>                <DT92040201 code=\\\"0\\\"/>            </SE1540101>        </SE1540101LIST>    </GR042101>    <GR031901LIST>        <GR031901>            <SE2890601>                <DE14430101>01</DE14430101>                <DCC0070101>                    <DE05360101>392 RUAUX CLAIREFONTAINE</DE05360101>                    <DE05390101>88370</DE05390101>                    <DE05410101>PLOMBIERES LES BAINS</DE05410101>                    <DT91090101 code=\\\"FR\\\"/>                    <DE05400101>France</DE05400101>                </DCC0070101>                <DT97090101 code=\\\"07\\\"/>            </SE2890601>        </GR031901>    </GR031901LIST>    <GR051501LIST>        <GR051501>            <SE0670401>                <DE01390101>1</DE01390101>                <DT90720102 code=\\\"01\\\"/>                <DCC2520102>                    <DT97200102 code=\\\"H\\\"/>                </DCC2520102>                <DCC0110102>                    <DE05350101>THIEBAUT</DE05350101>                    <DE05360101>392 RUAUX CLAIREFONTAINE</DE05360101>                    <DE05390101>88370</DE05390101>                    <DE05410101>PLOMBIERES LES BAINS</DE05410101>                    <DT91090101 code=\\\"FR\\\"/>                    <DE05400101>France</DE05400101>                </DCC0110102>            </SE0670401>        </GR051501>        <GR051501>            <SE0670401>                <DE01390101>2</DE01390101>                <DT90720102 code=\\\"99\\\"/>                <DCC0110102>                    <DE05350101> - ALLIANZ</DE05350101>                    <DE05390101>92076</DE05390101>                    <DE05410101>PARIS LA DEFENSE CEDEX</DE05410101>                    <DT91090101 code=\\\"FR\\\"/>                    <DE05400101>France</DE05400101>                </DCC0110102>            </SE0670401>        </GR051501>        <GR051501>            <SE0670401>                <DE01390101>3</DE01390101>                <DT90720102 code=\\\"99\\\"/>                <DCC2520102>                    <DT97200102 code=\\\"A\\\"/>                </DCC2520102>                <DCC0110102>                    <DE05350101>NoName</DE05350101>                    <DE05390101>92076</DE05390101>                    <DE05410101>PARIS LA DEFENSE CEDEX</DE05410101>                    <DT91090101 code=\\\"FR\\\"/>                    <DE05400101>France</DE05400101>                </DCC0110102>            </SE0670401>        </GR051501>        <GR051501>            <SE0670401>                <DE01390101>4</DE01390101>                <DT90720102 code=\\\"06\\\"/>                <DCC2520102>                    <DT97200102 code=\\\"H\\\"/>                </DCC2520102>                <DCC0110102>                    <DE05350101>NoName2</DE05350101>                    <DE05390101>92076</DE05390101>                    <DE05410101>PARIS LA DEFENSE CEDEX</DE05410101>                    <DT91090101 code=\\\"FR\\\"/>                    <DE05400101>France</DE05400101>                </DCC0110102>            </SE0670401>        </GR051501>    </GR051501LIST></ME650101>\"\n"
      + "                }\n"
      + "            }\n"
        + "    }\n"
        + "}";

    // When
    DemandeDepotDTO demandeDepotDTO = mapper.readValue(jsonDataTypes, DemandeDepotDTO.class);

    // Then
    assertThat(demandeDepotDTO.getDemandeDepot().getFormatMessageEdi().toString())
        .isEqualTo("XML3");
  }
}
