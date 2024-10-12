package br.com.fiap.coletalixo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.coletalixo.model.ColetaDeLixo;
import java.util.List;
import br.com.fiap.coletalixo.model.enums.StatusColeta;


public interface ColetaDeLixoRepository extends JpaRepository<ColetaDeLixo, Long> {

    List<ColetaDeLixo> findByStatusColeta(StatusColeta statusColeta);

}
