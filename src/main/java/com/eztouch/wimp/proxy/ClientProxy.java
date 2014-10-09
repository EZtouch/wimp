package com.eztouch.wimp.proxy;

import net.minecraft.client.model.ModelBiped;

import com.eztouch.wimp.client.settings.Keybindings;
import com.eztouch.wimp.entity.EntityPaperPlane;
import com.eztouch.wimp.entity.EntityPathFinderTrail;
import com.eztouch.wimp.render.EntityRender;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerKeyBindings()
	{
		ClientRegistry.registerKeyBinding(Keybindings.charge);
		ClientRegistry.registerKeyBinding(Keybindings.release);
	}

	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(
				EntityPathFinderTrail.class, new EntityRender(new ModelBiped(),
						0.5F));
		RenderingRegistry.registerEntityRenderingHandler(
				EntityPaperPlane.class, new EntityRender(new ModelBiped(),
						0.5F));
	}
}