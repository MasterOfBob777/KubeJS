package dev.latvian.mods.kubejs.client;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.generator.AssetJsonGenerator;
import dev.latvian.mods.kubejs.script.data.KubeJSResourcePack;
import dev.latvian.mods.kubejs.util.KubeJSPlugins;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KubeJSClientResourcePack extends KubeJSResourcePack {
	public static List<PackResources> inject(List<PackResources> packs) {
		List<PackResources> injected = new ArrayList<>(packs);
		injected.add(new KubeJSClientResourcePack());
		return injected;
	}

	public KubeJSClientResourcePack() {
		super(PackType.CLIENT_RESOURCES);
	}

	@Override
	public void generateJsonFiles(Map<ResourceLocation, JsonElement> map) {
		var generator = new AssetJsonGenerator(map);
		KubeJSPlugins.forEachPlugin(p -> p.generateAssetJsons(generator));

		Map<String, String> langMap = new HashMap<>();
		KubeJSPlugins.forEachPlugin(p -> p.generateLang(langMap));

		var lang = new JsonObject();

		for (var entry : langMap.entrySet()) {
			lang.addProperty(entry.getKey(), entry.getValue());
		}

		generator.json(new ResourceLocation("kubejs_generated:lang/en_us"), lang);
	}
}