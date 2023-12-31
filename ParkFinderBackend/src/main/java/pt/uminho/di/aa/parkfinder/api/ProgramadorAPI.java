package pt.uminho.di.aa.parkfinder.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.DTOs.GestorDTO;
import pt.uminho.di.aa.parkfinder.api.DTOs.ProgramadorDTO;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Estatisticas;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaParques.DTOs.ParqueDTO;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.ProgramadorService;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Administrador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Gestor;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Programador;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RequestMapping("/apiV1/programador")
public class ProgramadorAPI {
    private final ProgramadorService programadorService;

    @Autowired
    public ProgramadorAPI(ProgramadorService programadorService) {
        this.programadorService = programadorService;
    }

    @PutMapping("/gestores/criar")
    @Operation(summary = "Cria um gestor",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
                                                                                   description = "Informação para criar um gestor",
                                                                                   content = @Content(mediaType = "application/json",
                                                                                                      schema = @Schema(implementation = GestorDTO.class)))
    )
    public ResponseEntity<Void> criarGestor(@RequestBody GestorDTO gDTO) {
        try{
            Gestor g = new Gestor(gDTO.getNome(), gDTO.getEmail(), gDTO.getPassword(), gDTO.getNr_telemovel());
            programadorService.criarGestor(g, gDTO.getIds_parques());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/gestores/remover")
    @Operation(summary = "Remove um gestor da aplicação")
    public ResponseEntity<Void> removerGestor(@RequestParam("id_gestor") int id_gestor) {
        try{
            programadorService.removerGestor(id_gestor);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/gestores/adicionar_parques")
    @Operation(summary = "Adicionar parques a um gestor")
    public ResponseEntity<Void> adicionarParquesAGestor(@RequestBody List<Integer> ids_parques, @RequestParam("id_gestor") int id_gestor) {
        try{
            programadorService.adicionarParquesAGestor(ids_parques,id_gestor);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/gestores/remover_parques")
    @Operation(summary = "Remover parques a um gestor")
    public ResponseEntity<Void> removerParquesAGestor(@RequestBody List<Integer> ids_parques, @RequestParam("id_gestor") int id_gestor) {
        try{
            programadorService.removerParquesAGestor(ids_parques,id_gestor);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/parques/criar")
    @Operation(summary = "Registar um parque na aplicação")
    public ResponseEntity<Void> registarParque(@RequestBody ParqueDTO pDTO) {
        try{
            Parque p = new Parque(pDTO.getNome().orElse(null),
                    pDTO.getMorada().orElse(null),
                    pDTO.getDescricao().orElse(null),
                    pDTO.getLatitude().orElse(null),
                    pDTO.getLongitude().orElse(null),
                    true, 0, 0, 0,
                    pDTO.getCaminho_foto().orElse(null));
            programadorService.registarParque(p);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NullPointerException nue){
            return new ResponseEntityBadRequest<Void>().createBadRequest("Parque não pode ser criado. Campos em falta!");
        } catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/parques/remover")
    @Operation(summary = "Remover um parque da aplicação")
    public ResponseEntity<Void> removerParque(@RequestParam int id_parque) {
        try{
            programadorService.removerParque(id_parque);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/gestores/procurar")
    @Operation(summary = "Procurar um gestor pelo nome próprio")
    public ResponseEntity<List<GestorDTO>> procurarGestor(@RequestParam("nome") String nome) {
        try{
            List<Gestor> gestores = programadorService.procurarGestor(nome);
            List<GestorDTO> gestorDTOS = gestores.stream().map(this::gestorToDTO).toList();
            return new ResponseEntity<>(gestorDTOS, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<List<GestorDTO>>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/gestores/listar")
    @Operation(summary = "Apresenta a lista de gestor registados na aplicação")
    public ResponseEntity<List<GestorDTO>> listarGestores() {
        try{
            List<Gestor> gestores = programadorService.listarGestores();
            List<GestorDTO> gestorDTOS = gestores.stream().map(this::gestorToDTO).toList();
            return new ResponseEntity<>(gestorDTOS, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<List<GestorDTO>>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/parques/estatisticas")
    @Operation(summary = "Apresenta as estatísticas agregadas de todos os parques da aplicação")
    public ResponseEntity<Estatisticas> verEstatisticasGerais() {
        try{
            return new ResponseEntity<>(programadorService.verEstatisticasGerais(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Estatisticas>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/perfil/info")
    public ResponseEntity<ProgramadorDTO> getProgramadorInfo(){
        try{
            Programador prog = programadorService.getProgramadorInfo();
            ProgramadorDTO progDTO = new ProgramadorDTO(prog.getId(), prog.getNome(), prog.getEmail(), prog.getNrTelemovel(), prog.getPassword());
            return new ResponseEntity<>(progDTO, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<ProgramadorDTO>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/logout")
    @Operation(summary = "Efetua o logout do programador")
    public ResponseEntity<Void> logout() {
        try{
            programadorService.logout();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    private GestorDTO gestorToDTO(Gestor g){
        if(g == null) return null;
        List<Integer> ids_parques = null, ids_admins = null;

        //Se a load initialize exception for invocada, então ignora e envia uma lista a null.
        try{
            ids_parques = g.getParques().stream().map(Parque::getId).toList();
            ids_admins = g.getAdmins().stream().map(Administrador::getId).toList();
        } catch (LazyInitializationException ignored){}

        return new GestorDTO(g.getId(), g.getNome(), g.getEmail(), g.getNrTelemovel(), g.getPassword(), ids_parques, ids_admins);
    }
}
