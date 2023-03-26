package xyz.mahasamut.gun_game.listener.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import xyz.mahasamut.gun_game.scoreboard.ScoreboardUtils

class ScoreboardListener : Listener {

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        ScoreboardUtils.removeScoreboard(event.player)
    }

}