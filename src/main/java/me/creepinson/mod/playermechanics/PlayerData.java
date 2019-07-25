package me.creepinson.mod.playermechanics;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class PlayerData implements IPlayerData, INBTSerializable<NBTTagCompound> {

	private float tilt;

	public float getTilt() {
		return tilt;
	}

	public void setTilt(float tilt) {
		this.tilt = tilt;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setFloat("tilt", this.tilt);
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		this.tilt = nbt.getInteger("tilt");
	}
	
}
