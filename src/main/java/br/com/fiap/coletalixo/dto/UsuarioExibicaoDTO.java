package br.com.fiap.coletalixo.dto;

import br.com.fiap.coletalixo.model.Usuario;
import br.com.fiap.coletalixo.model.enums.UsuarioRole;

public record UsuarioExibicaoDTO(
    Long usuarioId,
    String nome,
    UsuarioRole role
) {

    public UsuarioExibicaoDTO(Usuario usuario){
        this(
            usuario.getUsuarioId(),
            usuario.getNome(),
            usuario.getRole()
        );
    }

}
