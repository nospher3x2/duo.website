package dev.duoplugins.website.api.token.controller;

import dev.duoplugins.website.api.token.model.Token;
import dev.duoplugins.website.api.token.repository.TokenRepository;
import dev.duoplugins.website.api.token.service.TokenService;
import dev.duoplugins.website.api.token.util.GenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author oNospher
 **/
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private TokenService tokenService;

    @CrossOrigin(origins = "babababa:8080")
    @PostMapping("/generate")
    public ResponseEntity<Token> generate() {
        String preToken = GenerateToken.generate();
        Long duration = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(2);

        Token token = new Token();
        token.setToken(preToken);
        token.setDuration(duration);
        return ResponseEntity.ok(tokenRepository.save(token));
    }

    @GetMapping("/{preToken}")
    public ResponseEntity<Token> validate(@PathVariable String preToken) {
        tokenService.validate(preToken);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
