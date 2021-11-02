package net.id.aether.world.feature.structure.sliderdungeon;

import java.util.Random;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.SimpleStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;

public final class SliderDungeonStartPiece extends SimpleStructurePiece{
    public SliderDungeonStartPiece(StructureManager manager, BlockPos pos, BlockRotation rotation){
        super(SliderDungeonFeature.PIECE_START, 0, manager, SliderDungeonFeature.START, SliderDungeonFeature.START.toString(), createPlacementData(rotation), pos);
    }
    
    public SliderDungeonStartPiece(ServerWorld world, NbtCompound nbt){
        super(SliderDungeonFeature.PIECE_START, nbt, world, (identifier)->createPlacementData(BlockRotation.valueOf(nbt.getString("rotation"))));
    }
    
    private static StructurePlacementData createPlacementData(BlockRotation rotation){
        return new StructurePlacementData().addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS).setRotation(rotation);
    }
    
    @Override
    protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random, BlockBox boundingBox){}
    
    @Override
    protected void writeNbt(ServerWorld world, NbtCompound nbt){
        super.writeNbt(world, nbt);
        
        nbt.putString("rotation", getRotation().name());
    }
}
