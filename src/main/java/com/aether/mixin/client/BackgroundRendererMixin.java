package com.aether.mixin.client;

import com.aether.fluids.DenseAercloudFluid;
import com.aether.util.RegistryUtil;
import com.aether.world.dimension.AetherDimension;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
    @Redirect(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraft/util/math/Vec3d;y:D", opcode = Opcodes.GETFIELD, ordinal = 1))
    private static double adjustVoidVector(Vec3d vec3d) {
        return RegistryUtil.dimensionMatches(MinecraftClient.getInstance().world, AetherDimension.TYPE) ? Double.MAX_VALUE : vec3d.y;
    }

    @Shadow private static float red;
    @Shadow private static float green;
    @Shadow private static float blue;

    @Environment(EnvType.CLIENT)
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;getPos()Lnet/minecraft/util/math/Vec3d;"))
    private static void denseAercloudRenderColor(Camera camera, float tickDelta, ClientWorld world, int i, float f, CallbackInfo ci){
        BlockPos playerPos = new BlockPos(MinecraftClient.getInstance().player.getEyePos());
        if(camera.getSubmersionType() == CameraSubmersionType.WATER
                && world.getFluidState(playerPos).getFluid() instanceof DenseAercloudFluid) {
            red = 0.323F;
            green = 0.434F;
            blue = 0.485F;
        }
    }
}
