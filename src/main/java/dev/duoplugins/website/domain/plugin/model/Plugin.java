package dev.duoplugins.website.domain.plugin.model;

import dev.duoplugins.website.domain.plugin.enums.PluginCategory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author oNospher
 **/
@Entity(name = "duo_plugins")
@EqualsAndHashCode
@Getter @Setter
public class Plugin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 60)
    private String name;

    @NotBlank
    @Size(max = 255)
    @Column(columnDefinition = "TEXT")
    private List<PluginCategory> categories;

    @NotBlank
    @Size(max = 5)
    private double version;

    @NotBlank
    private String description;
    @NotBlank
    private Boolean active;

    private String urlDownload;

}
