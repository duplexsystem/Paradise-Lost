package net.id.aether.structure;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryEntry;

import java.util.function.Function;

public class StructureUtil {
    public static Pair<Function<StructurePool.Projection, ? extends StructurePoolElement>, Integer> processedSingle(
            Identifier id,
            int weight,
            RegistryEntry<StructureProcessorList> processor) {
        return Pair.of(projection -> new SinglePoolElement(Either.left(id), processor, projection) {}, weight);
    }
}
