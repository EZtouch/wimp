package com.eztouch.wimp.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import com.eztouch.wimp.reference.Reference;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler
{
	public static Configuration configuration;
	public static boolean testValue = false;

	public static void init(File configFile)
	{
		// create the configuration object from the given configuration file
		if (configuration == null)
		{
			configuration = new Configuration(configFile);
			loadConfiguration();
		}
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
		{
			loadConfiguration();
		}
	}

	private static void loadConfiguration()
	{
		testValue = configuration.getBoolean("configValue", configuration.CATEGORY_GENERAL, false, "This is an example configuration value");

		if (configuration.hasChanged())
		{
			configuration.save();
		}
	}
}
