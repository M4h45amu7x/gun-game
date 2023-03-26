package xyz.mahasamut.gun_game.config

import de.exlll.configlib.configs.yaml.BukkitYamlConfiguration.BukkitYamlProperties
import de.exlll.configlib.format.FieldNameFormatters
import xyz.mahasamut.gun_game.GunGame
import xyz.mahasamut.gun_game.config.configs.Arena
import xyz.mahasamut.gun_game.config.configs.Config
import xyz.mahasamut.gun_game.config.configs.Message
import java.io.File


object ConfigManager {
    private val properties = BukkitYamlProperties.builder().setFormatter(FieldNameFormatters.UPPER_UNDERSCORE).build()

    lateinit var config: Config
    lateinit var message: Message
    lateinit var arena: Arena

    fun initConfigs() {
        val dataFolder = GunGame.instance.dataFolder

        config = Config(File(dataFolder, "config.yml").toPath(), properties)
        config.loadAndSave()

        message = Message(File(dataFolder, "message.yml").toPath(), properties)
        message.loadAndSave()

        arena = Arena(File(dataFolder, "arena.yml").toPath(), properties)
        arena.loadAndSave()
    }
}