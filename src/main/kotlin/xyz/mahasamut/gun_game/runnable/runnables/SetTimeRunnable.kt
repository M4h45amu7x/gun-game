package xyz.mahasamut.gun_game.runnable.runnables

import org.bukkit.scheduler.BukkitRunnable
import xyz.mahasamut.gun_game.GunGame
import xyz.mahasamut.gun_game.config.ConfigManager

class SetTimeRunnable : BukkitRunnable() {
    override fun run() {
        if (ConfigManager.config.setTime.enable && GunGame.instance.server.onlinePlayers.isNotEmpty())
            GunGame.instance.server.getWorld(ConfigManager.arena.world)?.time = ConfigManager.config.setTime.value
    }

}