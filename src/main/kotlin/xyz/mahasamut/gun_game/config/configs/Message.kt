package xyz.mahasamut.gun_game.config.configs

import de.exlll.configlib.configs.yaml.BukkitYamlConfiguration
import java.nio.file.Path

class Message(path: Path, properties: BukkitYamlProperties) : BukkitYamlConfiguration(path, properties) {

    var prefix = "&f[&6GunGame&f] &7"
    var onlyPlayer = "&7Only for player!"
    var unknownCommand = "&cUnknown command."
    var reloaded = "&aReloaded configs!"
    var setSpawn = "&aSet spawn!"
    var setPosOne = "&aPos 1 set!"
    var setPosTwo = "&aPos 2 set!"
    var toggledBuildMode = "&aToggled buildmode!"
    var cantAttackInThisArea = "&cYou can't attack player in spawn area"
    var killer = "&7You have killed &a%gungame_player% &7(&e%gungame_player_tier%&7)"
    var killed = "&7You were killed by &a%gungame_killer% &7(&e%gungame_killer_tier%&7)"

}