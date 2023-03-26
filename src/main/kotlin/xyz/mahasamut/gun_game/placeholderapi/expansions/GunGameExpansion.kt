package xyz.mahasamut.gun_game.placeholderapi.expansions

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import xyz.mahasamut.gun_game.GunGame
import xyz.mahasamut.gun_game.database.DataManager

class GunGameExpansion : PlaceholderExpansion() {

    override fun getAuthor(): String = GunGame.instance.description.authors[0]
    override fun getIdentifier(): String = GunGame.instance.description.name
    override fun getVersion(): String = GunGame.instance.description.version
    override fun persist(): Boolean = true

    override fun onRequest(player: OfflinePlayer?, params: String): String? {
        if (player !is Player) return null

        val topNameMatch = Regex("^top_name_(\\d+)$").matchEntire(params)
        val topTierMatch = Regex("^top_tier_(\\d+)$").matchEntire(params)

        fun getTopData(index: Int?, transform: (DataManager.PlayerData) -> String): String {
            if (index == null) return "n/A"
            return DataManager.getTopTier(index)?.let(transform) ?: "n/A"
        }

        topNameMatch?.groupValues?.get(1)?.toIntOrNull()
            ?.let { it -> return getTopData(it) { GunGame.instance.server.getOfflinePlayer(it.uuid).name } }
        topTierMatch?.groupValues?.get(1)?.toIntOrNull()
            ?.let { it -> return getTopData(it) { it.tier.toString() } }

        val playerData = DataManager.getData(player) ?: return null
        return when (params.lowercase()) {
            "tier" -> playerData.tier.toString()
            "kills" -> playerData.kill.toString()
            "tier_record" -> playerData.tierHighest.toString()
            "deaths" -> playerData.death.toString()
            else -> null
        }
    }


}