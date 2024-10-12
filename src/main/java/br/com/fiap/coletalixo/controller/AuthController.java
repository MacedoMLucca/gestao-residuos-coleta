package br.com.fiap.coletalixo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.coletalixo.config.security.TokenService;
import br.com.fiap.coletalixo.dto.LoginDTO;
import br.com.fiap.coletalixo.dto.TokenDTO;
import br.com.fiap.coletalixo.dto.UsuarioDTO;
import br.com.fiap.coletalixo.dto.UsuarioExibicaoDTO;
import br.com.fiap.coletalixo.model.Usuario;
import br.com.fiap.coletalixo.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> loginAuthController(@RequestBody @Valid LoginDTO usuarioDTO) {

            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(usuarioDTO.email(), usuarioDTO.senha());
            Authentication auth = authenticationManager.authenticate(usernamePassword);

            String token = tokenService.getToken((Usuario) auth.getPrincipal());

            return ResponseEntity.ok(new TokenDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioExibicaoDTO> registerAuthController(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        
        UsuarioExibicaoDTO usuarioSave = usuarioService.userRegister(usuarioDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSave);
    }
    

}
