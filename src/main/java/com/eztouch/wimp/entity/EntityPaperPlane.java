package com.eztouch.wimp.entity;

import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.world.World;

public class EntityPaperPlane extends EntityEnderEye
{

	private double spawnX;
	private double spawnY;
	private double spawnZ;
	private int despawnTimer;
	private boolean shatterOrDrop;

	public EntityPaperPlane(World par2World)
	{
		super(par2World);
	}

	public EntityPaperPlane(World par2World, double playerX, double playerY, double playerZ)
	{
		super(par2World, playerX, playerY, playerZ);
	}

}
