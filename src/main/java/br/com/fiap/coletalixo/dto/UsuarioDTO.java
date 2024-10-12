package br.com.fiap.coletalixo.dto;

import br.com.fiap.coletalixo.model.enums.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(
    Long usuarioId,

    @NotBlank(message = "O nome do usuário é obrigatório")
    String nome,

    @NotBlank(message = "O e-mail do usuário é o obrigatório")
    @Email(message = "O e-mail está em formato inválido")
    String email,

    @NotBlank(message = "A senha do usuário é obrigatória")
    @Size(min = 8, max = 30, message = "A senha deve conter entre 8 e 30 caracteres")
    String senha,

    UsuarioRole role
) {

}
