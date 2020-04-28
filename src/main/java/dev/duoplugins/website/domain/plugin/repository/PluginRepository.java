package dev.duoplugins.website.domain.plugin.repository;

import dev.duoplugins.website.domain.plugin.model.Plugin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author oNospher
 **/
@Repository
public interface PluginRepository extends JpaRepository<Plugin, Integer> {

   // List<Client> findByCategories(PluginCategory categories);
}
