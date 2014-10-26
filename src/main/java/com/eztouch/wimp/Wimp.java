package com.eztouch.wimp;

import com.eztouch.wimp.handler.ConfigurationHandler;
import com.eztouch.wimp.handler.KeyInputEventHandler;
import com.eztouch.wimp.init.ModBlocks;
import com.eztouch.wimp.init.ModEntities;
import com.eztouch.wimp.init.ModItems;
import com.eztouch.wimp.init.Recipes;
import com.eztouch.wimp.proxy.ClientProxy;
import com.eztouch.wimp.proxy.IProxy;
import com.eztouch.wimp.reference.Reference;
import com.eztouch.wimp.utility.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class Wimp
{
	@Mod.Instance(Reference.MOD_ID)
	public static Wimp instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

		proxy.registerKeyBindings();
		proxy.registerRenderers();

		ClientProxy clientP = new ClientProxy();
		clientP.registerRenderers();
		clientP.registerKeyBindings();

		ModItems.init();
		ModBlocks.init();
		ModEntities.init();

		LogHelper.info("Pre Initialization Complete");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());

		Recipes.init();

		LogHelper.info("Initialization Complete");
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		LogHelper.info("Post Initialization Complete");
	}

}
