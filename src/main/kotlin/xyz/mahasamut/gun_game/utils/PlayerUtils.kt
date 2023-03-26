package xyz.mahasamut.gun_game.utils

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import xyz.mahasamut.gun_game.config.ConfigManager

object PlayerUtils {

    fun isPlayerInArea(player: Player, loc1: Location, loc2: Location): Boolean {
        val minX = loc1.x.coerceAtMost(loc2.x)
        val maxX = loc1.x.coerceAtLeast(loc2.x)
        val minY = loc1.y.coerceAtMost(loc2.y)
        val maxY = loc1.y.coerceAtLeast(loc2.y)
        val minZ = loc1.z.coerceAtMost(loc2.z)
        val maxZ = loc1.z.coerceAtLeast(loc2.z)
        val playerLoc = player.location

        return playerLoc.x in minX..maxX && playerLoc.y in minY..maxY && playerLoc.z in minZ..maxZ
    }

    fun setPlayerTierItems(player: Player, tier: Int) {
        val tierItem = ConfigManager.config.gameplay.tierItem
        var targetTierItem = tierItem[tier.coerceAtMost(tierItem.size - 1)]

        if (targetTierItem != null) {
            if (player.health < player.maxHealth) {
                player.removePotionEffect(PotionEffectType.REGENERATION)
                player.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 20, 999))
            }

            val airItem = ItemStack(Material.AIR)

            player.inventory.clear()
            player.inventory.helmet =
                if (targetTierItem.helmet.isNotEmpty()) ItemUtils.setUnbreakble(
                    ItemStack(
                        MaterialUtils.getMaterial(
                            targetTierItem.helmet
                        )
                    )
                ) else airItem
            player.inventory.chestplate =
                if (targetTierItem.chestplate.isNotEmpty()) ItemUtils.setUnbreakble(
                    ItemStack(
                        MaterialUtils.getMaterial(
                            targetTierItem.chestplate
                        )
                    )
                ) else airItem
            player.inventory.leggings =
                if (targetTierItem.leggings.isNotEmpty()) ItemUtils.setUnbreakble(
                    ItemStack(
                        MaterialUtils.getMaterial(
                            targetTierItem.leggings
                        )
                    )
                ) else airItem
            player.inventory.boots = if (targetTierItem.boots.isNotEmpty()) ItemUtils.setUnbreakble(
                ItemStack(
                    MaterialUtils.getMaterial(targetTierItem.boots)
                )
            ) else airItem

            for (item in targetTierItem.items)
                player.inventory.setItem(
                    item.key,
                    ItemUtils.setUnbreakble(ItemStack(MaterialUtils.getMaterial(item.value)))
                )
        }
    }

    fun setPlayerEXP(player: Player, tier: Int) {
        player.level = tier
    }

}