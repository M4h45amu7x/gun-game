package xyz.mahasamut.gun_game.scoreboard

import fr.minuskube.netherboard.Netherboard
import fr.minuskube.netherboard.bukkit.BPlayerBoard
import org.bukkit.entity.Player
import xyz.mahasamut.gun_game.config.ConfigManager
import xyz.mahasamut.gun_game.utils.StringUtils

object ScoreboardUtils {

    fun createScoreboard(player: Player) {
        val config = ConfigManager.config.scoreboard
        val instance = Netherboard.instance()

        var board = instance.getBoard(player)
        if (board == null) board = instance.createBoard(player, StringUtils.format(config.title, player))

        board.apply {
            this.setAll(*config.lines.map {
                val formatted = StringUtils.format(it, player)

                formatted.substring(0, formatted.length.coerceAtMost(40))
            }.toTypedArray())
        }
    }

    fun removeScoreboard(player: Player) {
        Netherboard.instance().deleteBoard(player)
    }

    fun reloadScoreboard(player: Player) {
        removeScoreboard(player)
        createScoreboard(player)
    }

    fun getScoreboard(player: Player): BPlayerBoard = Netherboard.instance().getBoard(player)

    fun getScoreboards(): Map<Player, BPlayerBoard> = Netherboard.instance().boards

}