package com.eztouch.wimp.init;

import java.util.Random;

import net.minecraft.entity.EntityList;

import com.eztouch.wimp.Wimp;
import com.eztouch.wimp.entity.EntityPaperPlane;
import com.eztouch.wimp.entity.EntityPaperPlaneBackup;
import com.eztouch.wimp.entity.EntityPathFinderTrail;
import com.eztouch.wimp.reference.Names;

import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities
{

	public static void init()
	{
		registerEntity(EntityPathFinderTrail.class, Names.Entities.pathFinderTrail);
		//registerEntity(EntityPaperPlaneBackup.class, Names.Entities.paperPlane);
		registerEntity(EntityPaperPlane.class, Names.Entities.paperPlane);
	}

	public static void registerEntity(Class entityClass, String name)
	{
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
		long seed = name.hashCode();
		Random rand = new Random(seed);
		int primaryColor = rand.nextInt() * 16777215;
		int secondaryColor = rand.nextInt() * 16777215;

		EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
		EntityRegistry.registerModEntity(entityClass, name, entityID, Wimp.instance, 64, 1, true);
		EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, primaryColor, secondaryColor));
	}
}
