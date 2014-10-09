package com.eztouch.wimp.init;

import com.eztouch.wimp.item.ItemLocatorCore;
import com.eztouch.wimp.item.ItemMagicEnderPearl;
import com.eztouch.wimp.item.ItemMagicMirror;
import com.eztouch.wimp.item.ItemMagicPaperPlane;
import com.eztouch.wimp.item.ItemPathFinder;
import com.eztouch.wimp.item.ItemWIMP;
import com.eztouch.wimp.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
	public static final ItemWIMP locatorCore = new ItemLocatorCore();
	public static final ItemWIMP magicMirror = new ItemMagicMirror();
	public static final ItemWIMP magicPaperPlane = new ItemMagicPaperPlane();
	public static final ItemWIMP magicEnderPearl = new ItemMagicEnderPearl();
	public static final ItemWIMP pathFinder = new ItemPathFinder();

	public static void init()
	{
		GameRegistry.registerItem(locatorCore, "Locator");
		GameRegistry.registerItem(magicMirror, "magicMirror");
		GameRegistry.registerItem(magicPaperPlane, "magicPaperPlane");
		GameRegistry.registerItem(magicEnderPearl, "magicEnderPearl");
		GameRegistry.registerItem(pathFinder, "pathFinder");
	}
}
