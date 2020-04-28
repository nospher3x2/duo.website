package dev.duoplugins.website.domain.client.controller;

import dev.duoplugins.website.api.token.controller.TokenController;
import dev.duoplugins.website.api.token.service.TokenService;
import dev.duoplugins.website.domain.client.model.Client;
import dev.duoplugins.website.domain.client.repository.ClientRepository;
import dev.duoplugins.website.domain.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author oNospher
 **/
@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/{preToken}")
    public ResponseEntity<Client> insert(@PathVariable String preToken, @Valid @RequestBody Client client) {
        return ResponseEntity.ok(clientService.save(preToken, client));
    }

    @PutMapping("/{id}/{preToken}")
    public ResponseEntity<Client> update(@PathVariable Integer id, @PathVariable String preToken, @Valid @RequestBody Client client) {

        if(!clientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        client.setId(id);
        return ResponseEntity.ok(clientService.save(preToken, client));
    }

    @DeleteMapping("/{id}/{preToken}")
    public ResponseEntity<Void> delete(@PathVariable Integer id, @PathVariable String preToken) {

        tokenService.validate(preToken);

        if(!clientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        clientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findOne(@PathVariable Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        client.ifPresent(value -> value.setEmail(null));

        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Client> findAll() {
        List<Client> clients = clientRepository.findAll();
        clients.forEach(client -> client.setEmail(null));

        return clients;
    }
}
