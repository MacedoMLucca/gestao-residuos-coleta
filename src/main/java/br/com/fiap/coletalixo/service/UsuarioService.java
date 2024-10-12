package br.com.fiap.coletalixo.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.fiap.coletalixo.dto.UsuarioDTO;
import br.com.fiap.coletalixo.dto.UsuarioExibicaoDTO;
import br.com.fiap.coletalixo.exception.UserNotFoundException;
import br.com.fiap.coletalixo.model.Usuario;
import br.com.fiap.coletalixo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UsuarioExibicaoDTO userRegister(UsuarioDTO usuarioDto) {
        String senhaCriptografa = passwordEncoder.encode(usuarioDto.senha());
        
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuario);
        
        usuario.setSenha(senhaCriptografa);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioExibicaoDTO(usuarioSalvo);
    }

    public UsuarioExibicaoDTO userFindById(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            return new UsuarioExibicaoDTO(usuarioOptional.get()); 
        } else {
            throw new UserNotFoundException("Usuário não encontrado");
        }
    }

    public UsuarioExibicaoDTO updateUser(UsuarioDTO usuarioDto) {
        Usuario searchedUser = new Usuario();
        BeanUtils.copyProperties(usuarioDto, searchedUser);

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(searchedUser.getUsuarioId());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            String senhaCriptografa = passwordEncoder.encode(usuario.getSenha());
            
            BeanUtils.copyProperties(usuarioDto, usuario, "usuarioId", "senha");
            usuario.setSenha(senhaCriptografa);

            Usuario usuarioSalvo = usuarioRepository.save(usuario);
            return new UsuarioExibicaoDTO(usuarioSalvo);
        } else {
            throw new UserNotFoundException("Usuário não encontrado");
        }
    }

    public void deleteUser(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            usuarioRepository.delete(usuarioOptional.get());
        } else {
            throw new UserNotFoundException("Usuário não existe!");
        }
    }

    public Page<UsuarioExibicaoDTO> listAll(Pageable paginacao) {
        return usuarioRepository
                .findAll(paginacao)
                .map(UsuarioExibicaoDTO::new);
    }
}
