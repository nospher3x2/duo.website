package dev.duoplugins.website.api.token.repository;

import dev.duoplugins.website.api.token.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author oNospher
 **/
@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    Token findByToken(String token);
}
