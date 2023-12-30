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
    var name: String,

    @Column(name = "height")
    var height: Int,

    @Column(name = "created_at")
    var createdAt: Instant
) {
    override fun toString(): String {
        return "${name}(id = ${id}, height = ${height}, createdAt = ${createdAt})"
    }
}
