# IV Booster

Give perfect IVs to Pokémon based on everything from KO count to capture streak

## Help

[Discord](https://discord.com/invite/WKAR27SdSv)

## Features

### KO Counts & Streaks

Whenever a player defeats a wild Pokémon, if it’s of the same species as the species the player is currently on a KO streak for, the KO streak is incremented by 1. If a player defeats a wild Pokémon of a different species than their current streak, it resets to 1. Whenever a player defeats a wild Pokémon, a counter associated with that Pokémon’s species is increased for that player. This counter is never reset, and persists throughout the life of the player on that world.

### Perfect IVs

When a wild Pokémon spawns, the mod scans for nearby players and calculates their points based on their KO Streak, KO Count, Capture Streak and Capture Count for that Pokémon’s species, as well as their multiplies (see the Config Options section for more). If the nearby player with the most points has crossed a threshold, that threshold will determine how many perfect IVs the spawning Pokémon is given.

## Config Options

In the config, you can specify:

`koStreakPoints`, `koCountPoints`, `captureStreakPoints`, `captureCountPoints`

Each of these is a multiplier for their respective category. This multiplier is applied when considering how many points a player has for a particular species. For example, let’s take the below table which uses the multipliers from the default config, and we’ll assume the scores are the player’s score for Ekans:

| Category | Score | Multiplier | Total |
| --- | --- | --- | --- |
| KO Streak | 0 | 0 | 0 (0x0) |
| KO Count | 24 | 0 | 0 (24x0) |
| Capture Streak | 8 | 1 | 8 (8x1) |
| Capture Count | 16 | 0 | 0 (16x1) |

While the player in question has points in KO Count and Capture Count, these are ignored due to the multipliers being 0; their total score winds up being 8. This default config is meant to emulate the Let’s Go series’ capture streak combo (on this page, under the `Catch Combo Rewards - IVs` section https://www.serebii.net/letsgopikachueevee/catchcombo.shtml).

Let’s spice it up a bit and come up with our own config. We’ll keep the same player.

| Category | Score | Multiplier | Total |
| --- | --- | --- | --- |
| KO Streak | 0 | 0 | 0 |
| KO Count | 24 | 1 | 24 (24x1) |
| Capture Streak | 8 | 10 | 80 (8x10) |
| Capture Count | 16 | 0 | 0 (16x0) |

So now the player gets 1 point for every KO Count point they have and 10 points for every Capture Streak point they have; their total score winds up being 104. This, in combination with the thresholds system, lets you figure out how much of a certain task a player should do before getting enough points to cross a threshold.

`thresholds`

Thresholds determine how many points from the above points system a player should earn before they unlock bonus perfect IVs. In the default config, the player needs more than 5 points to get 1 perfect IV, more than 10 for 2, more than 20 for 3, and more than 30 for 4. Together with the default multipliers, these follow the same logic in the Serebii `Catch Combo Rewards - IVs` link above.

`effectiveRange`

The distance in blocks at which a spawning Pokémon will take into account a nearby player.

## Dependencies

Cobblemon [Modrinth](https://modrinth.com/mod/cobblemon) / [CurseForge](https://www.curseforge.com/minecraft/mc-mods/cobblemon)

Fabric Language Kotlin [Modrinth](https://modrinth.com/mod/fabric-language-kotlin) / [CurseForge](https://www.curseforge.com/minecraft/mc-mods/fabric-language-kotlin)

Cobblemon Counter [Modrinth](https://modrinth.com/mod/cobblemon-counter) / [CurseForge](https://www.curseforge.com/minecraft/mc-mods/cobblemon-counter)

Cloth Config [Modrinth](https://modrinth.com/mod/cloth-config) / [CurseForge](https://www.curseforge.com/minecraft/mc-mods/cloth-config)
