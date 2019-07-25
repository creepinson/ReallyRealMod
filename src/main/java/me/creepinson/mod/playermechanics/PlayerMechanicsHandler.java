package me.creepinson.mod.playermechanics;

import java.util.concurrent.Callable;

import me.creepinson.mod.Main;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class PlayerMechanicsHandler {

	public static final ResourceLocation PLAYERDATA_CAP = new ResourceLocation(Main.MODID, "player_data");

	public static void registerCapabilities() {
		CapabilityManager.INSTANCE.register(IPlayerData.class, new CapabilityPlayerMechanics(), new Factory());
	}

	private static class Factory implements Callable<IPlayerData> {

		@Override
		public IPlayerData call() throws Exception {
			return new PlayerData();
		}
	}

	@SubscribeEvent
	public static void attachCapability(AttachCapabilitiesEvent<EntityPlayer> event) {
		event.addCapability(PLAYERDATA_CAP, new PlayerMechanicsProvider());
	}

	public static class PlayerMechanicsProvider implements ICapabilitySerializable<NBTTagCompound> {
		@CapabilityInject(IPlayerData.class)
		public static final Capability<IPlayerData> PLAYERDATA_CAP = null;

		private IPlayerData instance = PLAYERDATA_CAP.getDefaultInstance();

		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
			return capability == PLAYERDATA_CAP;
		}

		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
			return capability == PLAYERDATA_CAP ? PLAYERDATA_CAP.<T>cast(this.instance) : null;
		}

		@Override
		public NBTTagCompound serializeNBT() {
			return (NBTTagCompound) PLAYERDATA_CAP.getStorage().writeNBT(PLAYERDATA_CAP, this.instance, null);
		}

		@Override
		public void deserializeNBT(NBTTagCompound nbt) {
			PLAYERDATA_CAP.getStorage().readNBT(PLAYERDATA_CAP, this.instance, null, nbt);
		}
	}

	public static class CapabilityPlayerMechanics implements IStorage<IPlayerData> {

		@Override
		public NBTBase writeNBT(Capability<IPlayerData> capability, IPlayerData instance, EnumFacing side) {
			NBTTagCompound nbt = new NBTTagCompound();
			return nbt;
		}

		@Override
		public void readNBT(Capability<IPlayerData> capability, IPlayerData instance, EnumFacing side, NBTBase nbt) {

		}

	}
}
