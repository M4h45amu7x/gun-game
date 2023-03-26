package xyz.mahasamut.gun_game.runnable

import xyz.mahasamut.gun_game.GunGame
import xyz.mahasamut.gun_game.config.ConfigManager
import xyz.mahasamut.gun_game.runnable.runnables.ScoreboardRunnable
import xyz.mahasamut.gun_game.runnable.runnables.SetTimeRunnable

object RunnableManager {
    fun initRunnables() {
        ScoreboardRunnable().runTaskTimer(GunGame.instance, 0L, ConfigManager.config.scoreboard.interval)
        SetTimeRunnable().runTaskTimer(GunGame.instance, 20L, 200L)
    }

}