package com.github.mkorman9

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "duck_groups")
class DuckGroup(
    @Id
    @Column(name = "id")
    val id: UUID,

    @Column(name = "name")
    var name: String,

    @Column(name = "created_at")
    val createdAt: Instant,

    @OneToMany(mappedBy = "group")
    val ducks: MutableSet<Duck> = mutableSetOf()
) {
    override fun equals(other: Any?): Boolean {
        return id == (other as? Duck)?.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

data class DuckGroupDto(
    val id: UUID,
    val name: String,
    val createdAt: Instant
)
