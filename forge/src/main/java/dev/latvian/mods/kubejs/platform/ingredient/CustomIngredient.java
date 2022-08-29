package dev.latvian.mods.kubejs.platform.ingredient;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.IIngredientSerializer;

import java.util.function.Predicate;

/**
 * @author LatvianModder
 */
public class CustomIngredient extends KubeJSIngredient {
	public static final KubeJSIngredientSerializer<CustomIngredient> SERIALIZER = new KubeJSIngredientSerializer<>(CustomIngredient::new, CustomIngredient::new);

	private final Predicate<ItemStack> predicate;

	public CustomIngredient(Predicate<ItemStack> predicate) {
		this.predicate = predicate;
	}

	private CustomIngredient(JsonObject json) {
		predicate = stack -> true;
	}

	private CustomIngredient(FriendlyByteBuf buf) {
		predicate = stack -> true;
	}

	@Override
	public boolean test(ItemStack stack) {
		return predicate.test(stack);
	}

	@Override
	public IIngredientSerializer<CustomIngredient> getSerializer() {
		return SERIALIZER;
	}

	@Override
	public JsonElement toJson() {
		JsonObject json = new JsonObject();
		json.addProperty("type", "kubejs:custom");
		return json;
	}

	@Override
	public void write(FriendlyByteBuf buf) {
	}
}