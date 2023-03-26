package xyz.mahasamut.gun_game.config.configs

import de.exlll.configlib.annotation.ConfigurationElement
import de.exlll.configlib.configs.yaml.BukkitYamlConfiguration
import java.nio.file.Path


class Arena(path: Path, properties: BukkitYamlProperties) : BukkitYamlConfiguration(path, properties) {

    var world = "world"
    var spawn = Location()
    var borders = Borders()

    @ConfigurationElement
    class Location {
        var x = 0.0
        var y = 0.0
        var z = 0.0
        var yaw = 0f
        var pitch = 0f
    }

    @ConfigurationElement
    class Borders {

        var posOne = Pos()
        var posTwo = Pos()

        @ConfigurationElement
        class Pos {
            var x = 0.0
            var y = 0.0
            var z = 0.0
        }
    }

}