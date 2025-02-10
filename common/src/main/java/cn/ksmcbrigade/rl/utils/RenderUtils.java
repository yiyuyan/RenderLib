package cn.ksmcbrigade.rl.utils;

import cn.ksmcbrigade.rl.Color;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class RenderUtils {
    public static void renderOutlineBlock(PoseStack poseStack, BlockPos pos, Color color){
        Matrix4f matrix4f = poseStack.last().pose();
        Minecraft MC = Minecraft.getInstance();
        if(MC.level==null) return;
        BlockState state = MC.level.getBlockState(pos);
        if(state.isAir()) return;
        Vec3 cameraPos = MC.gameRenderer.getMainCamera().getPosition();

        poseStack.pushPose();

        poseStack.translate(pos.getX()-cameraPos.x,pos.getY()-cameraPos.y, pos.getZ()-cameraPos.z);

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.begin(VertexFormat.Mode.DEBUG_LINE_STRIP, DefaultVertexFormat.POSITION_COLOR);

        state.getShape(MC.level,pos).forAllEdges((var1,var2,var3,var4,var5,var6)->{
            bufferBuilder.addVertex(matrix4f, (float) var1, (float) var2, (float) var3).setColor(color.toInt());
            bufferBuilder.addVertex(matrix4f, (float) var4, (float) var5, (float) var6).setColor(color.toInt());
        });

        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1f,1f,1f,1f);
        BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());

        poseStack.popPose();
    }

    public static void renderOutlineEntity(PoseStack poseStack, Entity entity, Color color){
        Minecraft MC = Minecraft.getInstance();
        Vec3 pos = entity.getPosition(0);
        Vec3 cameraPos = MC.gameRenderer.getMainCamera().getPosition();

        poseStack.pushPose();

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.begin(VertexFormat.Mode.DEBUG_LINE_STRIP, DefaultVertexFormat.POSITION_COLOR);

        final float width = entity.getBbWidth();
        final float height = entity.getBbHeight();
        final float x = (float) (pos.x - cameraPos.x - width/2), y = (float) (pos.y - cameraPos.y), z = (float) (pos.z - cameraPos.z - width/2);


        bufferBuilder.addVertex(x, y + height, z).setColor(color.toInt());
        bufferBuilder.addVertex(x + width, y + height, z).setColor(color.toInt());
        bufferBuilder.addVertex(x + width, y + height, z).setColor(color.toInt());
        bufferBuilder.addVertex(x + width, y + height, z + width).setColor(color.toInt());
        bufferBuilder.addVertex(x + width, y + height, z + width).setColor(color.toInt());
        bufferBuilder.addVertex(x, y + height, z + width).setColor(color.toInt());
        bufferBuilder.addVertex(x, y + height, z + width).setColor(color.toInt());
        bufferBuilder.addVertex(x, y + height, z).setColor(color.toInt());

        // BOTTOM
        bufferBuilder.addVertex(x + width, y, z).setColor(color.toInt());
        bufferBuilder.addVertex(x + width, y, z + width).setColor(color.toInt());
        bufferBuilder.addVertex(x + width, y, z + width).setColor(color.toInt());
        bufferBuilder.addVertex(x, y, z + width).setColor(color.toInt());
        bufferBuilder.addVertex(x, y, z + width).setColor(color.toInt());
        bufferBuilder.addVertex(x, y, z).setColor(color.toInt());
        bufferBuilder.addVertex(x, y, z).setColor(color.toInt());
        bufferBuilder.addVertex(x + width, y, z).setColor(color.toInt());

        // Edge 1
        bufferBuilder.addVertex(x + width, y, z + width).setColor(color.toInt());
        bufferBuilder.addVertex(x + width, y + height, z + width).setColor(color.toInt());

        // Edge 2
        bufferBuilder.addVertex(x + width, y, z).setColor(color.toInt());
        bufferBuilder.addVertex(x + width, y + height, z).setColor(color.toInt());

        // Edge 3
        bufferBuilder.addVertex(x, y, z + width).setColor(color.toInt());
        bufferBuilder.addVertex(x, y + height, z + width).setColor(color.toInt());

        // Edge 4
        bufferBuilder.addVertex(x, y, z).setColor(color.toInt());
        bufferBuilder.addVertex(x, y + height, z).setColor(color.toInt());

        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1f,1f,1f,1f);
        BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());

        poseStack.popPose();
    }

    public static void renderToEntityOutlineLines(PoseStack stack,Entity entity,Color color,float yOffset){
        Minecraft MC = Minecraft.getInstance();
        Vec3 cameraPos = MC.gameRenderer.getMainCamera().getPosition();
        if(MC.player==null) return;

        stack.pushPose();
        stack.translate(entity.getX()-cameraPos.x,entity.getY()-cameraPos.y,entity.getZ()-cameraPos.z);

        Tesselator tesselator = new Tesselator();
        BufferBuilder bufferBuilder = tesselator.begin(VertexFormat.Mode.DEBUG_LINE_STRIP,DefaultVertexFormat.POSITION_COLOR);

        float height = MC.player.getBbHeight();
        float height2 = entity.getBbHeight();

        bufferBuilder.addVertex((float) (MC.player.getX()-cameraPos.x), (float) (MC.player.getY()-cameraPos.y+height/2+yOffset), (float) (MC.player.getZ()-cameraPos.z)).setColor(color.toInt());
        bufferBuilder.addVertex((float) (entity.getX()-cameraPos.x), (float) (entity.getY()-cameraPos.y+height2/2+yOffset), (float) (entity.getZ()-cameraPos.z)).setColor(color.toInt());

        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());

        stack.popPose();
    }
}
