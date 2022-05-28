package net.id.aether.structure;

import net.id.aether.world.feature.structure.AetherConfiguredStructureFeatures;
import net.minecraft.structure.StructureSet;
import net.minecraft.structure.StructureSets;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;

@SuppressWarnings("unused")
public interface AetherStructureSets {
    RegistryEntry<StructureSet> SLIDER_LABYRINTHS = StructureSets.register(
            AetherStructureSetKeys.SLIDER_LABYRINTHS,
            AetherConfiguredStructureFeatures.SLIDER_LABYRINTH,
            new RandomSpreadStructurePlacement(32, 8, SpreadType.LINEAR, 12847026));

    static void init() {
    }
}
