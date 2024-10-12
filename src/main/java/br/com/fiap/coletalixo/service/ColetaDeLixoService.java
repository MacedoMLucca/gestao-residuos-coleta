package br.com.fiap.coletalixo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.coletalixo.dto.ColetaDeLixoDTO;
import br.com.fiap.coletalixo.dto.ColetaDeLixoExibicaoDTO;
import br.com.fiap.coletalixo.exception.ColetaNotFoundException;
import br.com.fiap.coletalixo.model.ColetaDeLixo;
import br.com.fiap.coletalixo.model.enums.StatusColeta;
import br.com.fiap.coletalixo.repository.ColetaDeLixoRepository;
import br.com.fiap.coletalixo.util.converter.ListToPageConverter;

@Service
public class ColetaDeLixoService {

    @Autowired
    private ColetaDeLixoRepository coletaDeLixoRepository;

    public ColetaDeLixoExibicaoDTO saveColetaDeLixo(ColetaDeLixoDTO coletaDeLixoDTO){

        ColetaDeLixo coletaDeLixo = new ColetaDeLixo();
        BeanUtils.copyProperties(coletaDeLixoDTO, coletaDeLixo);
        if (coletaDeLixo.getStatusColeta() == StatusColeta.CONCLUIDA && coletaDeLixo.getDataColeta() == null) {
            throw new IllegalArgumentException("A data de coleta deve ser especificada para coletas concluídas.");
        } else {
            ColetaDeLixo coletaDeLixoSaved = coletaDeLixoRepository.save(coletaDeLixo);
            return new ColetaDeLixoExibicaoDTO(coletaDeLixoSaved);
        }
    }

    public ColetaDeLixoExibicaoDTO findByIdColetaDeLixo(Long id){

        Optional<ColetaDeLixo> coletaDeLixo = coletaDeLixoRepository.findById(id);

        if (coletaDeLixo.isPresent()){
            return new ColetaDeLixoExibicaoDTO(coletaDeLixo.get());
        } else {
            throw new ColetaNotFoundException("A Coleta não foi encontrada");
        }

    }

    public ColetaDeLixoExibicaoDTO updateColetaDeLixo(ColetaDeLixoDTO coletaDeLixoDTO){

        ColetaDeLixo coletaDeLixo = new ColetaDeLixo();
        BeanUtils.copyProperties(coletaDeLixoDTO, coletaDeLixo);
    
        Optional<ColetaDeLixo> coletaDeLixoOptional = coletaDeLixoRepository.findById(coletaDeLixo.getIdColeta());
    
        if (coletaDeLixoOptional.isPresent()){
            ColetaDeLixo coletaDeLixoSaved = coletaDeLixoOptional.get();
    
            if (coletaDeLixo.getStatusColeta() == StatusColeta.CONCLUIDA && coletaDeLixo.getDataColeta() == null) {
                throw new IllegalArgumentException("A data de coleta deve ser especificada para coletas concluídas.");
            }
    
            coletaDeLixoSaved = coletaDeLixoRepository.save(coletaDeLixo);
            return new ColetaDeLixoExibicaoDTO(coletaDeLixoSaved);
        } else {
            throw new ColetaNotFoundException("Coleta não encontrada!");
        }
    }

    public void deleteColetaDeLixo(Long id){
        Optional<ColetaDeLixo> coletaDeLixoOptional = coletaDeLixoRepository.findById(id);
        if (coletaDeLixoOptional.isPresent()){
            coletaDeLixoRepository.delete(coletaDeLixoOptional.get());
        } else {
            throw new ColetaNotFoundException("Coleta não encontrada!");
        }
    }

    public Page<ColetaDeLixoExibicaoDTO> findByStatusColetaDeLixo(String statusColeta, Pageable paginacao){

        StatusColeta status = StatusColeta.fromString(statusColeta);
        List<ColetaDeLixo> coletaList = coletaDeLixoRepository.findByStatusColeta(status);

        List<ColetaDeLixoExibicaoDTO> coletaDtoList = coletaList
                .stream()
                .map(ColetaDeLixoExibicaoDTO::new)
                .toList();

        return ListToPageConverter.converter(coletaDtoList, paginacao);

    }

    public Page<ColetaDeLixoExibicaoDTO> listAllColetas(Pageable paginacao){
        return coletaDeLixoRepository
            .findAll(paginacao)
            .map(ColetaDeLixoExibicaoDTO::new);
    }
}
