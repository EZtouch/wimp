package com.eztouch.wimp.init;

import com.eztouch.wimp.block.BlockLocationTable;
import com.eztouch.wimp.block.BlockWIMP;
import com.eztouch.wimp.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
	public static final BlockWIMP locationTable = new BlockLocationTable();

	public static void init()
	{
		GameRegistry.registerBlock(locationTable, "locationTable");
	}
}
