package us.timinc.mc.cobblemon.ivbooster.config

import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment
import us.timinc.mc.cobblemon.ivbooster.IvBooster

@Config(name = IvBooster.MOD_ID)
class IvBoosterConfig : ConfigData {
    @Comment("The number of points each of these counter types grant")
    val koStreakPoints = 0
    val koCountPoints = 0
    val captureStreakPoints = 1
    val captureCountPoints = 0

    @Comment("The distance at which a spawning Pokemon considers a player")
    val effectiveRange = 64

    @Comment("Thresholds for the KO counts : shiny chance bonus")
    val thresholds: Map<Int, Int> = mutableMapOf(Pair(5, 1), Pair(10, 2), Pair(20, 3), Pair(30, 4))
}