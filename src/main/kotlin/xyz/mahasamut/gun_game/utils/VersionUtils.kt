package xyz.mahasamut.gun_game.utils

import org.bukkit.Bukkit


/**
 * Para administrar las versiones del servidor.
 *
 * @author PedroJM96
 * @version 1.9 08-09-2022
 */
enum class VersionUtils(private val value: Int) {
    v1_5(10500), v1_6(10600), v1_7(10700), v1_8_8(10808), v1_8_9(10809), v1_9(10900), v1_9_1(10901), v1_9_2(10902), v1_9_3(
        10903
    ),
    v1_9_4(10904), v1_9_x(10910), v1_10(11000), v1_10_1(11001), v1_10_2(11002), v1_10_x(11010), v1_11(11100), v1_11_1(
        11101
    ),
    v1_11_2(11102), v1_11_x(11110), v1_12(11200), v1_12_1(11201), v1_12_2(11202), v1_12_x(11210), v1_13(11300), v1_13_1(
        11301
    ),
    v1_13_2(11302), v1_13_x(11310), v1_14(11400), v1_14_1(11401), v1_14_2(11402), v1_14_3(11403), v1_14_4(11404), v1_14_x(
        11410
    ),
    v1_15(11500), v1_15_1(11501), v1_15_2(11502), v1_15_x(11510), v1_16(11600), v1_16_1(11601), v1_16_2(11602), v1_16_3(
        11603
    ),
    v1_16_4(11604), v1_16_5(11605), v1_16_x(11610), v1_17(11700), v1_17_1(11701), v1_17_x(11710), v1_18(11800), v1_18_1(
        11801
    ),
    v1_18_2(11802), v1_18_x(11810), v1_19(11900), v1_19_1(11901), v1_19_2(11902), v1_19_3(11903), v1_19_x(11910), vUnsupported(
        1000000
    );

    private val contains: Boolean
    private val equals: Boolean

    init {
        contains =
            Bukkit.getBukkitVersion().split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0].contains(
                this.toString()
            )
        equals = Bukkit.getBukkitVersion().split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0].equals(
            this.toString(),
            ignoreCase = true
        )
    }

    fun Is(): Boolean {
        return contains
    }

    fun IsEquals(): Boolean {
        return equals
    }

    override fun toString(): String {
        return name.replace("_".toRegex(), ".").split("v".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
    }

    fun esMayorr(v: VersionUtils): Boolean {
        return value > v.value
    }

    fun esMayorIgual(v: VersionUtils): Boolean {
        return value >= v.value
    }

    fun esMenor(v: VersionUtils): Boolean {
        return value < v.value
    }

    fun esMenorIgual(v: VersionUtils): Boolean {
        return value <= v.value
    }

    companion object {
        val version: VersionUtils
            get() {
                var retorno = vUnsupported
                for (version in values()) {
                    if (version.IsEquals()) {
                        retorno = version
                        break
                    }
                }
                return retorno
            }
    }
}