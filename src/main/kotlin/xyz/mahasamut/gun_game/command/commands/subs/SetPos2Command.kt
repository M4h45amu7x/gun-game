package xyz.mahasamut.gun_game.command.commands.subs

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.mahasamut.gun_game.config.ConfigManager
import xyz.mahasamut.gun_game.utils.StringUtils

class SetPos2Command {

    @Command(name = "setpos2", desc = "Set arena's pos 2.")
    @Require("gungame.setpos2")
    fun handler(@Sender sender: CommandSender) {
        val message = ConfigManager.message

        if (sender !is Player) {
            sender.sendMessage(StringUtils.format(message.onlyPlayer, prefix = true))
            return
        }

        ConfigManager.arena.borders.posTwo.x = sender.location.x
        ConfigManager.arena.borders.posTwo.y = sender.location.y
        ConfigManager.arena.borders.posTwo.z = sender.location.z
        ConfigManager.arena.save()
        ConfigManager.arena.load()

        sender.sendMessage(StringUtils.format(message.setPosTwo, sender, true))
    }

}