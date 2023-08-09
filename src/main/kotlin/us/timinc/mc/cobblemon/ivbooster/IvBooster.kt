package us.timinc.mc.cobblemon.ivbooster

import com.cobblemon.mod.common.api.spawning.context.SpawningContext
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.IVs
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer
import net.fabricmc.api.ModInitializer
import net.minecraft.world.entity.ai.targeting.TargetingConditions
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import us.timinc.mc.cobblemon.counter.Counter
import us.timinc.mc.cobblemon.ivbooster.config.IvBoosterConfig

object IvBooster : ModInitializer {
    const val MOD_ID = "ivbooster"
    private lateinit var ivBoosterConfig: IvBoosterConfig

    override fun onInitialize() {
        AutoConfig.register(IvBoosterConfig::class.java) { definition, configClass ->
            JanksonConfigSerializer(definition, configClass)
        }

        ivBoosterConfig = AutoConfig.getConfigHolder(IvBoosterConfig::class.java).config
    }

    @Suppress("KotlinConstantConditions")
    private fun getPoints(player: Player, species: String): Int {
        return (Counter.getPlayerKoStreak(
            player, species
        ) * ivBoosterConfig.koStreakPoints) + (Counter.getPlayerKoCount(
            player, species
        ) * ivBoosterConfig.koCountPoints) + (Counter.getPlayerCaptureStreak(
            player, species
        ) * ivBoosterConfig.captureStreakPoints) + (Counter.getPlayerCaptureCount(
            player, species
        ) * ivBoosterConfig.captureCountPoints)
    }

    private fun getPerfectIvCountFromPoints(points: Int): Int {
        return ivBoosterConfig.thresholds.maxOfOrNull { if (it.key < points) it.value else 0 } ?: 0
    }

    fun possiblyModifyIvs(entity: PokemonEntity, ctx: SpawningContext) {
        val world: Level = ctx.world
        val species = entity.pokemon.species.name.lowercase()

        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS") val possibleMaxPlayer = world.getNearbyPlayers(
            TargetingConditions.forNonCombat().ignoreLineOfSight().ignoreInvisibilityTesting(), null, AABB.ofSize(
                Vec3.atCenterOf(ctx.position),
                ivBoosterConfig.effectiveRange.toDouble(),
                ivBoosterConfig.effectiveRange.toDouble(),
                ivBoosterConfig.effectiveRange.toDouble()
            )
        ).stream().max(Comparator.comparingInt { player: Player? ->
            getPoints(player!!, species)
        })
        if (possibleMaxPlayer.isEmpty) {
            return
        }

        val maxPlayer = possibleMaxPlayer.get()
        val perfectIvs = getPerfectIvCountFromPoints(getPoints(maxPlayer, species))

        val newIvs = IVs.createRandomIVs(perfectIvs)
        for (newIv in newIvs) {
            if (newIv.value == IVs.MAX_VALUE) {
                entity.pokemon.ivs[newIv.key] = IVs.MAX_VALUE
            }
        }
    }
}