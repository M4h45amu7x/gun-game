package xyz.mahasamut.gun_game.database

import org.bukkit.entity.Player
import java.sql.Date
import java.util.*

object DataManager {

    private fun executeQuery(query: String, vararg params: Any): PlayerData? {
        val preparedStatement = DatabaseManager.connection.prepareStatement(query)

        params.forEachIndexed { index, param ->
            when (param) {
                is String -> preparedStatement.setString(index + 1, param)
                is Int -> preparedStatement.setInt(index + 1, param)
                is Date -> preparedStatement.setDate(index + 1, param)
            }
        }

        val resultSet = preparedStatement.executeQuery()
        if (!resultSet.first()) return null

        val playerData = PlayerData(
            resultSet.getInt("id"),
            UUID.fromString(resultSet.getString("uuid")),
            resultSet.getInt("kill"),
            resultSet.getInt("death"),
            resultSet.getInt("tier"),
            resultSet.getInt("tier_highest"),
            resultSet.getDate("created_date"),
            resultSet.getDate("updated_date")
        )

        resultSet.close()
        preparedStatement.close()

        return playerData
    }

    fun getData(player: Player): PlayerData? {
        val query = "SELECT * FROM gungame_player_data WHERE uuid = ?"
        return executeQuery(query, player.uniqueId.toString())
    }

    fun createDefaultData(player: Player) {
        val query = "INSERT INTO gungame_player_data (uuid) VALUES (?)"
        executeQuery(query, player.uniqueId.toString())
    }

    fun increaseDeath(player: Player) {
        val query =
            "UPDATE gungame_player_data SET death = death + 1, tier = GREATEST(0, FLOOR(tier / 2)), updated_date = ? WHERE uuid = ?"
        executeQuery(query, Date(System.currentTimeMillis()), player.uniqueId.toString())
    }

    fun increaseKill(player: Player) {
        val query =
            "UPDATE gungame_player_data SET `kill` = `kill` + 1, tier = tier + 1, tier_highest = GREATEST(tier_highest, tier), updated_date = ? WHERE uuid = ?"
        executeQuery(query, Date(System.currentTimeMillis()), player.uniqueId.toString())
    }

    fun resetTier(player: Player) {
        val query =
            "UPDATE gungame_player_data SET tier = 0, updated_date = ? WHERE uuid = ?"
        executeQuery(query, Date(System.currentTimeMillis()), player.uniqueId.toString())
    }

    fun getTopTier(number: Int): PlayerData? {
        val query = "SELECT * FROM gungame_player_data WHERE tier > 0 ORDER BY tier DESC LIMIT 1 OFFSET ?"
        return executeQuery(query, number - 1)
    }

    data class PlayerData(
        val id: Int,
        val uuid: UUID,
        val kill: Int,
        val death: Int,
        val tier: Int,
        val tierHighest: Int,
        val createdDate: Date,
        val updatedDate: Date
    )
}
