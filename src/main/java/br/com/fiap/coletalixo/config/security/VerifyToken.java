package br.com.fiap.coletalixo.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.fiap.coletalixo.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class VerifyToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String token = "";

        if (authorizationHeader == null) {
            token = null;
        } else {
            token = authorizationHeader.replace("Bearer ", "").trim();
            String email = tokenService.validToken(token);
            UserDetails usuario = usuarioRepository.findByEmail(email);

            UsernamePasswordAuthenticationToken authenticationToken = (
                new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities())
            );

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);         
            
        }

        filterChain.doFilter(request, response);
        
    }



}
