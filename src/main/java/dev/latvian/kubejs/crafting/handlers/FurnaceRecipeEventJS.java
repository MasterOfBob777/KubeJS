package dev.latvian.kubejs.crafting.handlers;

import dev.latvian.kubejs.events.EventJS;
import dev.latvian.kubejs.item.IIngredientJS;
import dev.latvian.kubejs.item.ItemStackJS;
import net.minecraft.item.crafting.FurnaceRecipes;

/**
 * @author LatvianModder
 */
public class FurnaceRecipeEventJS extends EventJS
{
	public void add(String id, ItemStackJS input, ItemStackJS output, float experience)
	{
		FurnaceRecipes.instance().addSmeltingRecipe(input.itemStack(), output.itemStack(), experience);
	}

	public void add(String id, ItemStackJS input, ItemStackJS output)
	{
		add(id, input, output, 0F);
	}

	public void remove(IIngredientJS output)
	{
		FurnaceRecipes.instance().getSmeltingList().values().removeIf(stack -> output.test(new ItemStackJS.Bound(stack)));
	}

	public void removeInput(IIngredientJS input)
	{
		FurnaceRecipes.instance().getSmeltingList().keySet().removeIf(stack -> input.test(new ItemStackJS.Bound(stack)));
	}
}