package me.nahkd.spigot.miscs.api.tinkering;

import java.util.HashMap;
import java.util.List;

public class ModifierTypesManager {
	
	private static HashMap<String, ModifierType> registeredTypes;
	public static HashMap<String, ModifierType> getMap() {
		if (registeredTypes == null) registeredTypes = new HashMap<String, ModifierType>();
		return registeredTypes;
	}
	
	public static void register(ModifierType type) {
		getMap().put(type.name, type);
	}
	
	public static ModifierType getByName(String name) {
		return getMap().get(name);
	}
	public static ModifierType getByLore(String lore) {
		if (lore.startsWith(ModifierType.lore_header)) {
			// The lore contains header
			for (String name : getMap().keySet()) {
				if (lore.startsWith(ModifierType.lore_header + name)) return getMap().get(name);
			}
		}
		return null;
	}
	public static HashMap<ModifierType, Modifier> getModifiersFormLore(List<String> lore) {
		HashMap<ModifierType, Modifier> mods = new HashMap<ModifierType, Modifier>();
		for (String string : lore) {
			if (string.startsWith(ModifierType.lore_header)) {
				for (String name : getMap().keySet()) if (string.startsWith(ModifierType.lore_header + name)) {
					String otherDatas = string.substring(ModifierType.lore_header.length() + name.length() + 1);
					int level = Integer.parseInt(otherDatas.split(" ")[0]);
					int currentItems = Integer.parseInt(otherDatas.split(" ")[1].substring(3).split("\\/")[0]);
					Modifier mod = new Modifier(getMap().get(name), level, currentItems);
					mods.put(mod.type, mod);
				}
			}
		}
		return mods;
	}
	
}
