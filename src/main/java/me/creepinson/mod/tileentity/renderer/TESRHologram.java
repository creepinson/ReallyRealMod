package me.creepinson.mod.tileentity.renderer;

import java.util.ArrayList;
import java.util.List;

import me.creepinson.mod.tileentity.TileEntityHologram;
import me.creepinson.mod.util.render.PortalRenderer;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class TESRHologram extends TileEntitySpecialRenderer<TileEntityHologram> {
	PortalRenderer render = new PortalRenderer();
	
	@Override
	public void renderTileEntityFast(TileEntityHologram te, double x, double y, double z, float partialTicks,
			int destroyStage, float partial, BufferBuilder buffer) {
		IBlockState state = Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK);
		BlockPos position = te.getPos();
		
		GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
    	GlStateManager.translate(x, y, z);
    	
    	GlStateManager.translate(0, 0, 0);
    	GlStateManager.scale(2, 2, 2);
    	
    	long angle = (System.currentTimeMillis() / 40) % 360;
    	GlStateManager.rotate(angle, 1, 1, 1);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        position.add(0.5, 0, 0.5);
        vertexbuffer.begin(7, DefaultVertexFormats.BLOCK);
        
        //GlStateManager.translate(position.getX(), position.getY(), position.getZ());
        
        BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        blockrendererdispatcher.getBlockModelRenderer().renderModel(te.getWorld(), blockrendererdispatcher.getModelForState(state), state, position, vertexbuffer, false, MathHelper.getPositionRandom(position));
        tessellator.draw();
    	GlStateManager.rotate(-angle, 1, 1, 1);
    	
    	GlStateManager.translate(0, 0, 0);
    	
    	GlStateManager.translate(-x, -y, -z);
    	GlStateManager.popMatrix();
        GlStateManager.popAttrib();
	}
	
	@Override
	public void render(TileEntityHologram te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		IBlockState state = Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK);
		BlockPos position = te.getPos();
		
		GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
    	GlStateManager.translate(x, y, z);
    	
    	GlStateManager.translate(0, 0, 0);
    	GlStateManager.scale(2, 2, 2);
    	
    	long angle = (System.currentTimeMillis() / 40) % 360;
    	GlStateManager.rotate(angle, 1, 1, 1);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        position.add(0.5, 0, 0.5);
        vertexbuffer.begin(7, DefaultVertexFormats.BLOCK);
        
        //GlStateManager.translate(position.getX(), position.getY(), position.getZ());
        
        BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        blockrendererdispatcher.getBlockModelRenderer().renderModel(te.getWorld(), blockrendererdispatcher.getModelForState(state), state, position, vertexbuffer, false, MathHelper.getPositionRandom(position));
        tessellator.draw();
    	GlStateManager.rotate(-angle, 1, 1, 1);
    	
    	GlStateManager.translate(0, 0, 0);
    	
    	GlStateManager.translate(-x, -y, -z);
    	GlStateManager.popMatrix();
        GlStateManager.popAttrib();
        
    }
   
	

}