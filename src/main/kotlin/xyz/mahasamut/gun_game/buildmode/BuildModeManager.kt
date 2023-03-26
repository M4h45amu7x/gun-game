package xyz.mahasamut.gun_game.buildmode

import java.util.*

object BuildModeManager {

    private val players = mutableListOf<UUID>()

    fun isInBuildMode(player: UUID): Boolean {
        return players.contains(player)
    }

    fun toggleBuildMode(player: UUID) {
        if (isInBuildMode(player))
            players.remove(player)
        else
            players.add(player)
    }

}