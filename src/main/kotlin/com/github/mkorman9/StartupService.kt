package com.github.mkorman9

import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.jboss.logging.Logger

@ApplicationScoped
class StartupService(
    private val log: Logger,
    private val duckService: DuckService
) {
    fun onStartup(@Observes startupEvent: StartupEvent) {
        log.info("Adding Pirates duck group")
        val pirates = duckService.addDuckGroup("Pirates")

        log.info("Adding Daffy duck")
        duckService.addDuck("Duffy", 7, pirates)

        log.info("Adding Donald duck")
        duckService.addDuck("Donald", 6, pirates)

        log.info("Adding Daisy duck")
        duckService.addDuck("Daisy", 6, pirates)

        log.info("Ducks in group: ${duckService.findDucksInGroup(pirates.id)}")
    }
}
