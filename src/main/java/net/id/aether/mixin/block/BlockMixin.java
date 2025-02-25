package net.id.aether.mixin.block;

import net.id.aether.entities.AetherEntityExtensions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "onLandedUpon", at = @At("TAIL"))
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance, CallbackInfo ci) {
        if (entity instanceof AetherEntityExtensions aetherEntity) {
            aetherEntity.setAetherFallen(false);
            aetherEntity.setAerbunnyFallen(false);
        }
    }
}
