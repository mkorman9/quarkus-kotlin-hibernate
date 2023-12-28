package com.github.mkorman9

import com.fasterxml.uuid.Generators
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import java.time.Instant

@ApplicationScoped
class DuckService(
    private val entityManager: EntityManager
) {
    @Transactional
    fun findDucks(): List<Duck> {
        return entityManager
            .createQuery("from Duck d", Duck::class.java)
            .resultList
    }

    @Transactional
    fun addDuck(name: String, height: Int) {
        val duck = Duck(
            id = ID_GENERATOR.generate(),
            name = name,
            height = height,
            createdAt = Instant.now()
        )
        entityManager.persist(duck)
    }

    companion object {
        private val ID_GENERATOR = Generators.timeBasedEpochGenerator()
    }
}
