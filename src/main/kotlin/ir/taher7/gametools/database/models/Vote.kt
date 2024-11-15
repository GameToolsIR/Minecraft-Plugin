package ir.taher7.gametools.database.models

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentTimestamp
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp
import org.sayandev.stickynote.bukkit.plugin


data class Vote(
    val id: Int = 0,
    val userId: Int = 0,
    val isReceivedRewards: Boolean = false,
    val votedAt: Instant = Clock.System.now(),
) {

    object Table : org.jetbrains.exposed.sql.Table("${plugin.name.lowercase()}_votes") {
        val id = integer("id").autoIncrement()
        val isReceivedRewards = bool("isReceivedRewards")
        val votedAt = timestamp("votedAt").defaultExpression(CurrentTimestamp)
        val userId = integer("userId").references(User.Table.id).uniqueIndex()

        override val primaryKey = PrimaryKey(id)

    }


}