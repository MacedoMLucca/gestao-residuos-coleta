package br.com.fiap.coletalixo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.coletalixo.dto.ColetaDeLixoDTO;
import br.com.fiap.coletalixo.dto.ColetaDeLixoExibicaoDTO;
import br.com.fiap.coletalixo.service.ColetaDeLixoService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/c")
public class ColetaDeLixoController {

    @Autowired
    private ColetaDeLixoService coletaDeLixoService;

    @PostMapping("/coletas")
    public ResponseEntity<ColetaDeLixoExibicaoDTO> saveColetaController(@RequestBody @Valid ColetaDeLixoDTO coletaDeLixoDTO) {
        ColetaDeLixoExibicaoDTO coletaDeLixoExibicaoDTO = coletaDeLixoService.saveColetaDeLixo(coletaDeLixoDTO);
        return ResponseEntity.ok(coletaDeLixoExibicaoDTO);
    }

    @PutMapping("/coletas")
    public ResponseEntity<ColetaDeLixoExibicaoDTO> updateColetaController(@RequestBody @Valid ColetaDeLixoDTO coletaDeLixoDTO){
        ColetaDeLixoExibicaoDTO coletaDeLixoExibicaoDTO = coletaDeLixoService.updateColetaDeLixo(coletaDeLixoDTO);
        return ResponseEntity.ok(coletaDeLixoExibicaoDTO);
    }

    @GetMapping("/coletas")
    public ResponseEntity<Page<ColetaDeLixoExibicaoDTO>> listAllColetaController(Pageable paginacao) {
        Page<ColetaDeLixoExibicaoDTO> coletaDeLixo = coletaDeLixoService.listAllColetas(paginacao);
        return ResponseEntity.ok(coletaDeLixo);
    }

    @GetMapping("/coletas/id/{coletaId}")
    public ResponseEntity<ColetaDeLixoExibicaoDTO> findByIdColetaController(@PathVariable Long coletaId){
        ColetaDeLixoExibicaoDTO coletaDeLixoExibicaoDTO = coletaDeLixoService.findByIdColetaDeLixo(coletaId);
        return ResponseEntity.ok(coletaDeLixoExibicaoDTO);
    }
    
    @GetMapping("/coletas/status/{statusColeta}")
    public ResponseEntity<Page<ColetaDeLixoExibicaoDTO>> findByStatusColetaController(@PathVariable String statusColeta, Pageable paginacao) {
        Page<ColetaDeLixoExibicaoDTO> coletaDeLixo = coletaDeLixoService.findByStatusColetaDeLixo(statusColeta, paginacao);
        return ResponseEntity.ok(coletaDeLixo);
    }

    @DeleteMapping("coletas/{coletaId}")
    public ResponseEntity<Void> deleteColetaController(@PathVariable Long coletaId){
        coletaDeLixoService.deleteColetaDeLixo(coletaId);
        return ResponseEntity.noContent().build();
    }
    
}
