package com.eztouch.wimp.creativeTab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.eztouch.wimp.init.ModItems;
import com.eztouch.wimp.reference.Reference;

public class CreativeTabWIMP
{
	public static final CreativeTabs LMRB_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
	{
		@Override
		public Item getTabIconItem()
		{
			return ModItems.locatorCore;
		}
	};
}