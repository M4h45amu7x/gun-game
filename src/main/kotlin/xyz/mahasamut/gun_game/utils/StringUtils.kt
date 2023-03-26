package xyz.mahasamut.gun_game.utils

import me.clip.placeholderapi.PlaceholderAPI
import net.md_5.bungee.api.ChatColor
import org.bukkit.entity.Player
import xyz.mahasamut.gun_game.config.ConfigManager
import xyz.mahasamut.gun_game.database.DataManager

object StringUtils {
    fun format(
        message: String,
        player: Player? = null,
        prefix: Boolean = false
    ): String = PlaceholderAPI.setPlaceholders(
        player,
        ChatColor.translateAlternateColorCodes('&', (if (prefix) ConfigManager.message.prefix else "") + message)
    )

    private fun getPlayerTier(player: Player): Int {
        val playerData = DataManager.getData(player)
        return playerData?.tier ?: -1
    }

    fun format(message: String, killer: Player, player: Player): String {
        val playerTier = getPlayerTier(player)
        val killerTier = getPlayerTier(killer)

        val formattedMessage = message
            .replace("%gungame_killer%", killer.name, ignoreCase = true)
            .replace("%gungame_player%", player.name, ignoreCase = true)
            .replace("%gungame_killer_tier%", killerTier.toString(), ignoreCase = true)
            .replace("%gungame_player_tier%", playerTier.toString(), ignoreCase = true)

        return ChatColor.translateAlternateColorCodes(
            '&', ConfigManager.message.prefix + formattedMessage
        )
    }
}
