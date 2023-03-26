package xyz.mahasamut.gun_game.runnable.runnables

import org.bukkit.scheduler.BukkitRunnable
import xyz.mahasamut.gun_game.GunGame
import xyz.mahasamut.gun_game.scoreboard.ScoreboardUtils
import xyz.mahasamut.gun_game.utils.WhiteListUtils

class ScoreboardRunnable : BukkitRunnable() {

    override fun run() {
        for (player in GunGame.instance.server.onlinePlayers) {
            if (WhiteListUtils.isAllow(player.world))
                ScoreboardUtils.createScoreboard(player)
        }
    }

}