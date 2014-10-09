package com.eztouch.wimp.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.eztouch.wimp.reference.Reference;

public class EntityRender extends RenderBiped
{
	private static final ResourceLocation textureLocation = new ResourceLocation(Reference.MOD_ID.toLowerCase() + ":" + "models/char.png");

	public EntityRender(ModelBiped model, float shadowSize)
	{
		super(model, shadowSize);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return textureLocation;
	}
}