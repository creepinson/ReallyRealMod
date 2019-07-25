package me.creepinson.mod.util.proxy;

import me.creepinson.mod.Main;
import me.creepinson.mod.entity.render.RenderCustomPlayer;
import me.creepinson.mod.tileentity.TileEntityHologram;
import me.creepinson.mod.tileentity.renderer.TESRHologram;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	/**
	 * Run before anything else. Read your config, create blocks, items, etc, and
	 * register them with the GameRegistry
	 */
	@Override
	public void preInit() {

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHologram.class, new TESRHologram());
		
		
	}

	/**
	 * Do your mod setup. Build whatever data structures you care about. Register
	 * recipes, send FMLInterModComms messages to other mods.
	 */
	@Override
	public void init() {
		
		
	
	}

	/**
	 * Handle interaction with other mods, complete your setup based on this.
	 */
	@Override
	public void postInit() {

	}

	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(Main.MODID + ":" + id, "inventory"));
	}
}