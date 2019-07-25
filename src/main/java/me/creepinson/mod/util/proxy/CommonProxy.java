package me.creepinson.mod.util.proxy;

import net.minecraft.item.Item;

public class CommonProxy {

	/**
	 * Run before anything else. Read your config, create blocks, items, etc, and
	 * register them with the GameRegistry
	 */
	public void preInit() {

	}

	/**
	 * Do your mod setup. Build whatever data structures you care about. Register
	 * recipes, send FMLInterModComms messages to other mods.
	 */
	public void init() {

	}

	/**
	 * Handle interaction with other mods, complete your setup based on this.
	 */
	public void postInit() {

	}

	public void registerItemRenderer(Item item, int meta, String id) {
	}
}