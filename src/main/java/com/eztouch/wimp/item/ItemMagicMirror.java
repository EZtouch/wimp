package com.eztouch.wimp.item;

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
		this.setUnlocalizedName("magicMirror");
		this.maxStackSize = 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer)
	{

		if (!par2World.isRemote)
		{
			/* LogHelper.info("?bed=" + par3EntityPlayer.getBedLocation(0)); */

			// Get the player's bed cords
			coordinates = par3EntityPlayer.getBedLocation(0);
			/* LogHelper.info("?coordinates=" + coordinates); */

			// If the player didn't have a bed
			if (coordinates == null)
			{
				// Get the world's spawn cords
				coordinates = par2World.getSpawnPoint();
			}

			// Sound effect indicator
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

				/*
				 * LogHelper.info("?dis=" +
				 * par3EntityPlayer.verifyRespawnCoordinates(par2World,
				 * par3EntityPlayer.getBedLocation(0), true));
				 */

				/*
				 * par3EntityPlayer .setPosition((par3EntityPlayer
				 * .verifyRespawnCoordinates(par2World,
				 * par3EntityPlayer.getBedLocation(0), true).posX),
				 * par3EntityPlayer .verifyRespawnCoordinates(par2World,
				 * par3EntityPlayer.getBedLocation(0), true).posY,
				 * par3EntityPlayer .verifyRespawnCoordinates(par2World,
				 * par3EntityPlayer.getBedLocation(0), true).posZ);
				 */

				/*
				 * LogHelper.info("THIS!:" +
				 * par3EntityPlayer.verifyRespawnCoordinates(par2World,
				 * coordinates, true));
				 */
			}
		}

		return par1ItemStack;
	}
}
