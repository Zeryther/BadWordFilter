package com.zeryther.badwordfilter;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.bukkit.plugin.java.JavaPlugin;

public class BWFPlugin extends JavaPlugin {

	private static BWFPlugin instance;
	private static ArrayList<String> BAD_WORDS;
	
	public static final String PERMISSION_SEE_BADWORDED_MESSAGES = "badwordfilter.seewords";
	public static final String PERMISSION_EXEMPT = "badwordfilter.exempt";
	
	public BWFPlugin() {
		instance = this;
		
		saveDefaultConfig();
		registerListeners();
		
		BAD_WORDS = new ArrayList<String>();
		
		for(String s : getConfig().getString("filter.badwords").split(",")) BAD_WORDS.add(s);
	}
	
	private void registerListeners(){
		new BWFListener();
	}
	
	public static BWFPlugin getInstance(){
		return instance;
	}
	
	public ArrayList<String> getBadWords(){
		return BAD_WORDS;
	}
	
	public boolean containsIgnoreCase(String s1, String s2) {
		return Pattern.compile(Pattern.quote(s2), 2).matcher(s1).find();
	}

}
