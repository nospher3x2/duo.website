package dev.duoplugins.website.domain.plugin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author oNospher
 **/
@AllArgsConstructor
@Getter
public enum PluginCategory {

    FACTIONS(
            "Factions"
    ),
    RANKUP(
            "RankUP"
    ),
    FULLPVP(
            "FullPvP"
    ),
    SURVIVAL(
            "Survival"
    ),
    UTILITY(
            "Utilidades"
    ),
    ENTERTAINMENT(
            "Diversão"
    ),
    MECHANIC(
            "Mecânica"
    ),
    ADMINISTRATION(
            "Administração"
    );

    private String displayName;
}
