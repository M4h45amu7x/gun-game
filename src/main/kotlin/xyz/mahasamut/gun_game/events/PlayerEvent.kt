package xyz.mahasamut.gun_game.events

import org.bukkit.entity.Player
import xyz.mahasamut.gun_game.database.DataManager
import xyz.mahasamut.gun_game.utils.PlayerUtils

object PlayerEvent {

    fun onKill(player: Player, playerData: DataManager.PlayerData) {
        PlayerUtils.setPlayerTierItems(player, playerData.tier)
        PlayerUtils.setPlayerEXP(player, playerData.tier)
    }

    fun onDeath(player: Player, playerData: DataManager.PlayerData) {
        PlayerUtils.setPlayerTierItems(player, playerData.tier)
        PlayerUtils.setPlayerEXP(player, playerData.tier)
    }

}