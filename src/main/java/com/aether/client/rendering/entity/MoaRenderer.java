package com.aether.client.rendering.entity;

import com.aether.client.model.entity.MoaModel;
import com.aether.client.rendering.entity.layer.AetherModelLayers;
import com.aether.entities.passive.MoaEntity;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class MoaRenderer extends MobEntityRenderer<MoaEntity, MoaModel> {

    public MoaRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new MoaModel(renderManager.getPart(AetherModelLayers.MOA)), 0.7f);
    }

    @Override
    protected void scale(MoaEntity moa, MatrixStack matrixStack, float partialTicks) {
        float moaScale = moa.isBaby() ? 0.3334F : 1.0F;
        matrixStack.scale(moaScale, moaScale, moaScale);
    }

    @Override
    public void render(MoaEntity moa, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(moa, f, g, matrixStack, vertexConsumerProvider, moa.getGenes().getRace().glowing() ? LightmapTextureManager.field_32767 : i);
    }

    @Override
    public Identifier getTexture(MoaEntity entity) {
        return entity.getGenes().getTexture();
    }
}
