package com.eztouch.wimp.item;

import com.eztouch.wimp.reference.Names;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMagicMirror extends ItemWIMP
{
	private ChunkCoordinates coordinates;

	public ItemMagicMirror()
	{
		super();
		this.setUnlocalizedName(Names.Items.magicMirror);
		this.maxStackSize = 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer)
	{

		if (!par2World.isRemote)
		{
			coordinates = par3EntityPlayer.getBedLocation(0);
			if (coordinates == null)
			{
				coordinates = par2World.getSpawnPoint();
			}
			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F,
					0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		}
		if (par2World.isRemote)
		{
			if (coordinates != null)
			{

				par3EntityPlayer.setPosition((double) coordinates.posX + 0.5D,
						(double) coordinates.posY + 3,
						(double) coordinates.posZ + 0.5D);
			}
		}
		return par1ItemStack;
	}
}
