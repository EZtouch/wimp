package com.eztouch.wimp.item;

import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import com.eztouch.wimp.entity.EntityPathFinderTrail;
import com.eztouch.wimp.reference.Names;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPathFinder extends ItemWIMP
{
	private ChunkCoordinates coordinates;

	public ItemPathFinder()
	{
		super();
		this.setUnlocalizedName(Names.Items.pathFinder);
	}

	@SideOnly(Side.CLIENT)
	@Override
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
			EntityEnderEye entityPaperPlane = new EntityEnderEye(par2World,
					par3EntityPlayer.posX, par3EntityPlayer.posY + 1.62D
							- (double) par3EntityPlayer.yOffset,
					par3EntityPlayer.posZ);
			entityPaperPlane.moveTowards((double) coordinates.posX + 0.5D,
					(int) coordinates.posY + 3,
					(double) coordinates.posZ + 0.5D);
			par2World.spawnEntityInWorld(entityPaperPlane);
			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F,
					0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			par2World.playAuxSFXAtEntity((EntityPlayer) null, 1002,
					(int) par3EntityPlayer.posX, (int) par3EntityPlayer.posY,
					(int) par3EntityPlayer.posZ, 0);
			EntityPathFinderTrail entityPathFinder = new EntityPathFinderTrail(
					par2World);

			entityPathFinder.setPositionAndRotation(par3EntityPlayer.posX,
					par3EntityPlayer.posY, par3EntityPlayer.posZ, 0, 0);
			par2World.spawnEntityInWorld(entityPathFinder);
			if (par2World.isRemote)
			{
				if (coordinates != null)
				{
				}
			}
		}
		return par1ItemStack;
	}
}
