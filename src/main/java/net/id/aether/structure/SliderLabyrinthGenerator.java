package net.id.aether.structure;

import com.google.common.collect.ImmutableList;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.structure.processor.StructureProcessorLists;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryEntry;

import static net.id.aether.Aether.locate;
import static net.id.aether.structure.StructureUtil.processedSingle;

public class SliderLabyrinthGenerator {
    public static final RegistryEntry<StructurePool> STRUCTURE_POOLS = StructurePools.register(new StructurePool(
            locate("labyrinth/arenas"),
            new Identifier("empty"),
            ImmutableList.of(
                    processedSingle(locate("labyrinth/arenas/test"),
                            1,
                            StructureProcessorLists.EMPTY)),
            StructurePool.Projection.RIGID));

    public static void init() {
        StructurePools.register(new StructurePool(
                locate("labyrinth/hallways"),
                new Identifier("empty"),
                ImmutableList.of(
                        processedSingle(locate("labyrinth/hallways/test_straight"),
                                8,
                                StructureProcessorLists.EMPTY),
                        processedSingle(locate("labyrinth/hallways/test_corner"),
                                3,
                                StructureProcessorLists.EMPTY),
                        processedSingle(locate("labyrinth/hallways/test_start"),
                                2,
                                StructureProcessorLists.EMPTY),
                        processedSingle(locate("labyrinth/hallways/test_straight_branch"),
                                5,
                                StructureProcessorLists.EMPTY)),
                StructurePool.Projection.RIGID));

        StructurePools.register(new StructurePool(
                locate("labyrinth/corridors"),
                new Identifier("empty"),
                ImmutableList.of(
                        processedSingle(locate("labyrinth/corridors/test_straight"),
                                2,
                                StructureProcessorLists.EMPTY),
                        processedSingle(locate("labyrinth/corridors/test_end"),
                                1,
                                StructureProcessorLists.EMPTY)),
                StructurePool.Projection.RIGID));
    }
}
