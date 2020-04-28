package dev.duoplugins.website.api.token.service;

import dev.duoplugins.website.api.token.exception.InvalidTokenException;
import dev.duoplugins.website.api.token.model.Token;
import dev.duoplugins.website.api.token.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author oNospher
 **/
@Service
public class TokenService {

    @Autowired
    private TokenRepository repository;

    public void validate(String preToken) {
        Token token = repository.findByToken(preToken);
        if(token == null) {
            throw new InvalidTokenException("Token does not exist or is expired.");
        }
        if(token.getDuration() < System.currentTimeMillis()) {
            this.delete(token);
            throw new InvalidTokenException("Token is expired.");
        }
    }

    public void delete(Token token) {
        repository.delete(token);
    }
}
