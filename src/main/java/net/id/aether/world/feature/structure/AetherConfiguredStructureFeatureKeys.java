package net.id.aether.world.feature.structure;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;

import static net.id.aether.Aether.locate;

public interface AetherConfiguredStructureFeatureKeys {
    RegistryKey<ConfiguredStructureFeature<?, ?>> SLIDER_LABYRINTH = of("slider_labyrinth");

    private static RegistryKey<ConfiguredStructureFeature<?, ?>> of(String id) {
        return RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, locate(id));
    }
}
