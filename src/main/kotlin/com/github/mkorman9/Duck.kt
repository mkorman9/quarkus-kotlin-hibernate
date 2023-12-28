package com.github.mkorman9

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "ducks")
class Duck(
    @Id
    @Column(name = "id")
    val id: UUID,

    @Column(name = "name")
    val name: String,

    @Column(name = "height")
    val height: Int,

    @Column(name = "created_at")
    val createdAt: Instant
) {
    override fun toString(): String {
        return "${name}(id = ${id}, height = ${height}, createdAt = ${createdAt})"
    }
}
