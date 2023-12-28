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
        log.info("Adding Daffy duck")
        duckService.addDuck("Duffy", 7)

        log.info("Adding Donald duck")
        duckService.addDuck("Donald", 6)

        log.info("Adding Daisy duck")
        duckService.addDuck("Daisy", 6)

        log.info("Ducks: ${duckService.findDucks()}")
    }
}
