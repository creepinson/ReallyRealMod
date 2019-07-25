package me.creepinson.mod;

import org.apache.commons.logging.impl.AvalonLogger;
import org.lwjgl.input.Keyboard;

import me.creepinson.mod.entity.render.RenderCustomPlayer;
import me.creepinson.mod.playermechanics.IPlayerData;
import me.creepinson.mod.playermechanics.PlayerMechanicsHandler.PlayerMechanicsProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

@EventBusSubscriber
public class EventHandler {

	@SubscribeEvent
	public static void renderPlayerStart(RenderPlayerEvent.Pre e) {
		EntityPlayer p = e.getEntityPlayer();
		if (p instanceof AbstractClientPlayer) {
			e.setCanceled(true);
			RenderCustomPlayer rp = new RenderCustomPlayer(Minecraft.getMinecraft().getRenderManager(),
					((AbstractClientPlayer) p).getSkinType() == "slim");
			rp.doRender((AbstractClientPlayer) p, 0, 0, 0, p.rotationYaw, 0);
		}
	}

	@SubscribeEvent
	public static void playerFall(LivingFallEvent e) {

		if (e.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer) e.getEntityLiving();

		}
	}

	@SubscribeEvent
	public static void slowFall(PlayerTickEvent event) {
		EntityPlayer p = event.player;
		if (p.isAirBorne) {

		} else {
			return;
		}

		if (p.motionY <= 0) {
			// p.motionY *= 0.94D; // 0.94D looks the best imo
			// p.fallDistance -= p.motionY / 2; // avoid fall damage from

			IPlayerData data = p.getCapability(PlayerMechanicsProvider.PLAYERDATA_CAP, null);
			if (!p.capabilities.isFlying && p.motionY <= 0 && !p.onGround && !p.isSneaking()) {
				if (p.world.isRemote && Keyboard.isKeyDown(Keyboard.KEY_A)) {
					//p.rotationYaw -= 4;
					data.setTilt(data.getTilt()-4);
				} else if (p.world.isRemote && Keyboard.isKeyDown(Keyboard.KEY_D)) {
					//p.rotationYaw += 4;
					data.setTilt(data.getTilt()+4);
				}

			}
		} else

		{

		}

	}

}
