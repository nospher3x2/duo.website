package dev.duoplugins.website.domain.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.duoplugins.website.domain.client.repository.ClientRepository;
import dev.duoplugins.website.domain.plugin.model.Plugin;
import dev.duoplugins.website.domain.plugin.repository.PluginRepository;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oNospher
 **/
@Entity(name = "duo_clients")
@EqualsAndHashCode
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;


    @Column
    @NotBlank
    @Size(max = 60)
    private String name;

    @NotBlank
    @Column
    @Email
    @Size(max = 255)
    private String email;

    @NotBlank
    @Column
    @Size(max = 11)
    private String cpf;

    @NotBlank
    @Column
    private String password;

    @Column(name = "purchased_plugins")
    private List<Integer> purchasedPlugins;

    public Client() {
        this.purchasedPlugins = new ArrayList<>();
    }

}

