package net.id.aether.world.feature.structure;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.JigsawFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class SliderLabyrinthFeature extends JigsawFeature {
    public SliderLabyrinthFeature(Codec<StructurePoolFeatureConfig> codec) {
        super(codec, 0, true, true, context -> true);
    }

    @Override
    public GenerationStep.Feature getGenerationStep() {
        return GenerationStep.Feature.SURFACE_STRUCTURES;
    }
}
