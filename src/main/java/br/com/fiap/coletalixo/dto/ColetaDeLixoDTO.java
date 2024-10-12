package br.com.fiap.coletalixo.dto;

import java.time.LocalDate;

import br.com.fiap.coletalixo.model.enums.StatusColeta;
import br.com.fiap.coletalixo.model.enums.TipoDeLixoColeta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ColetaDeLixoDTO(

    Long idColeta,

    LocalDate dataColeta,

    @NotBlank(message = "O endereço é obrigatório")
    String endereco,

    @NotNull(message = "O tipo de coleta é obrigatório")
    TipoDeLixoColeta tipoDeLixo,

    @NotNull(message = "O status da coleta é obrigatório")
    StatusColeta statusColeta

) {
}
