package xyz.mahasamut.gun_game

import org.bukkit.plugin.java.JavaPlugin
import xyz.mahasamut.gun_game.command.CommandManager
import xyz.mahasamut.gun_game.config.ConfigManager
import xyz.mahasamut.gun_game.database.DatabaseManager
import xyz.mahasamut.gun_game.listener.ListenerManager
import xyz.mahasamut.gun_game.placeholderapi.PlaceholderAPIManager
import xyz.mahasamut.gun_game.runnable.RunnableManager
import xyz.mahasamut.gun_game.scoreboard.ScoreboardUtils

class GunGame : JavaPlugin() {

    companion object {
        lateinit var instance: GunGame
            private set
    }

    override fun onEnable() {
        instance = this

        ConfigManager.initConfigs()
        PlaceholderAPIManager.initPlaceholderAPIs()
        CommandManager.initCommands()
        DatabaseManager.initDatabase()
        ListenerManager.initListeners()
        RunnableManager.initRunnables()
    }

    override fun onDisable() {
        for (player in server.onlinePlayers)
            ScoreboardUtils.removeScoreboard(player)
    }
}
