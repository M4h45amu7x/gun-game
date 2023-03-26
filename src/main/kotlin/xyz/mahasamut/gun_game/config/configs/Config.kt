package xyz.mahasamut.gun_game.config.configs

import de.exlll.configlib.annotation.ConfigurationElement
import de.exlll.configlib.annotation.ElementType
import de.exlll.configlib.configs.yaml.BukkitYamlConfiguration
import xyz.mahasamut.gun_game.GunGame
import java.nio.file.Path

class Config(path: Path, properties: BukkitYamlProperties) : BukkitYamlConfiguration(path, properties) {

    var database = Database()
    var setTime = SetTime()
    var clearChatOnJoin = true
    var noWeather = true
    var disableMob = true
    var disableEntityExplode = true
    var disableItemMove = true
    var disableArmorMove = true
    var disableDropItem = true
    var disablePickupItem = true
    var disableHunger = true
    var disableFallDamage = true
    var disableFarmGrief = true
    var disableQuitMessage = true
    var disableJoinMessage = true
    var scoreboard = Scoreboard()
    var gameplay = Gameplay()

    @ConfigurationElement
    class Database {
        var type = "mariadb"
        var name = GunGame.instance.description.name
        var host = "127.0.0.1"
        var port = 3306
        var username = "root"
        var password = ""
    }

    @ConfigurationElement
    class SetTime {
        var enable = true
        var value = 6000L
    }

    @ConfigurationElement
    class Scoreboard {
        var interval = 20L
        var title = "&f&lGUNGAME.NET"
        var lines = arrayListOf(
            "&r",
            "&fTier",
            "&6%gungame_tier%",
            "&r&r",
            "&fKills",
            "&e%gungame_kills%",
            "&r&r&r",
            "&fTier Record",
            "&c%gungame_tier_record%",
            "&r&r&r&r",
            "&6#1 &a%gungame_top_name_1% &7- &c%gungame_top_tier_1%",
            "&7#2 &a%gungame_top_name_2% &7- &c%gungame_top_tier_2%",
            "&c#3 &a%gungame_top_name_3% &7- &c%gungame_top_tier_3%"
        )
    }

    @ConfigurationElement
    class Gameplay {
        var resetTierOnQuit = true
        var setExpRelatedToTier = true
        var autoRespawn = true
        var killSound = Sound(true, "LEVEL_UP")
        var deathSound = Sound(false, "ENDERMAN_DEATH")

        @ElementType(TierItem::class)
        var tierItem = mapOf(
            0 to TierItem(
                items = mapOf(
                    0 to "WOOD_SWORD"
                )
            )
        )

        @ConfigurationElement
        data class Sound(var enable: Boolean = false, var value: String = "")

        @ConfigurationElement
        data class TierItem(
            var helmet: String = "",
            var chestplate: String = "",
            var leggings: String = "",
            var boots: String = "",
            var items: Map<Int, String> = mapOf()
        )
    }
}
