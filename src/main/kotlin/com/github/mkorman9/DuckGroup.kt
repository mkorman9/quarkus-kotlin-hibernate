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
    val ducks: MutableList<Duck>
)
