package xyz.mahasamut.gun_game.listener.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockBurnEvent
import org.bukkit.event.block.BlockIgniteEvent
import org.bukkit.event.block.BlockPlaceEvent
import xyz.mahasamut.gun_game.utils.WhiteListUtils

class BlockListener : Listener {

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        if (WhiteListUtils.isAllow(event.player))
            event.isCancelled = true
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        if (WhiteListUtils.isAllow(event.player))
            event.isCancelled = true
    }

    @EventHandler
    fun onBlockBurn(event: BlockBurnEvent) {
        if (WhiteListUtils.isAllow(event.block.world))
            event.isCancelled = true
    }

    @EventHandler
    fun onBlockIgnite(event: BlockIgniteEvent) {
        if (WhiteListUtils.isAllow(event.block.world))
            event.isCancelled = true
    }
}