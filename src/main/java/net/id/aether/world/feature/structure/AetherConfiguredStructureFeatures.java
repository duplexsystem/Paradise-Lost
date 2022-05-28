package net.id.aether.world.feature.structure;

import net.id.aether.structure.SliderLabyrinthGenerator;
import net.id.aether.tag.AetherBiomeTags;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.*;

public class AetherConfiguredStructureFeatures {
    public static final RegistryEntry<ConfiguredStructureFeature<?, ?>> SLIDER_LABYRINTH = register(
            AetherConfiguredStructureFeatureKeys.SLIDER_LABYRINTH,
            AetherStructureFeatures.SLIDER_LABYRINTH.configure(
                    new StructurePoolFeatureConfig(SliderLabyrinthGenerator.STRUCTURE_POOLS, 64),
                    AetherBiomeTags.SLIDER_LABYRINTH_HAS_STRUCTURE));

    public static void init() {
    }

    private static RegistryEntry<ConfiguredStructureFeature<?, ?>> register(
            RegistryKey<ConfiguredStructureFeature<?, ?>> key,
            ConfiguredStructureFeature<?, ?> configuredStructureFeature) {
        return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, key, configuredStructureFeature);
    }
}
