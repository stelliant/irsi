package eu.stelliant.irsi.web;

import com.darva.irsi.api.client.DefaultApi;
import com.darva.irsi.api.client.model.InlineResponse2001;
import com.darva.irsi.api.client.model.InlineResponse201;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.stelliant.irsi.web.dto.DemandeDepotDTO;
import eu.stelliant.irsi.web.dto.DemandeStatutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/irsi")
public class IrsiResource {

  private final DefaultApi defaultApi;
  private final ObjectMapper objectMapper;

  @Autowired
  public IrsiResource(DefaultApi defaultApi,
                      ObjectMapper objectMapper) {
    this.defaultApi = defaultApi;
    this.objectMapper = objectMapper;
  }

  @GetMapping("/statut")
  public ResponseEntity getStatut(@RequestBody DemandeStatutDTO demandeStatutDTO) {

    InlineResponse2001 inlineResponse2001 = defaultApi
        .dossierNoSinistreNoMissionMessagesNoAbonneEmetteurIdMessageEmetteurGet(demandeStatutDTO
                                                                                    .getCodeAssureur(),
                                                                                demandeStatutDTO
                                                                                    .getNoSinistre(),
                                                                                demandeStatutDTO
                                                                                    .getNoMission(),
                                                                                demandeStatutDTO
                                                                                    .getNoAbonneEmetteur(),
                                                                                demandeStatutDTO
                                                                                    .getIdMessageEmetteur(),
                                                                                demandeStatutDTO
                                                                                    .getBase()
                                                                                    .toString(),
                                                                                demandeStatutDTO
                                                                                    .getFormatMessageEdi()
                                                                                    .toString());
    return ResponseEntity.ok(inlineResponse2001);
  }

  @PostMapping("/document")
  public ResponseEntity getDocument(@RequestBody DemandeDepotDTO demandeDepotDTO) throws JsonProcessingException {

    InlineResponse201 inlineResponse201 = defaultApi
        .demandesdepotsPost(demandeDepotDTO.getCodeAssureur(),
                            objectMapper.writeValueAsString(demandeDepotDTO.getDemandeDepot()).replace("\\\\", ""),
                            demandeDepotDTO.getBase().toString(),
                            demandeDepotDTO.getModeTest());

    return ResponseEntity.ok(inlineResponse201);
  }
}

