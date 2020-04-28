package dev.duoplugins.website.domain.client.repository;

import dev.duoplugins.website.domain.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author oNospher
 **/
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByEmail(String email);

}
