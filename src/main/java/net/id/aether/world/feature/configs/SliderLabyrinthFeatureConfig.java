package net.id.aether.world.feature.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class SliderLabyrinthFeatureConfig extends StructurePoolFeatureConfig {
    public static final Codec<StructurePoolFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    StructurePool.REGISTRY_CODEC.fieldOf("start_pool")
                            .forGetter(StructurePoolFeatureConfig::getStartPool),
                    Codec.INT.fieldOf("size")
                            .forGetter(StructurePoolFeatureConfig::getSize))
            .apply(instance, SliderLabyrinthFeatureConfig::new));

    public SliderLabyrinthFeatureConfig(RegistryEntry<StructurePool> startPool, int size) {
        super(startPool, size);
    }
}
