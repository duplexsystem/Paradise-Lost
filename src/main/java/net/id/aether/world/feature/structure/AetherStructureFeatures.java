package net.id.aether.world.feature.structure;

import net.id.aether.structure.OrangeRuinGenerator;
import net.id.aether.structure.SkyrootTowerGenerator;
import net.id.aether.structure.WellGenerator;
import net.id.aether.world.feature.configs.SliderLabyrinthFeatureConfig;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import static net.id.aether.Aether.locate;

@SuppressWarnings("unused")
public class AetherStructureFeatures {
    public static final WellFeature WELL = register("well", new WellFeature(DefaultFeatureConfig.CODEC));
    public static final SkyrootTowerFeature SKYROOT_TOWER = register("skyroot_tower", new SkyrootTowerFeature(DefaultFeatureConfig.CODEC));
    public static final SliderLabyrinthFeature SLIDER_LABYRINTH = register("slider_labyrinth", new SliderLabyrinthFeature(SliderLabyrinthFeatureConfig.CODEC));
    public static final OrangeRuinFeature ORANGE_RUIN = register("orange_ruin", new OrangeRuinFeature(DefaultFeatureConfig.CODEC));

    public static final StructurePieceType WELL_PIECE = register(WellGenerator.Piece::new, "well");
    public static final StructurePieceType SKYROOT_TOWER_PIECE = register(SkyrootTowerGenerator.Piece::new, "skyroot_tower");
    public static final StructurePieceType ORANGE_RUIN_PIECE = register(OrangeRuinGenerator.Piece::new, "orange_ruin");

    public static void init() {
    }

    private static <T extends StructureFeature<?>> T register(String id, T structure) {
        return Registry.register(Registry.STRUCTURE_FEATURE, locate(id), structure);
    }

    private static StructurePieceType register(StructurePieceType pieceType, String id) {
        return Registry.register(Registry.STRUCTURE_PIECE, locate(id), pieceType);
    }
}
