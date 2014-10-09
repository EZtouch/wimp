package com.eztouch.wimp.block;

import net.minecraft.block.material.Material;

import com.eztouch.wimp.creativeTab.CreativeTabWIMP;
import com.eztouch.wimp.reference.Names;

public class BlockLocationTable extends BlockWIMP
{
	public BlockLocationTable()
	{
		super(Material.wood);
		this.setBlockName(Names.Blocks.LOCATION_TABLE);
		this.setBlockTextureName(Names.Blocks.LOCATION_TABLE);
		this.setHardness(2.5f);
		this.setResistance(30.0f);
	}
}
