package xyz.mahasamut.gun_game.command

import com.jonahseguin.drink.Drink
import xyz.mahasamut.gun_game.GunGame
import xyz.mahasamut.gun_game.command.commands.GunGameCommand
import xyz.mahasamut.gun_game.command.commands.subs.*

object CommandManager {

    fun initCommands() {
        val drink = Drink.get(GunGame.instance)

        drink.register(GunGameCommand(), "gungame", "gg").registerSub(ReloadCommand()).registerSub(SetSpawnCommand())
            .registerSub(SetPos1Command()).registerSub(SetPos2Command()).registerSub(BuildModeCommand())

        drink.registerCommands()
    }

}