package br.com.fiap.coletalixo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.coletalixo.dto.UsuarioDTO;
import br.com.fiap.coletalixo.dto.UsuarioExibicaoDTO;
import br.com.fiap.coletalixo.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/controll")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PutMapping("/usuarios")
    public ResponseEntity<UsuarioExibicaoDTO> updateUserController(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        UsuarioExibicaoDTO usuarioExibicaoDTO = usuarioService.updateUser(usuarioDTO);
        return ResponseEntity.ok(usuarioExibicaoDTO);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<Page<UsuarioExibicaoDTO>> listAllController(Pageable paginacao) {
        Page<UsuarioExibicaoDTO> usuarios = usuarioService.listAll(paginacao);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<UsuarioExibicaoDTO> findByIdController(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(usuarioService.userFindById(usuarioId));
    }

    @DeleteMapping("/usuarios/{usuarioId}")
    public ResponseEntity<Void> deleteUserController(@PathVariable Long usuarioId) {
        usuarioService.deleteUser(usuarioId);
        return ResponseEntity.noContent().build();
    }
}
