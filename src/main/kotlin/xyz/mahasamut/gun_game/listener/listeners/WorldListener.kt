package xyz.mahasamut.gun_game.listener.listeners

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.entity.EntityInteractEvent
import org.bukkit.event.hanging.HangingBreakByEntityEvent
import org.bukkit.event.hanging.HangingPlaceEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.weather.ThunderChangeEvent
import org.bukkit.event.weather.WeatherChangeEvent
import xyz.mahasamut.gun_game.config.ConfigManager
import xyz.mahasamut.gun_game.utils.MaterialUtils
import xyz.mahasamut.gun_game.utils.WhiteListUtils

class WorldListener : Listener {

    @EventHandler
    fun onWeatherChange(event: WeatherChangeEvent) {
        if (WhiteListUtils.isAllow(event.world) && ConfigManager.config.noWeather)
            event.isCancelled = true
    }

    @EventHandler
    fun onThunderChange(event: ThunderChangeEvent) {
        if (WhiteListUtils.isAllow(event.world) && ConfigManager.config.noWeather)
            event.isCancelled = true
    }

    @EventHandler
    fun onCreatureSpawn(event: CreatureSpawnEvent) {
        if (WhiteListUtils.isAllow(event.entity.world) && ConfigManager.config.disableMob)
            event.isCancelled = true
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (event.action == Action.PHYSICAL && WhiteListUtils.isAllow(event.player.world) && ConfigManager.config.disableFarmGrief && (MaterialUtils.isMaterial(
                event.clickedBlock.type, "SOUL_SOIL", "SOIL", "LEGACY_SOIL"
            ))
        )
            event.isCancelled = true
    }

    @EventHandler
    fun onEntityInteract(event: EntityInteractEvent) {
        val entity = event.entity

        if (entity !is Player && WhiteListUtils.isAllow(entity.world) && ConfigManager.config.disableFarmGrief && (MaterialUtils.isMaterial(
                event.block.type, "SOUL_SOIL", "SOIL", "LEGACY_SOIL"
            ))
        )
            event.isCancelled = true
    }

    @EventHandler
    fun onHangingBreakByEntity(event: HangingBreakByEntityEvent) {
        val player = event.remover

        if (player !is Player)
            return

        if (WhiteListUtils.isAllow(player))
            event.isCancelled = true
    }

    @EventHandler
    fun onHangingPlace(event: HangingPlaceEvent) {
        val player = event.player ?: return

        if (WhiteListUtils.isAllow(player))
            event.isCancelled = true
    }

    @EventHandler
    fun onEntityExplode(event: EntityExplodeEvent) {
        if (WhiteListUtils.isAllow(event.entity.world) && ConfigManager.config.disableEntityExplode)
            event.isCancelled = true
    }

}