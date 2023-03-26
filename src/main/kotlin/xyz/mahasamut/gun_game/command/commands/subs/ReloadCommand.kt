package xyz.mahasamut.gun_game.command.commands.subs

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.mahasamut.gun_game.GunGame
import xyz.mahasamut.gun_game.config.ConfigManager
import xyz.mahasamut.gun_game.scoreboard.ScoreboardUtils
import xyz.mahasamut.gun_game.utils.StringUtils

class ReloadCommand {

    @Command(name = "reload", desc = "Reload plugin.")
    @Require("gungame.reload")
    fun handler(@Sender sender: CommandSender) {
        ConfigManager.config.loadAndSave()
        ConfigManager.message.loadAndSave()
        ConfigManager.arena.loadAndSave()

        for (player in GunGame.instance.server.onlinePlayers) {
            ScoreboardUtils.removeScoreboard(player)
            ScoreboardUtils.createScoreboard(player)
        }

        sender.sendMessage(
            StringUtils.format(
                ConfigManager.message.reloaded,
                if (sender is Player) sender else null,
                true
            )
        )
    }

}