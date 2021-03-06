package com.eztouch.wimp.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import com.eztouch.wimp.entity.EntityPaperPlane;
import com.eztouch.wimp.reference.Names;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMagicPaperPlane extends ItemWIMP
{
	private static boolean creative = false;

	public ItemMagicPaperPlane()
	{
		super();
		this.setUnlocalizedName(Names.Items.MAGIC_PAPER_PLANE);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{

		if (!par2World.isRemote)
		{
			ChunkCoordinates coordinates = par3EntityPlayer.getBedLocation(0);
			if (coordinates == null)
			{
				coordinates = par2World.getSpawnPoint();
			}
			// //////EntityPaperPlane entityPaperPlane = new
			// EntityPaperPlane(par2World, par3EntityPlayer.posX,
			// par3EntityPlayer.posY + 1.62D - (double)
			// par3EntityPlayer.yOffset, par3EntityPlayer.posZ);
			EntityPaperPlane entityPaperPlane = new EntityPaperPlane(par2World, par3EntityPlayer.posX, par3EntityPlayer.posY + 1.62D - (double) par3EntityPlayer.yOffset, par3EntityPlayer.posZ);
			if (par3EntityPlayer.capabilities.isCreativeMode)
			{
				creative = true;
			} else
			{
				creative = false;
				--par1ItemStack.stackSize;
			}
			// //////entityPaperPlane.modeHelper(mode());
			// //////entityPaperPlane.moveTowards((double) coordinates.posX +
			// 0.5D, (int) coordinates.posY + 3, (double) coordinates.posZ +
			// 0.5D);
			entityPaperPlane.moveTowards((double) coordinates.posX + 0.5D, (int) coordinates.posY + 3, (double) coordinates.posZ + 0.5D);
			// //////par2World.spawnEntityInWorld(entityPaperPlane);
			par2World.spawnEntityInWorld(entityPaperPlane);
			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			par2World.playAuxSFXAtEntity((EntityPlayer) null, 1002, (int) par3EntityPlayer.posX, (int) par3EntityPlayer.posY, (int) par3EntityPlayer.posZ, 0);
		}

		return par1ItemStack;

	}

	public static boolean mode()
	{
		return creative;
	}

}