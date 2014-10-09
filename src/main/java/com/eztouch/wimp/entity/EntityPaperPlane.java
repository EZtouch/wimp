package com.eztouch.wimp.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.eztouch.wimp.init.ModItems;
import com.eztouch.wimp.item.ItemMagicPaperPlane;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityPaperPlane extends Entity
{
	private double spawnX;
	private double spawnY;
	private double spawnZ;
	private int despawnTimer;
	private boolean shatterOrDrop;
	private static final String __OBFID = "CL_00001716";

	public EntityPaperPlane(World par2World)
	{
		super(par2World);
		this.setSize(0.25F, 0.25F);
	}

	protected void entityInit()
	{
	}

	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double distance)
	{
		double d1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
		d1 *= 64.0D;
		return distance < d1 * d1;
	}

	public EntityPaperPlane(World par2World, double playerX, double playerY, double playerZ)
	{
		super(par2World);
		this.despawnTimer = 0;
		this.setSize(0.25F, 0.25F);
		this.setPosition(playerX, playerY, playerZ);
		this.yOffset = 0.0F;
	}

	public void moveTowards(double goalX, int goalY, double goalZ)
	{
		double d2 = goalX - this.posX;
		double d3 = goalZ - this.posZ;
		float f = MathHelper.sqrt_double(d2 * d2 + d3 * d3);

		if (f > 20.0F)
		{
			this.spawnX = this.posX + d2 / (double) f * 12.0D;
			this.spawnZ = this.posZ + d3 / (double) f * 12.0D;
			this.spawnY = this.posY + 8.0D;
		} else
		{
			this.spawnX = goalX;
			this.spawnY = (double) goalY;
			this.spawnZ = goalZ;
		}

		this.despawnTimer = 0;

		this.shatterOrDrop = modeHelper(!ItemMagicPaperPlane.mode());
	}

	@SideOnly(Side.CLIENT)
	public void setVelocity(double cordX, double cordY, double cordZ)
	{
		this.motionX = cordX;
		this.motionY = cordY;
		this.motionZ = cordZ;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float f = MathHelper.sqrt_double(cordX * cordX + cordZ * cordZ);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(cordX, cordZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(cordY, (double) f) * 180.0D / Math.PI);
		}
	}

	public void onUpdate()
	{
		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;
		this.lastTickPosZ = this.posZ;
		super.onUpdate();
		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

		for (this.rotationPitch = (float) (Math.atan2(this.motionY, (double) f) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
		{
			;
		}

		while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
		{
			this.prevRotationPitch += 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw < -180.0F)
		{
			this.prevRotationYaw -= 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
		{
			this.prevRotationYaw += 360.0F;
		}

		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;

		if (!this.worldObj.isRemote)
		{
			double d0 = this.spawnX - this.posX;
			double d1 = this.spawnZ - this.posZ;
			float f1 = (float) Math.sqrt(d0 * d0 + d1 * d1);
			float f2 = (float) Math.atan2(d1, d0);
			double d2 = (double) f + (double) (f1 - f) * 0.0025D;

			if (f1 < 1.0F)
			{
				d2 *= 0.8D;
				this.motionY *= 0.8D;
			}

			this.motionX = Math.cos((double) f2) * d2;
			this.motionZ = Math.sin((double) f2) * d2;

			if (this.posY < this.spawnY)
			{
				this.motionY += (1.0D - this.motionY) * 0.014999999664723873D;
			} else
			{
				this.motionY += (-1.0D - this.motionY) * 0.014999999664723873D;
			}
		}

		float f3 = 0.25F;

		if (this.isInWater())
		{
			for (int i = 0; i < 4; ++i)
			{
				this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double) f3, this.posY - this.motionY * (double) f3, this.posZ - this.motionZ * (double) f3, this.motionX, this.motionY, this.motionZ);
			}
		} else
		{
			this.worldObj.spawnParticle("portal", this.posX - this.motionX * (double) f3 + this.rand.nextDouble() * 0.6D - 0.3D, this.posY - this.motionY * (double) f3 - 0.5D, this.posZ - this.motionZ * (double) f3 + this.rand.nextDouble() * 0.6D - 0.3D, this.motionX, this.motionY, this.motionZ);
		}

		if (!this.worldObj.isRemote)
		{
			this.setPosition(this.posX, this.posY, this.posZ);
			++this.despawnTimer;

			if (this.despawnTimer > 80 && !this.worldObj.isRemote)
			{
				this.setDead();

				if (this.shatterOrDrop)
				{
					this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(ModItems.magicPaperPlane)));
				} else
				{
					this.worldObj.playAuxSFX(2003, (int) Math.round(this.posX), (int) Math.round(this.posY), (int) Math.round(this.posZ), 0);
				}
			}
		}
	}

	public void writeEntityToNBT(NBTTagCompound nbtw1)
	{
	}

	public void readEntityFromNBT(NBTTagCompound nbtr1)
	{
	}

	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0F;
	}

	public float getBrightness(float brightness)
	{
		return 1.0F;
	}

	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float renderBrightness)
	{
		return 15728880;
	}

	public boolean canAttackWithItem()
	{
		return false;
	}

	public boolean modeHelper(boolean option)
	{
		return option;
	}

}