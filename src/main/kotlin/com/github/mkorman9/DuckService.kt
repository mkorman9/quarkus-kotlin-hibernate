package com.github.mkorman9

import com.fasterxml.uuid.Generators
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import java.time.Instant
import java.util.UUID

@ApplicationScoped
class DuckService(
    private val entityManager: EntityManager
) {
    @Transactional
    fun findDucksInGroup(groupId: UUID): List<String> {
        return entityManager
            .find(DuckGroup::class.java, groupId)
            ?.let { group ->
                group.ducks.map { it.name }
            } ?: listOf()
    }

    @Transactional
    fun addDuckGroup(name: String): DuckGroup {
        val duckGroup = DuckGroup(
            id = ID_GENERATOR.generate(),
            name = name,
            createdAt = Instant.now(),
            ducks = mutableListOf()
        )
        entityManager.persist(duckGroup)
        return duckGroup
    }

    @Transactional
    fun addDuck(name: String, height: Int, group: DuckGroup) {
        val duck = Duck(
            id = ID_GENERATOR.generate(),
            name = name,
            height = height,
            createdAt = Instant.now(),
            group = group
        )
        entityManager.persist(duck)
    }

    companion object {
        private val ID_GENERATOR = Generators.timeBasedEpochGenerator()
    }
}
