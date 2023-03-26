package xyz.mahasamut.gun_game.listener.listeners

import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerPickupItemEvent
import org.bukkit.event.player.PlayerQuitEvent
import xyz.mahasamut.gun_game.GunGame
import xyz.mahasamut.gun_game.config.ConfigManager
import xyz.mahasamut.gun_game.database.DataManager
import xyz.mahasamut.gun_game.utils.WhiteListUtils

class PlayerListener : Listener {

    @EventHandler(priority = EventPriority.HIGH)
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val config = ConfigManager.config
        val arena = ConfigManager.arena
        val player = event.player

        if (DataManager.getData(player) == null)
            DataManager.createDefaultData(player)

        if (config.disableJoinMessage)
            event.joinMessage = null

        if (config.clearChatOnJoin) {
            for (i in 1..100)
                player.sendMessage("\n")
        }

        player.teleport(
            Location(
                GunGame.instance.server.getWorld(arena.world),
                arena.spawn.x,
                arena.spawn.y,
                arena.spawn.z,
                arena.spawn.yaw,
                arena.spawn.pitch
            )
        )
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        val config = ConfigManager.config

        if (config.disableQuitMessage)
            event.quitMessage = null
    }

    @EventHandler
    fun onPlayerDropItem(event: PlayerDropItemEvent) {
        if (WhiteListUtils.isAllow(event.player) && ConfigManager.config.disableDropItem)
            event.isCancelled = true
    }

    @EventHandler
    fun onPlayerPickupItem(event: PlayerPickupItemEvent) {
        if (WhiteListUtils.isAllow(event.player) && ConfigManager.config.disablePickupItem)
            event.isCancelled = true
    }

    @EventHandler
    fun onEntityDamage(event: EntityDamageEvent) {
        val player = event.entity

        if (player !is Player)
            return

        if (WhiteListUtils.isAllow(player) && ConfigManager.config.disableFallDamage && event.cause == EntityDamageEvent.DamageCause.FALL)
            event.isCancelled = true
    }

    @EventHandler
    fun onFoodLevelChange(event: FoodLevelChangeEvent) {
        val player = event.entity

        if (player !is Player)
            return

        if (WhiteListUtils.isAllow(player) && ConfigManager.config.disableHunger)
            event.isCancelled = true
    }

}