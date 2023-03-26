package xyz.mahasamut.gun_game.utils

import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

object ItemUtils {

    fun setUnbreakble(itemStack: ItemStack): ItemStack {
        val itemMeta = itemStack.itemMeta
        itemMeta.spigot().isUnbreakable = true
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)

        itemStack.itemMeta = itemMeta
        return itemStack
    }

}