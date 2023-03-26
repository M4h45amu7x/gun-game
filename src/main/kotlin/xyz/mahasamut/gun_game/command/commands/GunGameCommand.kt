package xyz.mahasamut.gun_game.command.commands

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.mahasamut.gun_game.config.ConfigManager
import xyz.mahasamut.gun_game.utils.StringUtils

class GunGameCommand {

    @Command(name = "", desc = "GunGame commands.")
    fun handler(@Sender sender: CommandSender) {
        sender.sendMessage(
            StringUtils.format(
                ConfigManager.message.unknownCommand,
                if (sender is Player) sender else null,
                true
            )
        )
    }
}