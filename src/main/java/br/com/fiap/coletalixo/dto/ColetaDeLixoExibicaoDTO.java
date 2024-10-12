package br.com.fiap.coletalixo.dto;

import br.com.fiap.coletalixo.model.ColetaDeLixo;
import br.com.fiap.coletalixo.model.enums.StatusColeta;
import br.com.fiap.coletalixo.model.enums.TipoDeLixoColeta;

public record ColetaDeLixoExibicaoDTO(
    Long idColeta,
    Object dataColeta, //Alteração para exibir uma mensagem para o usuário se não tiver data de Coleta ("Não conhecia esse tipo Object")
    String endereco,
    TipoDeLixoColeta tipoDeLixo,
    StatusColeta statusColeta

) {

    public ColetaDeLixoExibicaoDTO(ColetaDeLixo coleta){
        this(
            coleta.getIdColeta(),
            coleta.getDataColeta() != null ? coleta.getDataColeta() : "A coleta ainda não foi realizada",
            coleta.getEndereco(),
            coleta.getTipoDeLixo(),
            coleta.getStatusColeta()
        );
    }
}
