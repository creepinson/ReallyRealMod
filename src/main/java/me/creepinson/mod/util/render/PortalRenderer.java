package me.creepinson.mod.util.render;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PortalRenderer {

	public EnumFacing renderPortal(List<BlockPos> blocks) {
		Vector3f offset = new Vector3f(0.5f, 0.5f, 0.5f);
		for (BlockPos pos : blocks) {
			GlStateManager.translate(offset.x + pos.getX(), offset.y + pos.getY(), offset.z + pos.getZ());
			for (int i = 0; i < EnumFacing.values().length; i++) {
				EnumFacing facing = EnumFacing.values()[i];

				if (blocks.contains(pos.offset(facing)))
					return facing;
				if (facing == EnumFacing.UP)
					return facing;

				renderPartialPortalFace(this, facing);
			}
			GlStateManager.translate(0, 0, 0);
		}
		GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
		GL11.glPolygonOffset(-1f, -1f);
		Tessellator.getInstance().draw();
		GL11.glPolygonOffset(0f, 0f);
		
		GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
		return null;

	}

	public void renderBlock(World world, BlockPos position, IBlockState state) {
		GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.enableAlpha();
        //GlStateManager.alphaFunc(0, 0);
        
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        position.add(0.5, 0, 0.5);
        vertexbuffer.begin(7, DefaultVertexFormats.BLOCK);
        
        //GlStateManager.translate(position.getX(), position.getY(), position.getZ());
        
        BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        blockrendererdispatcher.getBlockModelRenderer().renderModel(world, blockrendererdispatcher.getModelForState(state), state, position, vertexbuffer, false, MathHelper.getPositionRandom(position));
        tessellator.draw();

        GlStateManager.enableLighting();
        GlStateManager.popMatrix();

	}
	
	
	private void renderPartialPortalFace(PortalRenderer portalRenderer, EnumFacing facing) {
		double xF = facing.getFrontOffsetX() * 0.5;
		double yF = facing.getFrontOffsetY() * 0.5;
		double zF = facing.getFrontOffsetZ() * 0.5;
		EnumFacing rotFacing;
		if (facing.getAxis() == EnumFacing.Axis.Y) {
			rotFacing = EnumFacing.NORTH;
		} else {
			rotFacing = EnumFacing.UP;
		}
		for (int i = 0; i < 3; i++) {
			Axis a = facing.getAxis();
			EnumFacing nextRotFacing = rotFacing.rotateAround(a);
			if (facing.getAxisDirection() != EnumFacing.AxisDirection.POSITIVE) {
				nextRotFacing = nextRotFacing.getOpposite();
			}

			Tessellator.getInstance().getBuffer()
					.pos(xF + rotFacing.getFrontOffsetX() * 0.5 + nextRotFacing.getFrontOffsetX() * 0.5 + 0.5,
							(yF + rotFacing.getFrontOffsetY() * 0.5 + nextRotFacing.getFrontOffsetY() * 0.5 + 0.5),
							zF + rotFacing.getFrontOffsetZ() * 0.5 + nextRotFacing.getFrontOffsetZ() * 0.5 + 0.5)
					.endVertex();
			rotFacing = nextRotFacing;
		}
	}

	public void renderPortalBlocks(Vec3d pos, List<BlockPos> blocks) {
		Vec3d offset = pos.subtract(new Vec3d(0.5, 0.5, 0.5));

		Tessellator tessellator = Tessellator.getInstance();
		tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

		for (BlockPos p : blocks) {
			GlStateManager.translate(offset.x + p.getX(), offset.y + p.getY(), offset.z + p.getZ());
			renderPortalBlock(blocks, pos);
		}

		GlStateManager.translate(0.0, 0.0, 0.0);

		Minecraft mc = Minecraft.getMinecraft();
		GlStateManager.color(1f, 1f, 1f);
		mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		RenderHelper.disableStandardItemLighting();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.enableBlend();
		tessellator.draw();
		GlStateManager.disableBlend();
		RenderHelper.enableStandardItemLighting();
	}

	private void renderPortalBlock(List<BlockPos> blocks, Vec3d pos) {
		EnumFacing facing = EnumFacing.DOWN;
		EnumFacing rotFacing;
		if (facing.getAxis() == EnumFacing.Axis.Y) {
			rotFacing = EnumFacing.NORTH;
		} else {
			rotFacing = EnumFacing.UP;
		}
		Axis a = facing.getAxis();
		EnumFacing nextRotFacing = rotFacing.rotateAround(a);
		if (facing.getAxisDirection() != EnumFacing.AxisDirection.POSITIVE) {
			nextRotFacing = nextRotFacing.getOpposite();
		}
		for (int i = 0; i < 3; i++) {
			Vec3d p = new Vec3d(rotFacing.getDirectionVec()).scale(0.5)
					.add(new Vec3d(rotFacing.getDirectionVec()).scale(0.5)).add(new Vec3d(0.5, 0.5, 0.5));
			Tessellator.getInstance().getBuffer().pos(p.x, p.y, p.z).color(1f, 1f, 1f, 1f);
			switch (i) {
			case 0:
				Tessellator.getInstance().getBuffer().tex(0, 0);
			case 1:
				Tessellator.getInstance().getBuffer().tex(0, 1);
			case 2:
				Tessellator.getInstance().getBuffer().tex(1, 1);
			case 3:
				Tessellator.getInstance().getBuffer().tex(1, 0);
			}
			Tessellator.getInstance().getBuffer().lightmap(240, 240).endVertex();
			rotFacing = nextRotFacing;
		}
	}

}
