package xyz.mahasamut.gun_game.listener.listeners

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import xyz.mahasamut.gun_game.config.ConfigManager
import xyz.mahasamut.gun_game.utils.WhiteListUtils

class InventoryListener : Listener {

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val config = ConfigManager.config
        var player = event.whoClicked

        if (player !is Player)
            return

        if (WhiteListUtils.isAllow(player)) {
            if (event.slotType != InventoryType.SlotType.ARMOR) {
                if (config.disableItemMove)
                    event.isCancelled = true
            } else if (config.disableArmorMove)
                event.isCancelled = true
        }
    }

}