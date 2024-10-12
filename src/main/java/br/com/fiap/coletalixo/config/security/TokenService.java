package br.com.fiap.coletalixo.config.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import br.com.fiap.coletalixo.model.Usuario;

@Service
public class TokenService {

    @Value("${my.secret.key}")
    private String mySecretKey;

    public String getToken(Usuario usuario){

        Algorithm algorithm = Algorithm.HMAC256(mySecretKey);

        try{ 
            
            String token = JWT.create()
                            .withIssuer("coleta-lixo")
                            .withSubject(usuario.getEmail())
                            .withExpiresAt(getExpirationDate())
                            .sign(algorithm);
    
            return token;
        } catch (JWTCreationException e){
            throw new RuntimeException("Não foi possível gerar o token!");
        }
    }

    public String validToken(String token){

        Algorithm algorithm = Algorithm.HMAC256(mySecretKey);

        return JWT.require(algorithm)
                    .withIssuer("coleta-lixo")
                    .build()
                    .verify(token)
                    .getSubject();

    }

    public Instant getExpirationDate(){
        return LocalDateTime
            .now()
            .plusHours(2)
            .toInstant(ZoneOffset.of("-03:00"));
    }

}
