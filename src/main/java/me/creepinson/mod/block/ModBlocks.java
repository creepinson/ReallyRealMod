package me.creepinson.mod.block;

import me.creepinson.mod.Main;
import me.creepinson.mod.tileentity.TileEntityHologram;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
public class ModBlocks {

	public static BlockBase oreCopper = new BlockHologram(Material.CLOTH, "ore_copper").setCreativeTab(CreativeTabs.MATERIALS);

	public static void register(IForgeRegistry<Block> registry) {
		registry.registerAll(
				oreCopper
		);
		GameRegistry.registerTileEntity(TileEntityHologram.class, new ResourceLocation(Main.MODID, "hologram"));
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		registry.registerAll(
				oreCopper.createItemBlock()
		);
	}
	
	public static void registerModels() {
		oreCopper.registerItemModel(Item.getItemFromBlock(oreCopper));
	}

}