package us.timinc.mc.cobblemon.ivbooster.config

import draylar.omegaconfig.api.Comment
import draylar.omegaconfig.api.Config
import us.timinc.mc.cobblemon.ivbooster.IvBooster

class IvBoosterConfig : Config {
//    @Comment("The number of points each of these counter types grant")
    val koStreakPoints = 0
    val koCountPoints = 0
    val captureStreakPoints = 1
    val captureCountPoints = 0

//    @Comment("The distance at which a spawning Pokemon considers a player")
    val effectiveRange = 64

//    @Comment("Thresholds for the KO counts : shiny chance bonus")
    val thresholds: Map<Int, Int> = mutableMapOf(Pair(5, 1), Pair(10, 2), Pair(20, 3), Pair(30, 4))

    override fun getName(): String {
        return IvBooster.MOD_ID
    }
}