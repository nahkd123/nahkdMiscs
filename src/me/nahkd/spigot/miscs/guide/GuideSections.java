package me.nahkd.spigot.miscs.guide;

import java.util.HashMap;

public class GuideSections {
	
	private static HashMap<String, GuideSection> sections;
	public static HashMap<String, GuideSection> getMap() {
		if (sections == null) sections = new HashMap<String, GuideSection>();
		return sections;
	}
	
	public static void register(GuideSection section) {
		getMap().put(section.id, section);
	}
	
}
