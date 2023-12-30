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
    fun findDuckGroups(): List<DuckGroupDto> {
        return entityManager
            .createQuery("from DuckGroup dg", DuckGroup::class.java)
            .resultList
            .map { group ->
                DuckGroupDto(
                    id = group.id,
                    name = group.name,
                    createdAt = group.createdAt
                )
            }
    }

    @Transactional
    fun findDucksInGroup(groupId: UUID): List<DuckDto> {
        return entityManager
            .find(DuckGroup::class.java, groupId)
            ?.let { group ->
                group.ducks.map { duck ->
                    DuckDto(
                        id = duck.id,
                        name = duck.name,
                        height = duck.height,
                        createdAt = duck.createdAt,
                        groupId = duck.group?.id
                    )
                }
            } ?: listOf()
    }

    @Transactional
    fun addDuckGroup(name: String): DuckGroupDto {
        val duckGroup = DuckGroup(
            id = ID_GENERATOR.generate(),
            name = name,
            createdAt = Instant.now()
        )
        entityManager.persist(duckGroup)
        return DuckGroupDto(
            id = duckGroup.id,
            name = duckGroup.name,
            createdAt = duckGroup.createdAt
        )
    }

    @Transactional
    fun addDuck(name: String, height: Int, group: DuckGroupDto): DuckDto {
        val duck = Duck(
            id = ID_GENERATOR.generate(),
            name = name,
            height = height,
            createdAt = Instant.now(),
            group = entityManager.find(DuckGroup::class.java, group.id)
        )
        entityManager.persist(duck)
        return DuckDto(
            id = duck.id,
            name = duck.name,
            height = duck.height,
            createdAt = duck.createdAt,
            groupId = duck.group?.id
        )
    }

    companion object {
        private val ID_GENERATOR = Generators.timeBasedEpochGenerator()
    }
}
