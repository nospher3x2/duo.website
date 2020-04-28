package dev.duoplugins.website.domain.client.service;

import dev.duoplugins.website.api.token.controller.TokenController;
import dev.duoplugins.website.api.token.service.TokenService;
import dev.duoplugins.website.domain.client.exception.ClientException;
import dev.duoplugins.website.api.token.exception.InvalidTokenException;
import dev.duoplugins.website.api.token.model.Token;
import dev.duoplugins.website.domain.client.model.Client;
import dev.duoplugins.website.domain.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author oNospher
 **/
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TokenService tokenService;

    public Client save(String preToken, Client client) {
        Client client1 = clientRepository.findByEmail(client.getEmail());

        tokenService.validate(preToken);

        if(client1 != null && !client.equals(client1)) {
            throw new ClientException("This email is already in use");
        }

        return clientRepository.save(client);
    }

}
