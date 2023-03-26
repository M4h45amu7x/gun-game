package xyz.mahasamut.gun_game.command.commands.subs

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.mahasamut.gun_game.config.ConfigManager
import xyz.mahasamut.gun_game.utils.StringUtils

class SetSpawnCommand {

    @Command(name = "setspawn", desc = "Set arena's spawn.")
    @Require("gungame.setspawn")
    fun handler(@Sender sender: CommandSender) {
        val message = ConfigManager.message

        if (sender !is Player) {
            sender.sendMessage(StringUtils.format(message.onlyPlayer, prefix = true))
            return
        }

        ConfigManager.arena.world = sender.world.name
        ConfigManager.arena.spawn.x = sender.location.x
        ConfigManager.arena.spawn.y = sender.location.y
        ConfigManager.arena.spawn.z = sender.location.z
        ConfigManager.arena.spawn.yaw = sender.location.yaw
        ConfigManager.arena.spawn.pitch = sender.location.pitch
        ConfigManager.arena.save()
        ConfigManager.arena.load()

        sender.sendMessage(StringUtils.format(message.setSpawn, sender, true))
    }

}