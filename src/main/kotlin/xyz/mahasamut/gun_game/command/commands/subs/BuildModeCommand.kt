package xyz.mahasamut.gun_game.command.commands.subs

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.mahasamut.gun_game.buildmode.BuildModeManager
import xyz.mahasamut.gun_game.config.ConfigManager
import xyz.mahasamut.gun_game.utils.StringUtils

class BuildModeCommand {

    @Command(name = "buildmode", desc = "Toggle buildmode.")
    @Require("gungame.buldmode")
    fun handler(@Sender sender: CommandSender) {
        val message = ConfigManager.message

        if (sender !is Player) {
            sender.sendMessage(StringUtils.format(message.onlyPlayer, prefix = true))
            return
        }

        BuildModeManager.toggleBuildMode(sender.uniqueId)

        sender.sendMessage(
            StringUtils.format(
                message.toggledBuildMode,
                if (sender is Player) sender else null, true
            )
        )
    }

}