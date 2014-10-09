package com.eztouch.wimp.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes
{
	public static void init()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.locatorCore), "rfr", "iii", "ppp", 'r', new ItemStack(Items.redstone), 'i', "dye", 'p', new ItemStack(Items.paper), 'f', new ItemStack(Items.feather)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.magicEnderPearl), new ItemStack(Items.ender_pearl)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.magicMirror), "igi", "gdg", "ixi", 'i', "ingotIron", 'g', "blockGlass", 'd', "gemDiamond", 'x', new ItemStack(ModItems.locatorCore)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.magicPaperPlane), "xsx", "sss", "sss", 's', new ItemStack(Items.paper), 'x', new ItemStack(ModItems.locatorCore)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.locationTable), new ItemStack(Blocks.crafting_table), new ItemStack(ModItems.locatorCore)));

	}
}
