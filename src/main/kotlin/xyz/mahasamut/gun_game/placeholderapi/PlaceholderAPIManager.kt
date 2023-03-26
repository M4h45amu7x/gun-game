package xyz.mahasamut.gun_game.placeholderapi

import xyz.mahasamut.gun_game.placeholderapi.expansions.GunGameExpansion

object PlaceholderAPIManager {

    fun initPlaceholderAPIs() {
        GunGameExpansion().register()
    }

}