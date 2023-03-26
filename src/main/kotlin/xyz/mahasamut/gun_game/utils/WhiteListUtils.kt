package xyz.mahasamut.gun_game.utils

import org.bukkit.World
import org.bukkit.entity.Player
import xyz.mahasamut.gun_game.buildmode.BuildModeManager
import xyz.mahasamut.gun_game.config.ConfigManager

object WhiteListUtils {

    private val arena = ConfigManager.arena

    fun isAllow(player: Player): Boolean {
        return player.world.name.equals(
            arena.world,
            ignoreCase = true
        ) && !BuildModeManager.isInBuildMode(player.uniqueId)
    }

    fun isAllow(world: World): Boolean {
        return world.name.equals(
            arena.world,
            ignoreCase = true
        )
    }

}