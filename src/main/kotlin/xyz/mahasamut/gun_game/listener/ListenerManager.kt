package xyz.mahasamut.gun_game.listener

import xyz.mahasamut.gun_game.GunGame
import xyz.mahasamut.gun_game.listener.listeners.*

object ListenerManager {

    fun initListeners() {
        val plugin = GunGame.instance
        val pluginManager = plugin.server.pluginManager

        pluginManager.registerEvents(BlockListener(), plugin)
        pluginManager.registerEvents(GameplayListener(), plugin)
        pluginManager.registerEvents(InventoryListener(), plugin)
        pluginManager.registerEvents(PlayerListener(), plugin)
        pluginManager.registerEvents(ScoreboardListener(), plugin)
        pluginManager.registerEvents(WorldListener(), plugin)
    }

}