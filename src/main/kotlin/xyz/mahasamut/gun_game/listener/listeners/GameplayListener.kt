package xyz.mahasamut.gun_game.listener.listeners

import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.scheduler.BukkitRunnable
import xyz.mahasamut.gun_game.GunGame
import xyz.mahasamut.gun_game.config.ConfigManager
import xyz.mahasamut.gun_game.database.DataManager
import xyz.mahasamut.gun_game.utils.MaterialUtils
import xyz.mahasamut.gun_game.utils.PlayerUtils
import xyz.mahasamut.gun_game.utils.StringUtils
import xyz.mahasamut.gun_game.utils.WhiteListUtils

class GameplayListener : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player

        if (!WhiteListUtils.isAllow(player)) return

        val playerData = DataManager.getData(player)

        if (playerData != null) {
            PlayerUtils.setPlayerTierItems(player, playerData.tier)
            PlayerUtils.setPlayerEXP(player, playerData.tier)
        }
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        if (ConfigManager.config.gameplay.resetTierOnQuit) DataManager.resetTier(event.player)
    }

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        val config = ConfigManager.config
        val killer = event.entity.killer
        val player = event.entity

        if (!WhiteListUtils.isAllow(player)) return

        if (config.gameplay.event.death.sound.enable) player.playSound(
            player.location, Sound.valueOf(config.gameplay.event.death.sound.value), 1f, 1f
        )
        if (config.gameplay.event.death.command.enable) {
            for (command in config.gameplay.event.death.command.value) GunGame.instance.server.dispatchCommand(
                if (command.startsWith("CONSOLE:")) GunGame.instance.server.consoleSender else player,
                StringUtils.format(command, player)
            )
        }

        event.deathMessage = null
        event.drops.clear()
        event.droppedExp = 0

        object : BukkitRunnable() {
            override fun run() {
                player.spigot().respawn()
            }
        }.runTaskLater(GunGame.instance, 10)

        if (killer == null || !WhiteListUtils.isAllow(killer)) return

        val message = ConfigManager.message

        DataManager.increaseKill(killer)
        val playerData = DataManager.getData(killer)
        if (playerData != null) {
            PlayerUtils.setPlayerEXP(killer, playerData.tier)
            PlayerUtils.setPlayerTierItems(killer, playerData.tier)
        }
        killer.sendMessage(StringUtils.format(message.killer, killer, player))
        if (config.gameplay.event.kill.sound.enable) killer.playSound(
            killer.location, Sound.valueOf(config.gameplay.event.kill.sound.value), 1f, 1f
        )
        if (config.gameplay.event.kill.command.enable) {
            for (command in config.gameplay.event.kill.command.value) GunGame.instance.server.dispatchCommand(
                if (command.startsWith("CONSOLE:")) GunGame.instance.server.consoleSender else killer,
                StringUtils.format(command, killer)
            )
        }

        DataManager.increaseDeath(player)
        player.sendMessage(StringUtils.format(message.killed, killer, player))
    }

    @EventHandler
    fun onPlayerRespawn(event: PlayerRespawnEvent) {
        val player = event.player

        if (!WhiteListUtils.isAllow(player.world)) return

        val arena = ConfigManager.arena

        object : BukkitRunnable() {
            override fun run() {
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

                val playerData = DataManager.getData(player)
                if (playerData != null) {
                    PlayerUtils.setPlayerTierItems(player, playerData.tier)
                    PlayerUtils.setPlayerEXP(player, playerData.tier)
                }
            }
        }.runTaskLater(GunGame.instance, 1)
    }

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player = event.player

        if (!WhiteListUtils.isAllow(player)) return

        if (MaterialUtils.isMaterial(player.location.block.type, "WATER", "STATIONARY_WATER") && !player.isDead) {
            player.health = 0.0
            DataManager.increaseDeath(player)
        }
    }

    @EventHandler
    fun onEntityDamageByEntity(event: EntityDamageByEntityEvent) {
        val arena = ConfigManager.arena
        val message = ConfigManager.message
        val killer = event.damager
        val player = event.entity

        if (killer !is Player || player !is Player) return

        if (!WhiteListUtils.isAllow(player)) return

        val world = GunGame.instance.server.getWorld(arena.world)
        if (PlayerUtils.isPlayerInArea(
                player,
                Location(world, arena.borders.posOne.x, arena.borders.posOne.y, arena.borders.posOne.z),
                Location(world, arena.borders.posTwo.x, arena.borders.posTwo.y, arena.borders.posTwo.z)
            )
        ) {
            event.isCancelled = true
            killer.sendMessage(StringUtils.format(message.cantAttackInThisArea, killer, player))
        }
    }

}