package br.com.fiap.coletalixo.model;

import java.time.LocalDate;

import br.com.fiap.coletalixo.model.enums.StatusColeta;
import br.com.fiap.coletalixo.model.enums.TipoDeLixoColeta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_coleta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ColetaDeLixo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coleta")
    private Long idColeta;

    @Column(name = "data_coleta")
    private LocalDate dataColeta;

    private String endereco;

    @Column(name = "tipo_lixo")
    private TipoDeLixoColeta tipoDeLixo;

    @Column(name = "status_coleta")
    private StatusColeta statusColeta;

}
